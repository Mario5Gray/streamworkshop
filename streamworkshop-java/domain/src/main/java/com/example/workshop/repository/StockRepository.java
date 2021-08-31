package com.example.workshop.repository;

import com.example.workshop.domain.Stock;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import reactor.core.publisher.Mono;

public interface StockRepository extends R2dbcRepository<Stock, String> {
    Mono<Stock> findBySymbol(String symbol);
}
