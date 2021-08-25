package com.example.workshop.domain;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public class StockTick {
    String symbol;
    double price;
}
