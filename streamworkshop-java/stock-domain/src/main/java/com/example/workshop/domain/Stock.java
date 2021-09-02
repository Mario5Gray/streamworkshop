package com.example.workshop.domain;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.util.Assert;


@Setter
@Getter
public class Stock {
    @Id
    String symbol;
    double price;
    double high;
    double low;

    public Stock() {
    }

    public Stock(String s, double p) {
        init(s, p, p, p);
    }

    public Stock(String symbol, double price, double high, double low) {
        init(symbol, price, high, low);
    }

    void init(String symbol, double price, double high, double low) {
        Assert.state(symbol != null && symbol.length() == 4, () -> "Symbol must be 4 alphanumeric characters.");
        Assert.state(((high > low && low < high) || high == low), () -> "hi/lo value mismatch.");
        this.symbol = symbol;
        this.price = price;
        this.high = high;
        this.low = low;
    }
}