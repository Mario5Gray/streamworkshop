package com.example.test.domain;

import com.example.workshop.domain.Stock;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.assertj.core.api.Assertions.assertThatNoException;

public class StockTests {

    @Test
    public void shouldStockCreate() {
        assertThatNoException().isThrownBy(() ->
                new Stock(1L, "TEST", 10.0));

        Assertions
                .assertThat(new Stock(1L, "TEST", 10.00))
                .isNotNull()
                .hasNoNullFieldsOrProperties();
    }

    @Test
    public void shouldNotCreate() {
        assertThatExceptionOfType(IllegalStateException.class).isThrownBy(() ->
                new Stock(null, "TEST", 10.0, 10.0, 10.0, 10.0));
    }

    @Test
    public void shouldNotCreteWhenLowMoreThanHigh() {
        assertThatExceptionOfType(IllegalStateException.class).isThrownBy(() ->
                new Stock(null, "TEST", 10.0, 10.0, 10.0, 15.0));
    }

    @Test
    public void shouldNotCreateWhenLastNotWithinHighLowBounds() {
        assertThatExceptionOfType(IllegalStateException.class).isThrownBy(() ->
                new Stock(null, "TEST", 10.0, 150.0, 100.0, 50.0));
    }
}
