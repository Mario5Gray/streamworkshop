package com.example.workshop.service;

import com.example.workshop.domain.Stock;
import reactor.core.publisher.Mono;

public interface ConflationService {
    Mono<Stock> conflate(String symbol, double price);
}
