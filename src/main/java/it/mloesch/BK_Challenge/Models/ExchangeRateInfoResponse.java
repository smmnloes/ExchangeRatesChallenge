package it.mloesch.BK_Challenge.Models;

import lombok.Data;

import java.time.LocalDate;
import java.util.Map;
import java.util.SortedMap;

@Data
public class ExchangeRateInfoResponse {

    private LocalDate start_at;
    private LocalDate end_at;
    private String base;
    private SortedMap<LocalDate, Map<String, Double>> rates;

}
