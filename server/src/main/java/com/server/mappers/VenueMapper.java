package com.server.mappers;

import com.server.domains.Address;
import com.server.domains.Venue;
import com.server.domains.requests.location.VenueRequest;
import com.server.domains.responses.VenueResponse;

public class VenueMapper {

  public static VenueResponse generateResponse(Venue venue) {
    return VenueResponse.builder()
        .id(venue.getId())
        .name(venue.getName())
        .description(venue.getDescription())
        .phone(venue.getPhone())
        .addressId(venue.getAddress() != null ? venue.getAddress().getId() : null)
        .image(venue.getImage())
        .build();
  }

  public static Venue createVenue(VenueRequest request, Address address) {
    return Venue.builder()
        .name(request.getName())
        .description(request.getDescription())
        .phone(request.getPhone())
        .address(address)
        .image(request.getImage())
        .build();
  }

  public static Venue updateVenue(Venue venue, VenueRequest request, Address address) {
    venue.setName(request.getName());
    venue.setDescription(request.getDescription());
    venue.setPhone(request.getPhone());
    venue.setAddress(address);
    venue.setImage(request.getImage());
    return venue;
  }
}
