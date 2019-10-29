package it.mloesch.BK_Challenge.RestControllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import static it.mloesch.BK_Challenge.Definitions.URLs.EXCHANGE_INFO_URL;


@RestController()
public class ExchangeRateController {

    @GetMapping(EXCHANGE_INFO_URL)
    ResponseEntity<String> getExchangeInfo(@PathVariable String date, @PathVariable String baseCurrency,
                                           @PathVariable String targetCurrency) {
        String result = "Date: " + date + " BaseCurrency: " + baseCurrency + " TargetCurrency: " + targetCurrency;

        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
