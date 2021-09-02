package com.example.workshop.function.conflate;

import com.example.workshop.domain.Stock;
import com.example.workshop.domain.TradeRequest;
import com.example.workshop.service.ConflationService;
import com.example.workshop.service.impl.MemoryConflationService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import reactor.core.publisher.Flux;

import java.util.function.Function;

@RequiredArgsConstructor
public class ConflateFunctions {
    private final ConflationService conflationService = new MemoryConflationService();

    @Bean
    public Function<Flux<TradeRequest>, Flux<Stock>> conflate() {
        return trades -> trades.flatMap(s ->
                conflationService
                        .conflate(s.getSymbol(), s.getPrice())
        );
    }
}