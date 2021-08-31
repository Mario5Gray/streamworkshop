package com.example.workshop.service.impl;

import com.example.workshop.domain.StockTick;
import com.example.workshop.service.StockTickService;
import reactor.core.publisher.Flux;

public class MemoryStockTickService implements StockTickService {
    @Override
    public Flux<StockTick> subscribeToSymbolTicks(String symbol) {
        return null;
    }
}
