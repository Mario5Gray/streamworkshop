package com.example.workshop.service;

import com.example.workshop.domain.Stock;
import com.example.workshop.domain.StockTick;
import com.example.workshop.domain.User;
import reactor.core.publisher.Mono;

public interface StockQuoteService {
    Mono<Stock> getQuote(String symbol);
}
