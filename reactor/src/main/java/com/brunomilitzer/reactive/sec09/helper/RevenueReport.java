package com.brunomilitzer.reactive.sec09.helper;

import lombok.ToString;

import java.time.LocalDateTime;
import java.util.Map;

@ToString
public class RevenueReport {

    private final LocalDateTime localDateTime = LocalDateTime.now();
    private final Map<String, Double> revenue;

    public RevenueReport(Map<String, Double> revenue) {
        this.revenue = revenue;
    }
}
