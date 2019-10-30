package it.mloesch.BK_Challenge.Services;

import it.mloesch.BK_Challenge.Exceptions.ExchangeRatesAPIException;
import it.mloesch.BK_Challenge.Models.ExchangeRateInfo;

import java.time.LocalDate;

public interface ExchangeRateAPIService {
    ExchangeRateInfo getExchangeRateInfo(LocalDate date, String baseCurrency, String targetCurrency) throws ExchangeRatesAPIException;
}
