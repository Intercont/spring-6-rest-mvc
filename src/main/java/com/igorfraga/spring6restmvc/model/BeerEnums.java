package com.igorfraga.spring6restmvc.model;

import lombok.Builder;
import lombok.Data;

import java.util.Map;

@Data
@Builder
public class BeerEnums {

    Map<String, String> beerStyles;
    Map<String, String> beerWhats;

}
