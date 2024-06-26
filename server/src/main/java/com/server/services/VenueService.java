package com.server.services;

import com.server.domains.Address;
import com.server.domains.Venue;
import com.server.domains.requests.location.VenueRequest;
import com.server.domains.responses.VenueResponse;
import com.server.mappers.VenueMapper;
import com.server.repositories.AddressRepository;
import com.server.repositories.VenueRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class VenueService {

  private final VenueRepository venueRepository;
  private final AddressRepository addressRepository;

  public List<VenueResponse> getAll() {
    Iterable<Venue> venues = venueRepository.findAll();

    List<VenueResponse> venueResponses = new ArrayList<>();
    venues.forEach(venue -> venueResponses.add(VenueMapper.generateResponse(venue)));
    return venueResponses;
  }

  public VenueResponse getById(Integer id) {
    return VenueMapper.generateResponse(venueRepository.findById(id)
        .orElseThrow(() -> new IllegalArgumentException("Venue not found")));
  }

  public VenueResponse createVenue(VenueRequest request) {
    Address address = addressRepository.findById(request.getAddressId())
        .orElseThrow(() -> new IllegalArgumentException("Address not found"));
    Venue venue = venueRepository.save(VenueMapper.createVenue(request, address));
    return VenueMapper.generateResponse(venue);
  }

  public VenueResponse updateVenue(VenueRequest request) {
    Optional<Venue> optionalVenue = venueRepository.findById(request.getId());
    Venue existingVenue = optionalVenue.orElseThrow(() -> new IllegalArgumentException("Venue not found"));

    Address address = addressRepository.findById(request.getAddressId())
        .orElseThrow(() -> new IllegalArgumentException("Address not found"));

    Venue updatedVenue = VenueMapper.updateVenue(existingVenue, request, address);
    updatedVenue = venueRepository.save(updatedVenue);
    return VenueMapper.generateResponse(updatedVenue);
  }

  public void deleteVenue(Integer id) {
    venueRepository.deleteById(id);
  }
}
