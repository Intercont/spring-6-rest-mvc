package com.igorfraga.spring6restmvc.services;

import com.igorfraga.spring6restmvc.model.Beer;
import com.igorfraga.spring6restmvc.model.BeerEnums;
import com.igorfraga.spring6restmvc.model.BeerStyle;

import java.util.List;
import java.util.UUID;

public interface BeerService {

    List<Beer> listBeers();

    BeerEnums listBeerStyles();

    Beer getBeerById(UUID id);

    Beer saveNewBeer(Beer beer);

    void updateBeerById(UUID beerId, Beer beer);

    void deleteById(UUID beerId);

    void patchBeerById(UUID beerId, Beer beer);
}
