package com.example.workshop.test.service;

import com.example.workshop.domain.Stock;
import com.example.workshop.domain.User;
import com.example.workshop.repository.StockRepository;
import com.example.workshop.repository.UserRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.r2dbc.DataR2dbcTest;
import org.springframework.data.r2dbc.core.R2dbcEntityTemplate;
import org.springframework.data.r2dbc.dialect.H2Dialect;
import org.springframework.r2dbc.core.DatabaseClient;
import reactor.test.StepVerifier;


@DataR2dbcTest
public class StockRepositoryTests {

    @Autowired
    private DatabaseClient databaseClient;

    @Autowired
    UserRepository repo;

    @Test
    void shouldSaveFind() {
        var publisher = repo
                .save(new User(null, "MARIO"))
                .then(repo.findAll().last());


        StepVerifier
                .create(publisher)
                .assertNext(s -> {
                    System.out.println(s.getName());
                    Assertions
                            .assertThat(s)
                            .isNotNull()
                            .hasNoNullFieldsOrProperties();
                })
                .verifyComplete();
    }


}