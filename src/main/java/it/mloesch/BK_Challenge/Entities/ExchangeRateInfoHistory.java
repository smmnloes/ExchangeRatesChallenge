package it.mloesch.BK_Challenge.Entities;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import it.mloesch.BK_Challenge.Models.ExchangeRateInfo;
import it.mloesch.BK_Challenge.Models.Trend;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.transaction.Transactional;
import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@Table(name = "exchange_rate_info_history")
@Transactional
@JsonIgnoreProperties(value = {
        "exchangeRateInfoHistoryId"
})
public class ExchangeRateInfoHistory {
    @Id
    @GeneratedValue
    @Column(name = "exchange_rate_info_history_id")
    private Long exchangeRateInfoHistoryId;

    private LocalDate date;

    private String baseCurrency;
    private String targetCurrency;

    private double currentRate;
    private double fiveDayAverage;
    private Trend exchangeTrend;

    public ExchangeRateInfoHistory(LocalDate date, String baseCurrency, String targetCurrency, double currentRate, double fiveDayAverage, Trend exchangeTrend) {
        this.date = date;
        this.currentRate = currentRate;
        this.fiveDayAverage = fiveDayAverage;
        this.exchangeTrend = exchangeTrend;
        this.baseCurrency = baseCurrency;
        this.targetCurrency = targetCurrency;
    }

    public static ExchangeRateInfoHistory fromQuery(LocalDate date, String baseCurrency, String targetCurrency, ExchangeRateInfo exchangeRateInfo) {
        return new ExchangeRateInfoHistory(date, baseCurrency, targetCurrency, exchangeRateInfo.getCurrentRate(), exchangeRateInfo.getFiveDayAverage(), exchangeRateInfo.getExchangeTrend());
    }

}
