package com.igorfraga.spring6restmvc.controller;

import com.igorfraga.spring6restmvc.model.Beer;
import com.igorfraga.spring6restmvc.model.BeerEnums;
import com.igorfraga.spring6restmvc.services.BeerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping(BeerController.BEER_PATH)
public class BeerController {

    public static final String BEER_PATH = "/api/v1/beer";
    public static final String BEER_ID = "/{beerId}";
    public static final String BEER_PATH_WITH_ID = BEER_PATH + BEER_ID;

    private final BeerService beerService;

//    @RequestMapping(method = RequestMethod.GET)
    @GetMapping
    public List<Beer> listBeers() {
        return beerService.listBeers();
    }

    @GetMapping("/metadata")
    public BeerEnums listBeerStyles() {
        return beerService.listBeerStyles();
    }

//    @RequestMapping(value = "/{beerId}", method = RequestMethod.GET)
    @GetMapping(value = BEER_ID)
    public Beer getBeerById(@PathVariable("beerId") UUID beerId) {
        log.debug("GET beer by id in controller - 545 gfghfg");
        return beerService.getBeerById(beerId);
    }

    @PostMapping
    public ResponseEntity handlePost(@RequestBody Beer beer) {
        Beer savedBeer = beerService.saveNewBeer(beer);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Location", BEER_PATH + "/" + savedBeer.getId().toString());

        return new ResponseEntity(headers, HttpStatus.CREATED);
    }

    @PutMapping(BEER_ID)
    public ResponseEntity updateById(@PathVariable("beerId") UUID beerId, @RequestBody Beer beer) {
        beerService.updateBeerById(beerId, beer);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping(BEER_ID)
    public ResponseEntity deleteById(@PathVariable("beerId") UUID beerId) {
        beerService.deleteById(beerId);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @PatchMapping(BEER_ID)
    public ResponseEntity updateBeerPatchById(@PathVariable("beerId") UUID beerId, @RequestBody Beer beer) {
        beerService.patchBeerById(beerId, beer);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}
