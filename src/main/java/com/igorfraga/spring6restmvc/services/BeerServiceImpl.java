package com.igorfraga.spring6restmvc.services;

import com.igorfraga.spring6restmvc.model.Beer;
import com.igorfraga.spring6restmvc.model.BeerEnums;
import com.igorfraga.spring6restmvc.model.BeerStyle;
import com.igorfraga.spring6restmvc.model.BeerWhat;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Service
public class BeerServiceImpl implements BeerService {

    private Map<UUID, Beer> beerMap;

    public BeerServiceImpl() {
        this.beerMap = new HashMap<>();

        Beer beer1 = Beer.builder()
                .id(UUID.randomUUID())
                .version(1)
                .beerName("Crank")
                .beerStyle(BeerStyle.IPA)
                .upc("12345")
                .price(new BigDecimal("11.99"))
                .quantityOnHand(9)
                .createdDate(LocalDateTime.now())
                .updatedDate(LocalDateTime.now())
                .build();

        Beer beer2 = Beer.builder()
                .id(UUID.randomUUID())
                .version(1)
                .beerName("Galaxy Cat")
                .beerStyle(BeerStyle.PALE_ALE)
                .upc("12345")
                .price(new BigDecimal("12.99"))
                .quantityOnHand(12)
                .createdDate(LocalDateTime.now())
                .updatedDate(LocalDateTime.now())
                .build();

        Beer beer3 = Beer.builder()
                .id(UUID.randomUUID())
                .version(1)
                .beerName("Sunshine City")
                .beerStyle(BeerStyle.PILSNER)
                .upc("12345")
                .price(new BigDecimal("9.99"))
                .quantityOnHand(12)
                .createdDate(LocalDateTime.now())
                .updatedDate(LocalDateTime.now())
                .build();

        beerMap.put(beer1.getId(), beer1);
        beerMap.put(beer2.getId(), beer2);
        beerMap.put(beer3.getId(), beer3);
    }

    @Override
    public List<Beer> listBeers() {
        return new ArrayList<>(beerMap.values());
    }

    @Override
    public BeerEnums listBeerStyles() {
        return BeerEnums.builder()
                .beerStyles(Arrays.stream(BeerStyle.values()).collect(Collectors.toMap(BeerStyle::name, BeerStyle::getDescription)))
                .beerWhats(Arrays.stream(BeerWhat.values()).collect(Collectors.toMap(BeerWhat::name, BeerWhat::getDescription)))
                .build();
    }

    @Override
    public Beer getBeerById(UUID id) {
        log.debug("Get Beer Id in service was called");
        return beerMap.get(id);
    }

    @Override
    public Beer saveNewBeer(Beer beer) {

        Beer savedBeer = Beer.builder()
                .id(UUID.randomUUID())
                .createdDate(LocalDateTime.now())
                .updatedDate(LocalDateTime.now())
                .beerName(beer.getBeerName())
                .beerStyle(beer.getBeerStyle())
                .quantityOnHand(beer.getQuantityOnHand())
                .upc(beer.getUpc())
                .price(beer.getPrice())
                .version(beer.getVersion())
                .build();

        beerMap.put(savedBeer.getId(), savedBeer);

        return savedBeer;
    }

    @Override
    public void updateBeerById(UUID beerId, Beer beer) {
        Beer beerToUpdate = beerMap.get(beerId);

        beerToUpdate.setBeerName(beer.getBeerName());
        beerToUpdate.setPrice(beer.getPrice());
        beerToUpdate.setUpc(beer.getUpc());
        beerToUpdate.setQuantityOnHand(beer.getQuantityOnHand());
        beerToUpdate.setUpdatedDate(LocalDateTime.now());

        beerMap.put(beerToUpdate.getId(), beerToUpdate);
    }

    @Override
    public void deleteById(UUID beerId) {
        beerMap.remove(beerId);
    }

    @Override
    public void patchBeerById(UUID beerId, Beer beer) {

        Beer existingBeer = beerMap.get(beerId);

        //PATCH LOGIC: if the value is NOT null nor empty, it must be updated
        if (StringUtils.hasText(beer.getBeerName())) {
            existingBeer.setBeerName(beer.getBeerName());
        }

        if (Objects.nonNull(beer.getBeerStyle())) {
            existingBeer.setBeerStyle(beer.getBeerStyle());
        }

        if (Objects.nonNull(beer.getPrice())) {
            existingBeer.setPrice(beer.getPrice());
        }

        if (Objects.nonNull(beer.getQuantityOnHand())) {
            existingBeer.setQuantityOnHand(beer.getQuantityOnHand());
        }

        if (StringUtils.hasText(beer.getUpc())) {
            existingBeer.setUpc(beer.getUpc());
        }
    }
}
