package it.mloesch.BK_Challenge.Services;

import it.mloesch.BK_Challenge.Exceptions.ExchangeRatesAPIException;
import it.mloesch.BK_Challenge.Models.ExchangeRateInfo;
import it.mloesch.BK_Challenge.Models.ExchangeRateInfoResponse;
import it.mloesch.BK_Challenge.Models.Trend;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.OptionalDouble;

import static it.mloesch.BK_Challenge.Definitions.URLs.EXCHANGE_RATES_API_HISTORY_URL;

@Service
public class ExchangeRateAPIServiceImpl implements ExchangeRateAPIService {
    @Override
    public ExchangeRateInfo getExchangeRateInfo(LocalDate date, String baseCurrency, String targetCurrency) throws ExchangeRatesAPIException {
        String uri = UriComponentsBuilder.fromUriString(EXCHANGE_RATES_API_HISTORY_URL)
                .queryParam("base", baseCurrency)
                .queryParam("symbols", targetCurrency)
                .queryParam("start_at", date.minusDays(6).toString())
                .queryParam("end_at", date)
                .build().toUriString();

        RestTemplate template = new RestTemplate();

        ExchangeRateInfoResponse response;
        try {
            response = template.getForObject(uri, ExchangeRateInfoResponse.class);
        } catch (HttpClientErrorException e) {
            throw new ExchangeRatesAPIException(uri, e.getStatusCode(), e.getResponseBodyAsString());
        }
        if (response == null) {
            throw new RuntimeException();
        }

        List<Double> ratesByDaySorted = getRatesByDaySorted(response, targetCurrency);
        return extractExchangeRateInfo(ratesByDaySorted);

    }

    private ExchangeRateInfo extractExchangeRateInfo(List<Double> ratesByDaySorted) {
        double currentRate = ratesByDaySorted.get(ratesByDaySorted.size() - 1);

        OptionalDouble averageOptional = ratesByDaySorted.stream().mapToDouble(Double::doubleValue).average();
        double average = averageOptional.isPresent() ? averageOptional.getAsDouble() : -1;

        Trend trend = getTrend(ratesByDaySorted);

        return new ExchangeRateInfo(currentRate, average, trend);
    }

    private Trend getTrend(List<Double> ratesByDaySorted) {
        boolean ascending = true;
        boolean descending = true;
        boolean constant = true;

        for (int i = 1; i < ratesByDaySorted.size(); i++) {
            if (ratesByDaySorted.get(i) > ratesByDaySorted.get(i - 1)) {
                descending = false;
                constant = false;
            } else if (ratesByDaySorted.get(i) < ratesByDaySorted.get(i - 1)) {
                ascending = false;
                constant = false;
            } else {
                ascending = false;
                descending = false;
            }
        }

        if (ascending) {
            return Trend.ASCENDING;
        }
        if (descending) {
            return Trend.DESCENDING;
        }
        if (constant) {
            return Trend.CONSTANT;
        }

        return Trend.UNDEFINED;
    }

    private List<Double> getRatesByDaySorted(ExchangeRateInfoResponse response, String targetCurrency) {
        List<Double> ratesByDaySorted = new ArrayList<>();
        for (Map.Entry<LocalDate, Map<String, Double>> entry : response.getRates().entrySet()) {
            ratesByDaySorted.add(entry.getValue().get(targetCurrency));
        }
        return ratesByDaySorted;
    }


}
