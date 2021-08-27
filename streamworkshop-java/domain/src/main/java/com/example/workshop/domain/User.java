package com.example.workshop.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.util.Assert;

@NoArgsConstructor
public class User {
    Long id;
    String name;

    public User(Long id, String name) {
        Assert.notNull(id, () -> "User id must not be null");
        Assert.notNull(name, () -> "Name must not be null");
        Assert.state(name.length() > 3, () -> "Name must be more than 3 characters");
        this.id = id;
        this.name = name;
    }
}