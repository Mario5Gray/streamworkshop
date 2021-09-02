package com.example.workshop.function;

import com.example.workshop.domain.Stock;
import com.example.workshop.domain.TradeRequest;
import com.example.workshop.service.StockService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import reactor.core.publisher.Flux;

import java.util.function.Function;

@RequiredArgsConstructor
public class TradeFunctions {
    private final StockService stockService;

    @Bean
    public Function<Flux<TradeRequest>, Flux<Stock>> trade() {
        return trades -> trades.flatMap(s ->
                stockService
                        .tradeStock(s.getSymbol(), s.getPrice())
        );
    }
}