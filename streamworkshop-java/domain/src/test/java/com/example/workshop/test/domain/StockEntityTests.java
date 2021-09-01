package com.example.workshop.test.domain;

import com.example.workshop.domain.Stock;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import reactor.test.StepVerifier;

@DataMongoTest
public class StockEntityTests {

    @Autowired
    private ReactiveMongoTemplate template;

    @Test
    public void shouldSaveFind() {
        var publisher = template
                .save(new Stock("TEST", 0.0))
                .thenMany(template.findAll(Stock.class));

        StepVerifier
                .create(publisher.log())
                .assertNext(s -> {
                    Assertions.assertThat(s)
                            .isNotNull()
                            .hasNoNullFieldsOrProperties();
                })
                .verifyComplete();
    }
}