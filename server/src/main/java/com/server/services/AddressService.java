package com.server.services;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.server.domains.Address;
import com.server.domains.City;
import com.server.domains.Country;
import com.server.domains.requests.location.AddressRequest;
import com.server.domains.responses.AddressResponse;
import com.server.mappers.AddressMapper;
import com.server.repositories.AddressRepository;
import com.server.repositories.CityRepository;
import com.server.repositories.CountryRepository;
import com.server.tools.LocationTools;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AddressService {

  private final AddressRepository repository;
  private final CityRepository cityRepository;
  private final CountryRepository countryRepository;

  public AddressResponse createAddress(AddressRequest request) {
    City city = LocationTools.getCity(request.getCityId(), cityRepository);
    Country country = LocationTools.getCountry(request.getCountryId(), countryRepository);
    final Address address = repository.save(AddressMapper.createAddress(request, city, country));
    return AddressMapper.generateResponse(address);
  }

  public AddressResponse getAddressById(Integer id) {
    return AddressMapper.generateResponse(repository.findById(id)
        .orElseThrow(() -> new IllegalArgumentException("Address not found")));
  }

  public AddressResponse updateAddress(AddressRequest request) {
    Optional<Address> optionalAddress = repository.findById(request.getId());
    Address existingAddress = optionalAddress.orElseThrow(() -> new IllegalArgumentException("Address not found"));

    City city = LocationTools.getCity(request.getCityId(), cityRepository);
    Country country = LocationTools.getCountry(request.getCountryId(), countryRepository);

    final Address updatedAddress = repository
        .save(AddressMapper.updateAddress(existingAddress, request, city, country));
    return AddressMapper.generateResponse(updatedAddress);
  }

  public void deleteAddress(Integer id) {
    repository.deleteById(id);
  }
}
