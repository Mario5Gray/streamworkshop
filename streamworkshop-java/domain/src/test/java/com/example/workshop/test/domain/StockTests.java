package com.example.workshop.test.domain;

import com.example.workshop.domain.Stock;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.assertj.core.api.Assertions.assertThatNoException;

public class StockTests {

    @Test
    public void shouldStockCreate() {
        assertThatNoException().isThrownBy(() ->
                new Stock("TEST", 10.0));

        Assertions
                .assertThat(new Stock("TEST", 10.00))
                .isNotNull()
                .hasNoNullFieldsOrProperties();
    }

    @Test
    public void shouldNotCreteWhenLowMoreThanHigh() {
        assertThatExceptionOfType(IllegalStateException.class).isThrownBy(() ->
                new Stock("TEST", 10.0, 10.0, 10.0, 15.0));
    }
}
