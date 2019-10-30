package it.mloesch.BK_Challenge.Models;

import lombok.Data;

@Data
public class CustomError {
    private final String status;
    private final String title;
    private final String description;
}
