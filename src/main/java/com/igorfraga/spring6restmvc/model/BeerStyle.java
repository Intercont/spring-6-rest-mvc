package com.igorfraga.spring6restmvc.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum BeerStyle {
    LAGER("Lager"),
    PILSNER("Pilsner"),
    STOUT("Pula linha"),
    GOSE("Gose"),
    PORTER("Porter"),
    ALE("Ale"),
    WHEAT("Wheat"),
    IPA("Ipa"),
    PALE_ALE("Pale Ale"),
    SAISON("Saison");

    private final String description;
}
