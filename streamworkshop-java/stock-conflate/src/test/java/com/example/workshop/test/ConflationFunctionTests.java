package com.example.workshop.test;

import com.example.workshop.domain.TradeRequest;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.stream.binder.test.InputDestination;
import org.springframework.cloud.stream.binder.test.OutputDestination;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.converter.CompositeMessageConverter;

import java.util.HashMap;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE,
        properties = {
                "--spring.cloud.function.definition=conflate",
                "--spring.cloud.stream.bindings.conflate-in-0.destination=conflate-req",
                "--spring.cloud.stream.bindings.conflate-out-0.destination=conflate"
        }
)
public class ConflationFunctionTests {

    @Autowired
    InputDestination inputDestination;
    @Autowired
    OutputDestination outputDestination;
    @Autowired
    CompositeMessageConverter converter;

    @Test
    public void shouldPerformConflation() {
        var headers = new HashMap<String, Object>();
        headers.put("contentType", "application/json");
        var messageHeaders = new MessageHeaders(headers);
        inputDestination.send(converter.toMessage(new TradeRequest("TEST", 10.0), messageHeaders));
        var msg = outputDestination.receive(2000);
        Assertions
                .assertThat(msg)
                .isNotNull();
    }
}