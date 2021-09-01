package com.example.workshop.test.service;

import com.example.workshop.domain.Stock;
import com.example.workshop.repository.StockRepository;
import com.example.workshop.service.impl.RepositoryStockService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

@ExtendWith(SpringExtension.class)
@Import(RepositoryStockService.class)
public class RepositoryStockServiceTests {

    @MockBean
    private StockRepository repo;

    @Test
    void shouldTradeStock(@Autowired RepositoryStockService stockService) {
        Mockito.when(repo.save(Mockito.any()))
                .thenReturn(Mono.empty());

        StepVerifier
                .create(stockService.tradeStock("TEST", 100.00))
                .verifyComplete();
    }

    @Test
    void shouldReturnQuote(@Autowired  RepositoryStockService stockService) {
        Mockito.when(repo.findBySymbol(Mockito.anyString()))
                .thenReturn(Mono.just(new Stock("TEST", 0.0)));

        StepVerifier
                .create(stockService.getQuote("TEST"))
                .assertNext(s -> {
                    Assertions.assertThat(s)
                            .isNotNull()
                            .hasNoNullFieldsOrProperties();
                });
    }
}