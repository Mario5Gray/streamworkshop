package com.example.workshop.test;

import com.example.workshop.repository.StockRepository;
import com.example.workshop.service.impl.RepositoryStockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

@TestConfiguration
public class TestServiceConfig {
    @Autowired
    StockRepository stockRepository;

    @Bean
    public RepositoryStockService stockService() {
        return new RepositoryStockService(stockRepository);
    }
}
