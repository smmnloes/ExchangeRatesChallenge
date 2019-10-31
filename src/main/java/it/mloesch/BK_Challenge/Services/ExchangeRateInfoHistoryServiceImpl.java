package it.mloesch.BK_Challenge.Services;

import it.mloesch.BK_Challenge.Entities.ExchangeRateInfoHistory;
import it.mloesch.BK_Challenge.Repositories.ExchangeRateInfoHistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Collection;

@Service
public class ExchangeRateInfoHistoryServiceImpl implements ExchangeRateInfoHistoryService {
    private ExchangeRateInfoHistoryRepository exchangeRateInfoHistoryRepository;

    @Autowired
    public ExchangeRateInfoHistoryServiceImpl(ExchangeRateInfoHistoryRepository exchangeRateInfoHistoryRepository) {
        this.exchangeRateInfoHistoryRepository = exchangeRateInfoHistoryRepository;
    }


    @Override

    public Collection<ExchangeRateInfoHistory> getByDay(LocalDate date) {
        return exchangeRateInfoHistoryRepository.getExchangeRateInfoHistoriesByDate(date);
    }

    @Override
    public Collection<ExchangeRateInfoHistory> getByMonth(LocalDate date) {
        return null;
    }

    @Override
    public void saveHistory(ExchangeRateInfoHistory exchangeRateInfoHistory) {
        exchangeRateInfoHistoryRepository.save(exchangeRateInfoHistory);
    }
}
