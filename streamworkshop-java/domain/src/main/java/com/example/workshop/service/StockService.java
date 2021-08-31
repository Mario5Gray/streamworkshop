package com.example.workshop.service;

import com.example.workshop.domain.Stock;
import com.example.workshop.domain.StockTick;
import com.example.workshop.domain.User;
import reactor.core.publisher.Mono;

public interface StockService {
    Mono<Stock> getQuote(String symbol);
    Mono<Void> tradeStock(String symbol, double price);
}

