package com.server.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.server.domains.City;
import com.server.domains.Country;
import com.server.domains.requests.location.CityRequest;
import com.server.domains.responses.CityResponse;
import com.server.mappers.CityMapper;
import com.server.repositories.CityRepository;
import com.server.repositories.CountryRepository;
import com.server.tools.LocationTools;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class CityService {
  private final CityRepository repository;
  private final CountryRepository countryRepository;

  public List<CityResponse> getAll() {
    Iterable<City> cities = repository.findAll();

    List<CityResponse> cityResponses = new ArrayList<>();
    cities.forEach(city -> cityResponses.add(CityMapper.generateResponse(city)));
    return cityResponses;
  }

  public CityResponse getById(Integer id) {
    return CityMapper.generateResponse(repository.findById(id)
        .orElseThrow(() -> new IllegalArgumentException("City not found")));
  }

  public CityResponse createCity(CityRequest request) {
    Country country = LocationTools.getCountry(request.getCountryId(), countryRepository);
    final City city = repository.save(CityMapper.createCity(request, country));
    return CityMapper.generateResponse(city);
  }

  public CityResponse updateCity(CityRequest request) {
    Optional<City> optionalCity = repository.findById(request.getId());
    City existingCity = optionalCity.orElseThrow(() -> new IllegalArgumentException("City not found"));

    Country country = LocationTools.getCountry(request.getCountryId(), countryRepository);
    final City updatedCity = repository.save(CityMapper.updateCity(existingCity, request, country));
    return CityMapper.generateResponse(updatedCity);
  }

  public void deleteCity(Integer id) {
    repository.deleteById(id);
  }
}
