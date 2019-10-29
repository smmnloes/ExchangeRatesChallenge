package it.mloesch.BK_Challenge.RestControllers;

import Models.ExchangeRateInfo;
import it.mloesch.BK_Challenge.Services.ExchangeRateAPIService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import static it.mloesch.BK_Challenge.Definitions.URLs.EXCHANGE_INFO_URL;


@RestController()
public class ExchangeRateController {
    private ExchangeRateAPIService exchangeRateAPIService;


    @Autowired
    public ExchangeRateController(ExchangeRateAPIService exchangeRateAPIService) {
        this.exchangeRateAPIService = exchangeRateAPIService;
    }

    @GetMapping(EXCHANGE_INFO_URL)
    ResponseEntity<ExchangeRateInfo> getExchangeInfo(@PathVariable String date, @PathVariable String baseCurrency,
                                           @PathVariable String targetCurrency) {

        LocalDate parsedDate;
        try {
            parsedDate = LocalDate.parse(date);
        } catch (DateTimeParseException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        LocalDate minimumDate = LocalDate.parse("2000-01-01");
        LocalDate maximumDate = LocalDate.now().minusDays(1);
        if (parsedDate.isBefore(minimumDate) || parsedDate.isAfter(maximumDate)) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        ExchangeRateInfo exchangeRateInfo = this.exchangeRateAPIService.getExchangeRateInfo(parsedDate, baseCurrency, targetCurrency);
        return new ResponseEntity<>(exchangeRateInfo, HttpStatus.OK);

    }
}
