package com.example.workshop.function;

import com.example.workshop.domain.Stock;
import com.example.workshop.domain.StockTick;
import com.example.workshop.service.StockQuoteService;
import com.example.workshop.service.StockTickService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.function.Function;

@RequiredArgsConstructor
public class StockFunctions {
    private final StockQuoteService quoteService;
    private final StockTickService tickService;

    @Bean
    public Function<Mono<String>, Mono<Stock>> snapshot() {
        return stringMono -> stringMono
                .flatMap(quoteService::getQuote);
    }

    @Bean
    public Function<Mono<String>, Flux<StockTick>> stockTicks() {
        return stringMono -> stringMono.flatMapMany(tickService::subscribeToSymbolTicks);
    }
}