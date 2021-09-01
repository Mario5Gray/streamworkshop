package com.example.workshop.service.impl;

import com.example.workshop.domain.Stock;
import com.example.workshop.repository.StockRepository;
import com.example.workshop.service.StockService;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

// TODO
// replace this conflation with a specific service ( see == https://github.com/spring-cloud/stream-applications )
@RequiredArgsConstructor
public class RepositoryStockService implements StockService {

    private final Map<String, Stock> conflateMap = new ConcurrentHashMap<>();
    private final StockRepository stockRepository;

    @Override
    public Mono<Stock> getQuote(String symbol) {
        return stockRepository.findBySymbol(symbol);
    }

    @Override
    public Mono<Stock> tradeStock(String symbol, double price) {
        return Mono.just(new Stock(symbol, price))
                .map(s -> {
                    var c = conflateMap.getOrDefault(symbol, s);
                    if (price > c.getHigh()) c.setHigh(price);
                    if (price < c.getLow()) c.setLow(price);

                    return c;
                })
                .flatMap(stockRepository::save);
    }
}