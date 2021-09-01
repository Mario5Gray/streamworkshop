package com.example.workshop.service;

import com.example.workshop.domain.Stock;
import reactor.core.publisher.Mono;

public interface StockService {
    Mono<Stock> getQuote(String symbol);
    Mono<Void> tradeStock(String symbol, double price);
}

