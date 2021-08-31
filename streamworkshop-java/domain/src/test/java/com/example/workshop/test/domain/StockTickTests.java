package com.example.workshop.test.domain;

import com.example.workshop.domain.StockTick;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatNoException;

public class StockTickTests {

    @Test
    void shouldConstruct() {
        assertThatNoException().isThrownBy(() -> new StockTick("TEST", 1234));
        assertThat(new StockTick("TEST", 1234))
                .isNotNull()
                .hasNoNullFieldsOrProperties();
    }
}