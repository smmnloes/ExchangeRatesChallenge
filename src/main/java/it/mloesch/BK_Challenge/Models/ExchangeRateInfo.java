package it.mloesch.BK_Challenge.Models;

import lombok.Data;

@Data
public class ExchangeRateInfo {
    private final double currentRate;
    private final double fiveDayAverage;
    private final Trend exchangeTrend;
}
