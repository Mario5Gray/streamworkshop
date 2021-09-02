package com.example.workshop.function.conflate;

import com.example.workshop.service.ConflationService;
import com.example.workshop.service.impl.MemoryConflationService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class App {
    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }
}