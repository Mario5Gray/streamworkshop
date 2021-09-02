package com.example.workshop.function.trade;

import com.example.workshop.domain.Stock;
import com.example.workshop.domain.TradeRequest;
import com.example.workshop.service.StockService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import reactor.core.publisher.Flux;

import java.util.function.Function;

@RequiredArgsConstructor
@Configuration
public class TradeFunctions {
    private final StockService stockService;

    @Bean
    public Function<Flux<TradeRequest>, Flux<Stock>> conflate() {
        return trades -> trades.flatMap(s ->
                stockService
                        .tradeStock(s.getSymbol(), s.getPrice())
        );
    }
}