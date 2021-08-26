package com.example.workshop.service;

import com.example.workshop.domain.User;
import reactor.core.publisher.Mono;

interface UserAuthenticationService {
    Mono<User> getUserByNameAndSecret(String name, String secret);
}

interface UserService {
    Mono<Void> addUser(User user);
}