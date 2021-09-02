package com.example.workshop.function.quote;

import com.example.workshop.repository.StockRepository;
import com.example.workshop.service.StockService;
import com.example.workshop.service.impl.RepositoryStockService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;

@SpringBootApplication
@EnableReactiveMongoRepositories(basePackages = {"com.example.workshop.repository"})
public class App {
    @Bean
    public StockService StockService(StockRepository repo) {
        return new RepositoryStockService(repo);
    }

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }
}