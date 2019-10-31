package it.mloesch.BK_Challenge.RestControllers;

import it.mloesch.BK_Challenge.Entities.ExchangeRateInfoHistory;
import it.mloesch.BK_Challenge.Exceptions.ExchangeRatesAPIException;
import it.mloesch.BK_Challenge.Exceptions.InvalidDateException;
import it.mloesch.BK_Challenge.Models.ExchangeRateInfo;
import it.mloesch.BK_Challenge.Services.ExchangeRateAPIService;
import it.mloesch.BK_Challenge.Services.ExchangeRateInfoHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Collection;

import static it.mloesch.BK_Challenge.Definitions.URLs.INTERNAL_RATE_HISTORY_DAILY_URL;
import static it.mloesch.BK_Challenge.Definitions.URLs.INTERNAL_RATE_INFO_URL;


@RestController()
public class ExchangeRateController {
    private static final LocalDate MIN_DATE = LocalDate.parse("2000-01-01");
    private static final LocalDate MAX_DATE = LocalDate.now().minusDays(1);

    private ExchangeRateAPIService exchangeRateAPIService;
    private ExchangeRateInfoHistoryService exchangeRateInfoHistoryService;

    @Autowired
    public ExchangeRateController(ExchangeRateAPIService exchangeRateAPIService, ExchangeRateInfoHistoryService exchangeRateInfoHistoryService) {
        this.exchangeRateInfoHistoryService = exchangeRateInfoHistoryService;
        this.exchangeRateAPIService = exchangeRateAPIService;
    }

    @GetMapping(INTERNAL_RATE_INFO_URL)
    ResponseEntity<ExchangeRateInfo> getExchangeInfo(@PathVariable String date, @PathVariable String baseCurrency,
                                                     @PathVariable String targetCurrency)
            throws ExchangeRatesAPIException,
            DateTimeParseException,
            InvalidDateException {


        LocalDate parsedDate;
        parsedDate = LocalDate.parse(date);

        if (parsedDate.isBefore(MIN_DATE) || parsedDate.isAfter(MAX_DATE)) {
            throw new InvalidDateException("Invalid date! Only dates between " + MIN_DATE + " and " + MAX_DATE + " are allowed.");
        }

        ExchangeRateInfo exchangeRateInfo = this.exchangeRateAPIService.getExchangeRateInfo(parsedDate, baseCurrency, targetCurrency);
        exchangeRateInfoHistoryService.saveHistory(ExchangeRateInfoHistory.fromQuery(parsedDate, baseCurrency, targetCurrency, exchangeRateInfo));
        return new ResponseEntity<>(exchangeRateInfo, HttpStatus.OK);

    }

    @GetMapping(INTERNAL_RATE_HISTORY_DAILY_URL)
    ResponseEntity<Collection<ExchangeRateInfoHistory>> getDailyExchangeInfo(@PathVariable String year, @PathVariable String month, @PathVariable String day) {
        LocalDate date = LocalDate.parse(year + "-" + month + "-" + day);
        Collection<ExchangeRateInfoHistory> exchangeRateInfoHistories = exchangeRateInfoHistoryService.getByDay(date);
        return new ResponseEntity<>(exchangeRateInfoHistories, HttpStatus.OK);
    }
}
