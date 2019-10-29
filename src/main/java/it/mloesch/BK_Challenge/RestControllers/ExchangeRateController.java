package it.mloesch.BK_Challenge.RestControllers;

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

    @GetMapping(EXCHANGE_INFO_URL)
    ResponseEntity<String> getExchangeInfo(@PathVariable String date, @PathVariable String baseCurrency,
                                           @PathVariable String targetCurrency) {

        LocalDate inputDate;
        try {
            inputDate = LocalDate.parse(date);
        } catch (DateTimeParseException e) {
            return new ResponseEntity<>("Invalid Date Format! Please use yyyy-MM-dd", HttpStatus.BAD_REQUEST);
        }

        LocalDate minimumDate = LocalDate.parse("2000-01-01");
        LocalDate maximumDate = LocalDate.now().minusDays(1);
        if (inputDate.isBefore(minimumDate) || inputDate.isAfter(maximumDate)) {
            return new ResponseEntity<>("Invalid date! Only dates between 2000-01-01 and yesterday allowed!", HttpStatus.BAD_REQUEST);
        }

        return ResponseEntity.ok().build();

    }
}
