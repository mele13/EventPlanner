package com.server.mappers;

import com.server.domains.Address;
import com.server.domains.City;
import com.server.domains.Country;
import com.server.domains.requests.address.AddressRequest;
import com.server.repositories.CityRepository;
import com.server.repositories.CountryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AddressMapper {

    private final CityRepository cityRepository;
    private final CountryRepository countryRepository;

    public Address mapToAddress(AddressRequest request) {
        Address address = new Address();
        address.setId(request.getId()); // Editing
        address.setStreet(request.getStreet());
        address.setNumber(request.getNumber());
        address.setPostalCode(request.getPostalCode());
        address.setState(request.getState());
        address.setAddressDetails(request.getAddressDetails());

        City city = cityRepository.findById(request.getCityId())
                .orElseThrow(() -> new IllegalArgumentException("City not found"));
        address.setCity(city);

        Country country = countryRepository.findById(request.getCountryId())
                .orElseThrow(() -> new IllegalArgumentException("Country not found"));
        address.setCountry(country);

        return address;
    }
}
