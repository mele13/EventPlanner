package com.server.services;

import org.springframework.stereotype.Service;

import com.server.domains.Address;
import com.server.domains.requests.address.AddressRequest;
import com.server.mappers.AddressMapper;
import com.server.repositories.AddressRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AddressService {

  private final AddressRepository addressRepository;
  private final AddressMapper addressMapper;

  public Address createOrUpdateAddress(AddressRequest request) {
    Address address = addressMapper.mapToAddress(request);
    return addressRepository.save(address);
  }

  public Address getAddressById(Integer addressId) {
    return addressRepository.findById(addressId)
        .orElseThrow(() -> new IllegalArgumentException("Address not found"));
  }

  public void deleteAddress(Integer addressId) {
    addressRepository.deleteById(addressId);
  }
}
