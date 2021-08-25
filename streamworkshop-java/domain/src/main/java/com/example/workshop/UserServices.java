package com.example.workshop;

import reactor.core.publisher.Mono;

interface UserServices {
    Mono<User> getUserByNameAndSecret(String name, String secret);
}
