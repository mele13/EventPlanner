package com.server.mappers;

import com.server.domains.City;
import com.server.domains.Country;
import com.server.domains.requests.location.CityRequest;
import com.server.domains.responses.CityResponse;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class CityMapper {
  public static CityResponse generateResponse(City city) {
    return CityResponse.builder()
        .id(city.getId())
        .name(city.getName())
        .code(city.getCode())
        .countryId(city.getCountry() != null ? city.getCountry().getId() : null)
        .build();
  }

  public static City createCity(CityRequest request, Country country) {
    return City.builder()
        .id(null)
        .name(request.getName())
        .code(request.getCode())
        .country(country)
        .build();
  }

  public static City updateCity(City city, CityRequest request, Country country) {
    return City.builder()
        .id(city.getId())
        .name(request.getName())
        .code(request.getCode())
        .country(country)
        .build();
  }
}
