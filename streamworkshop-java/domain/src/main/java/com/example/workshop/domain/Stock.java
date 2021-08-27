package com.example.workshop.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.util.Assert;


@NoArgsConstructor
public class Stock {
    Long id;
    String symbol;
    double price;
    double lastClose;
    double high52Week;
    double low52Week;

    public Stock(Long id, String s, double p) {
        init(id, s, p, 0.0, p, p);
    }

    public  Stock(Long id, String symbol, double price, double last, double high, double low) {
        init(id, symbol, price, last, high, low);
    }

    void init(Long id, String symbol, double price, double last, double high, double low) {
        Assert.state(id != null, () -> "the id should not be null");
        Assert.state(symbol !=null && symbol.length()==4, () -> "Symbol must be 4 alphanumeric characters.");
        Assert.state(((high > low && low < high) || high==low), () -> "High cannot be lower than low.");
        Assert.state((high==0.0) == (0.0==low) || (high > low && low < last && high > last),
                () -> "last is not in bounds of low and high");
        this.id = id;
        this.symbol = symbol;
        this.price = price;
        this.lastClose = last;
        this.high52Week = high;
        this.low52Week = low;
    }
}