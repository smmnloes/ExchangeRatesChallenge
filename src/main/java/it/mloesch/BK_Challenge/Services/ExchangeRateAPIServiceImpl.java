package it.mloesch.BK_Challenge.Services;

import Models.ExchangeRateInfo;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.time.LocalDate;

import static it.mloesch.BK_Challenge.Definitions.URLs.EXCHANGE_RATES_API_HISTORY_URL;

@Service
public class ExchangeRateAPIServiceImpl implements ExchangeRateAPIService {
    @Override
    public ExchangeRateInfo getExchangeRateInfo(LocalDate date, String baseCurrency, String targetCurrency) {
        String uri = UriComponentsBuilder.fromUriString(EXCHANGE_RATES_API_HISTORY_URL)
                .queryParam("base", baseCurrency)
                .queryParam("symbols", targetCurrency)
                .queryParam("start_at", date.minusDays(7).toString())
                .queryParam("end_at", date)
                .build().toUriString();

        RestTemplate template = new RestTemplate();
        String result = template.getForObject(uri, String.class);
        System.out.println(result);
        return null;
    }
}
