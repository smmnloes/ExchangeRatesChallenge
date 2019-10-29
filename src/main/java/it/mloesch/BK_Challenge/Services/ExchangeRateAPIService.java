package it.mloesch.BK_Challenge.Services;

import Models.ExchangeRateInfo;

import java.time.LocalDate;

public interface ExchangeRateAPIService {
    ExchangeRateInfo getExchangeRateInfo(LocalDate date, String baseCurrency, String targetCurrency);
}
