package it.mloesch.BK_Challenge.Exceptions;


import it.mloesch.BK_Challenge.Models.CustomError;
import it.mloesch.BK_Challenge.Models.Errors;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.format.DateTimeParseException;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(ExchangeRatesAPIException.class)
    public ResponseEntity<?> httpClientErrorException(ExchangeRatesAPIException ex) {
        CustomError customError = new CustomError(ex.getStatus().toString(), "ExchangeRateApiException",
                ex.getMessage());
        return new ResponseEntity<>(new Errors(customError), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(DateTimeParseException.class)
    public ResponseEntity<?> dateTimeParseException() {
        CustomError customError = new CustomError(HttpStatus.BAD_REQUEST.toString(), "DateTimeParseException",
                "Error parsing date. Please use format yyyy-MM-dd");
        return new ResponseEntity<>(new Errors(customError), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(InvalidDateException.class)
    public ResponseEntity<?> invalidDateException(InvalidDateException ex) {
        CustomError customError = new CustomError(HttpStatus.BAD_REQUEST.toString(), "InvalidDateException",
                ex.getMessage());
        return new ResponseEntity<>(new Errors(customError), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> exception(Exception ex) {
        ex.printStackTrace();
        CustomError customError = new CustomError(HttpStatus.INTERNAL_SERVER_ERROR.toString(), "InternalServerError",
                "An internal error occured, please try again later.");
        return new ResponseEntity<>(new Errors(customError), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
