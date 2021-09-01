package com.example.workshop.test;

import com.example.workshop.App;
import com.example.workshop.domain.Stock;
import com.example.workshop.function.StockFunctions;
import com.example.workshop.repository.StockRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.stream.binder.test.InputDestination;
import org.springframework.cloud.stream.binder.test.OutputDestination;
import org.springframework.cloud.stream.binder.test.TestChannelBinderConfiguration;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.converter.CompositeMessageConverter;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;
import reactor.test.StepVerifier;

import java.util.HashMap;

@Testcontainers
@Tag("integration")
public class StockTradeFunctionTests {

    @Container
    static final MongoDBContainer mongoDBContainer = new MongoDBContainer(DockerImageName.parse("mongo:4.0.10"));

    @Test
    public void saveByRepositoryQueryByStream() {
        try (ConfigurableApplicationContext context = new SpringApplicationBuilder(
                TestChannelBinderConfiguration
                        .getCompleteConfiguration(App.class, StockFunctions.class)
        ).web(WebApplicationType.NONE).run(
                "--spring.data.mongodb.uri=" + mongoDBContainer.getReplicaSetUrl(),
                "--spring.cloud.function.definition=trade | tick",
                "--spring.cloud.stream.bindings.snapshot-in-0.destination=snapshot-req",
                "--spring.cloud.stream.bindings.snapshot-out-0.destination=snapshots"
        )) {
            var repository = context.getBean(StockRepository.class);
            StepVerifier
                    .create(repository.save(new Stock("TEST", 42.0)))
                    .assertNext(s -> {
                        Assertions
                                .assertThat(s)
                                .isNotNull();
                    })
                    .verifyComplete();
            var converter = context.getBean(CompositeMessageConverter.class);
            var headers = new HashMap<String, Object>();
            headers.put("contentType", "application/json");
            var messageHeaders = new MessageHeaders(headers);

            InputDestination inputDestination = context.getBean(InputDestination.class);
            inputDestination.send(converter.toMessage("TEST", messageHeaders));

            OutputDestination outputDestination = context.getBean(OutputDestination.class);
            var msg = outputDestination.receive(2000);
            Assertions
                    .assertThat(msg)
                    .isNotNull();
        }
    }
}
