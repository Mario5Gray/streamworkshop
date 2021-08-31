package com.example.workshop.service.impl;

import com.example.workshop.domain.Stock;
import com.example.workshop.repository.StockRepository;
import com.example.workshop.service.StockService;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
public class RepositoryStockService implements StockService {

    private final StockRepository stockRepository;

    @Override
    public Mono<Stock> getQuote(String symbol) {
        return stockRepository.findBySymbol(symbol);
    }

    @Override
    public Mono<Void> tradeStock(String symbol, double price) {
        return stockRepository
                .save(new Stock(symbol, price))
                .then();
    }
}