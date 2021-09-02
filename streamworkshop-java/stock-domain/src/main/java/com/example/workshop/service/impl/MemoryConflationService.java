package com.example.workshop.service.impl;

import com.example.workshop.domain.Stock;
import com.example.workshop.service.ConflationService;
import com.example.workshop.service.StockService;
import reactor.core.publisher.Mono;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class MemoryConflationService implements ConflationService {
    private Map<String, Stock> conflateMap = new ConcurrentHashMap<>();

    @Override
    public Mono<Stock> conflate(String symbol, double price) {
        return Mono.create(sink ->
        {
            var c = conflateMap.getOrDefault(symbol, new Stock(symbol, price));
            if (price > c.getHigh()) c.setHigh(price);
            if (price < c.getLow()) c.setLow(price);

            sink.success(c);
        });
    }
}