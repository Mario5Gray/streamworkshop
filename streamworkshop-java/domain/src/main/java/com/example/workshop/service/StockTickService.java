package com.example.workshop.service;

import com.example.workshop.domain.StockTick;
import com.example.workshop.domain.User;
import reactor.core.publisher.Flux;

public interface StockTickService {
    Flux<StockTick> subscribeToSymbolTicks(String symbol);
}
