package com.server.mappers;

import com.server.domains.Address;
import com.server.domains.City;
import com.server.domains.Country;
import com.server.domains.requests.location.AddressRequest;
import com.server.domains.responses.AddressResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AddressMapper {
  public static AddressResponse generateResponse(Address address) {
    return AddressResponse.builder()
        .id(address.getId())
        .street(address.getStreet())
        .number(address.getNumber())
        .postalCode(address.getPostalCode())
        .state(address.getState())
        .addressDetails(address.getAddressDetails())
        .cityId(address.getCity() != null ? address.getCity().getId() : null)
        .countryId(address.getCountry() != null ? address.getCountry().getId() : null)
        .build();
  }

  public static Address createAddress(AddressRequest request, City city, Country country) {
    return Address.builder()
        .id(null)
        .street(request.getStreet())
        .number(request.getNumber())
        .postalCode(request.getPostalCode())
        .state(request.getState())
        .addressDetails(request.getAddressDetails())
        .city(city)
        .country(country)
        .build();
  }

  public static Address updateAddress(Address address, AddressRequest request, City city, Country country) {
    return Address.builder()
        .id(address.getId())
        .street(request.getStreet())
        .number(request.getNumber())
        .postalCode(request.getPostalCode())
        .state(request.getState())
        .addressDetails(request.getAddressDetails())
        .city(city)
        .country(country)
        .build();
  }
}
