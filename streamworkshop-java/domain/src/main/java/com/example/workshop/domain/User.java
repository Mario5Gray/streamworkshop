package com.example.workshop.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.util.Assert;

@Getter
@Setter
@NoArgsConstructor
public class User {
    @Id
    Long id;
    String name;

    public User(Long id, String name) {
        Assert.notNull(name, () -> "Name must not be null");
        Assert.state(name.length() > 3, () -> "Name must be more than 3 characters");
        this.id = id;
        this.name = name;
    }
}