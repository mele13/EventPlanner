package com.server.tools;

import java.util.Optional;

import com.server.domains.City;
import com.server.domains.Country;
import com.server.repositories.CityRepository;
import com.server.repositories.CountryRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class LocationTools {

  public static Country getCountry(Integer id, CountryRepository repository) {
    Optional<Country> optionalCountry = repository.findById(id);
    return optionalCountry.orElseThrow(() -> new IllegalArgumentException("Country not found"));
  }

  public static City getCity(Integer id, CityRepository repository) {
    Optional<City> optionalCity = repository.findById(id);
    return optionalCity.orElseThrow(() -> new IllegalArgumentException("City not found"));
  }
}
