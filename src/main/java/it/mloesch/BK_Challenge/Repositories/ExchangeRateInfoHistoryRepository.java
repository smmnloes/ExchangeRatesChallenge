package it.mloesch.BK_Challenge.Repositories;

import it.mloesch.BK_Challenge.Entities.ExchangeRateInfoHistory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.Collection;

public interface ExchangeRateInfoHistoryRepository extends JpaRepository<ExchangeRateInfoHistory, Long> {
    Collection<ExchangeRateInfoHistory> getExchangeRateInfoHistoriesByDate(LocalDate date);

    Collection<ExchangeRateInfoHistory> getExchangeRateInfoHistoriesByDateBetween(LocalDate start, LocalDate end);
}
