package com.example.workshop.test.domain;

import com.example.workshop.domain.StockTick;
import com.example.workshop.domain.User;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatNoException;

public class UserTests {

    @Test
    void shouldCreate() {
        assertThatNoException().isThrownBy(() -> new User(1L, "TEST"));
        assertThat(new User(1L, "TEST"))
                .isNotNull()
                .hasNoNullFieldsOrProperties();
    }
}