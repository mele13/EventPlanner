package com.server.services;

import com.server.domains.Country;
import com.server.domains.requests.location.CountryRequest;
import com.server.domains.responses.CountryResponse;
import com.server.mappers.CountryMapper;
import com.server.repositories.CountryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CountryService {

  private final CountryRepository repository;

  public List<CountryResponse> getAll() {
    Iterable<Country> countries = repository.findAll();

    List<CountryResponse> countryResponses = new ArrayList<>();
    countries.forEach(country -> countryResponses.add(CountryMapper.generateResponse(country)));
    return countryResponses;
  }

  public CountryResponse getByName(String name) {
    return CountryMapper.generateResponse(repository.findByName(name)
        .orElseThrow(() -> new IllegalArgumentException("Country not found")));
  }

  public CountryResponse createCountry(CountryRequest request) {
    final Country createdCountry = repository.save(CountryMapper.createCountry(request));
    return CountryMapper.generateResponse(createdCountry);
  }

  public CountryResponse updateCountry(CountryRequest request) {
    Optional<Country> optionalCountry = repository.findById(request.getId());
    Country existingCountry = optionalCountry.orElseThrow(() -> new IllegalArgumentException("Country not found"));

    final Country updatedCountry = repository.save(CountryMapper.updateCountry(existingCountry, request));
    return CountryMapper.generateResponse(updatedCountry);
  }

  public void deleteCountry(Integer id) {
    repository.deleteById(id);
  }
}
