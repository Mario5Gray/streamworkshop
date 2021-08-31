package com.example.workshop.domain;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.util.Assert;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Table;


@Setter
@Getter
@Entity
@Table(name = "stock")
public class Stock {
    @Id
    String symbol;
    double price;
    double lastClose;
    double high52Week;
    double low52Week;

    public Stock() {}

    public Stock(String s, double p) {
        init(s, p, 0.0, p, p);
    }

    public  Stock(String symbol, double price, double last, double high, double low) {
        init(symbol, price, last, high, low);
    }

    void init(String symbol, double price, double last, double high, double low) {
        //Assert.state(id != null, () -> "the id should not be null");
        Assert.state(symbol !=null && symbol.length()==4, () -> "Symbol must be 4 alphanumeric characters.");
        Assert.state(((high > low && low < high) || high==low), () -> "High cannot be lower than low.");
        this.symbol = symbol;
        this.price = price;
        this.lastClose = last;
        this.high52Week = high;
        this.low52Week = low;
    }
}