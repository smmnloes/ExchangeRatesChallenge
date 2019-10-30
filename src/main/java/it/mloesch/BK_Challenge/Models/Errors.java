package it.mloesch.BK_Challenge.Models;

import lombok.Data;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Data
public class Errors {
    private List<CustomError> errors;

    public Errors(CustomError... errors) {
        this.errors = new ArrayList<>();
        this.errors.addAll(Arrays.asList(errors));
    }
}
