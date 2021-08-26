package com.example.workshop.domain;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public class Stock {
    String symbol;
    double price;
    double lastClose;
    double high52Week;
    double low52Week;
}
