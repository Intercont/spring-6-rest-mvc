package com.igorfraga.spring6restmvc.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum BeerWhat {
    SMALL("Small"),
    MEDIUM("Medium"),
    BIG("Big");

    private final String description;
}
