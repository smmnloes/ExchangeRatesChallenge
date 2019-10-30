package it.mloesch.BK_Challenge.Exceptions;


import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@Getter
@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ExchangeRatesAPIException extends Exception {
    private HttpStatus status;

    public ExchangeRatesAPIException(String uri, HttpStatus status, String error) {
        super("Performing GET on URI: " + uri + " returned error: "
                + error);
        this.status = status;
    }
}
