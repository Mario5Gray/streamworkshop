package com.example.workshop.test;

import com.example.workshop.domain.Stock;
import com.example.workshop.repository.StockRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.stream.binder.test.InputDestination;
import org.springframework.cloud.stream.binder.test.OutputDestination;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.converter.CompositeMessageConverter;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;
import reactor.test.StepVerifier;

import java.util.HashMap;

@Testcontainers
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE,
        properties = {
                "--spring.cloud.function.definition=getQuote",
                "--spring.cloud.stream.bindings.getQuote-in-0.destination=quotes-req",
                "--spring.cloud.stream.bindings.getQuote-out-0.destination=quotes"
        }
)
@Tag("integration")
public class StockQuotesFunctionTests {

    @Container
    static final MongoDBContainer mongoDBContainer = new MongoDBContainer(DockerImageName.parse("mongo:4.0.10"));

    @DynamicPropertySource
    static void registerProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.data.mongodb.uri", () -> mongoDBContainer.getReplicaSetUrl());
    }

    @Autowired
    StockRepository repository;
    @Autowired
    InputDestination inputDestination;
    @Autowired
    OutputDestination outputDestination;
    @Autowired
    CompositeMessageConverter converter;

    @Test
    public void shouldReceiveSnapshot() {
        StepVerifier
                .create(repository.save(new Stock("TEST", 42.0)))
                .assertNext(s -> {
                    Assertions
                            .assertThat(s)
                            .isNotNull();
                })
                .verifyComplete();

        var headers = new HashMap<String, Object>();
        headers.put("contentType", "application/json");
        var messageHeaders = new MessageHeaders(headers);
        inputDestination.send(converter.toMessage("TEST", messageHeaders));
        var msg = outputDestination.receive(2000);
        Assertions
                .assertThat(msg)
                .isNotNull();
    }
}