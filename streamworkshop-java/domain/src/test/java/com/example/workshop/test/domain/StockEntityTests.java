package com.example.workshop.test.domain;

import com.example.workshop.domain.Stock;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.r2dbc.DataR2dbcTest;
import org.springframework.data.r2dbc.core.R2dbcEntityTemplate;
import org.springframework.data.r2dbc.dialect.H2Dialect;
import org.springframework.r2dbc.core.DatabaseClient;
import reactor.test.StepVerifier;

@DataR2dbcTest
public class StockEntityTests {

    @Autowired
    private DatabaseClient databaseClient;

    @Test
    public void shouldSaveFind() {
        var template = new R2dbcEntityTemplate(databaseClient, H2Dialect.INSTANCE);

        var data = template.select(Stock.class).first();
        var publisher = template.insert(new Stock("TEST", 0.0)).then(data);

        StepVerifier
                .create(publisher.log())
                .assertNext(s -> {
                    Assertions.assertThat(s)
                            .isNotNull()
                            .hasNoNullFieldsOrProperties();
                })
                .verifyComplete();
    }

    @Test
    public void shouldSave() {
        var template = new R2dbcEntityTemplate(databaseClient, H2Dialect.INSTANCE);

        var publisher = template.insert(new Stock("TTTT", 0.0));

        StepVerifier
                .create(publisher)
                .assertNext(s -> {
                    Assertions.assertThat(s)
                            .isNotNull()
                            .hasNoNullFieldsOrProperties();
                })
                .verifyComplete();
    }
}
