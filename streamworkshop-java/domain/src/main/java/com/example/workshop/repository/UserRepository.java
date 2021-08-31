package com.example.workshop.repository;

import com.example.workshop.domain.User;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Mono;

public interface UserRepository extends ReactiveMongoRepository<User, Long> {
    Mono<User> findByName(String name);
}