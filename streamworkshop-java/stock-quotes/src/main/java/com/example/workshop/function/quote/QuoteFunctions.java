package com.example.workshop.function.quote;

import com.example.workshop.domain.Stock;
import com.example.workshop.domain.TradeRequest;
import com.example.workshop.service.StockService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.function.Function;

@RequiredArgsConstructor
@Configuration
public class QuoteFunctions {
    private final StockService stockService;

    @Bean
    public Function<Mono<String>, Mono<Stock>> getQuote() {
        return stringMono -> stringMono
                .flatMap(stockService::getQuote);
    }
}