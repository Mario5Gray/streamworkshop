package com.example.workshop.test;

import com.example.workshop.function.StockFunctions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.test.context.runner.ApplicationContextRunner;
import org.springframework.cloud.stream.binder.test.InputDestination;
import org.springframework.cloud.stream.binder.test.TestChannelBinderConfiguration;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.messaging.support.GenericMessage;

public class StockFunctionTests {

    @Test
    public void shouldReceiveQuote() {
        try (ConfigurableApplicationContext context = new SpringApplicationBuilder(
                TestChannelBinderConfiguration
                        .getCompleteConfiguration(StockFunctions.class)
        ).web(WebApplicationType.NONE).run(
                "--spring.cloud.function.definition=snapshot",
                "--spring.cloud.stream.bindings.snapshot-in-0.destination=snapshot-req",
                "--spring.cloud.stream.bindings.snapshot-out-0.destination=snapshots"
        )) {
            InputDestination inputDestination = context.getBean(InputDestination.class);
            inputDestination.send(new GenericMessage<>("TTTT"));

        }
    }
}
