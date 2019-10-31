package it.mloesch.BK_Challenge.Services;

import it.mloesch.BK_Challenge.Entities.ExchangeRateInfoHistory;

import java.time.LocalDate;
import java.util.Collection;

public interface ExchangeRateInfoHistoryService {
    Collection<ExchangeRateInfoHistory> getByDay(LocalDate date);

    Collection<ExchangeRateInfoHistory> getByMonth(LocalDate date);

    void saveHistory(ExchangeRateInfoHistory exchangeRateInfoHistory);
}
