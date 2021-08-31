package com.example.workshop.repository;

import com.example.workshop.domain.Stock;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Mono;

public interface StockRepository extends ReactiveMongoRepository<Stock, String> {
    Mono<Stock> findBySymbol(String symbol);
}
