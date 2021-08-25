package com.example.workshop.service;

import com.example.workshop.domain.User;
import reactor.core.publisher.Mono;

interface UserServices {
    Mono<User> getUserByNameAndSecret(String name, String secret);
}

interface UserBaseService {
    Mono<Void> addUser(User user);
}