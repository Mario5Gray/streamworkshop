package com.example.workshop.test.service;

import com.example.workshop.domain.Stock;
import com.example.workshop.repository.StockRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import reactor.test.StepVerifier;

@DataMongoTest
public class StockRepositoryTests {

    @Autowired
    StockRepository repo;

    @Test
    void shouldSaveFind() {
        var publisher = repo
                .save(new Stock("TEST", 1.0))
                .then(repo.findAll().last());

        StepVerifier
                .create(publisher)
                .assertNext(s -> {
                    System.out.println(s.getSymbol());
                    Assertions
                            .assertThat(s)
                            .isNotNull()
                            .hasNoNullFieldsOrProperties();
                })
                .verifyComplete();
    }
}