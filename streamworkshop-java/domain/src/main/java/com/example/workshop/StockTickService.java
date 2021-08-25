package com.example.workshop;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

interface StockTickService {
    Flux<StockTick> subscribeToSymbolTicks(User user, String symbol);
}

interface StockQuoteService {
    Mono<StockTick> getQuote(User user, String symbol);
}

interface StockExchangeService {
    Mono<StockTick> tradeSymbol(User user, String symbol, int quantity);
}