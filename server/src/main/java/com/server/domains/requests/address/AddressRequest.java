package com.server.domains.requests.address;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AddressRequest {
  private Integer id; // Editing
  private String street;
  private String number;
  private String postalCode;
  private String state;
  private String addressDetails;
  private Integer cityId;
  private Integer countryId;
}
