package com.example.workshop.service;

import com.example.workshop.domain.StockTick;
import com.example.workshop.domain.User;
import reactor.core.publisher.Mono;

interface StockServices {
    Mono<StockTick> tradeSymbol(User user, String symbol, int quantity);
}