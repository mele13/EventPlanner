package com.server.mappers;

import com.server.domains.Country;
import com.server.domains.requests.location.CountryRequest;
import com.server.domains.responses.CountryResponse;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class CountryMapper {
  public static CountryResponse generateResponse(Country country) {
    return CountryResponse.builder()
        .id(country.getId())
        .code(country.getCode())
        .lang(country.getLang())
        .currency(country.getCurrency())
        .build();
  }

  public static Country createCountry(CountryRequest request) {
    return Country.builder()
        .id(null)
        .code(request.getCode())
        .lang(request.getLang())
        .currency(request.getCurrency())
        .build();
  }

  public static Country updateCountry(Country country, CountryRequest request) {
    return Country.builder()
        .id(country.getId())
        .code(request.getCode())
        .lang(request.getLang())
        .currency(request.getCurrency())
        .build();
  }
}
