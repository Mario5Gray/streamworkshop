package com.example.workshop.function;

import com.example.workshop.domain.Stock;
import com.example.workshop.domain.StockTick;
import com.example.workshop.service.StockService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.function.Function;

@RequiredArgsConstructor
public class StockFunctions {
    private final StockService stockService;

    @Bean
    public Function<Flux<TradeRequest>, Flux<StockTick>> trade() {
        return trades -> trades.flatMap(s ->
                stockService
                        .tradeStock(s.getSymbol(), s.getPrice())
                        .map(f -> new StockTick(s.getSymbol(), s.getPrice()))
        );
    }

    @Bean
    public Function<Mono<String>, Mono<Stock>> snapshot() {
        return stringMono -> stringMono
                .flatMap(stockService::getQuote);
    }
}