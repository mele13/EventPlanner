package com.server.domains.responses;

import lombok.Builder;
import lombok.Data;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AddressResponse {
  private Integer id;
  private String street;
  private String number;
  private String postalCode;
  private String state;
  private String addressDetails;
  private Integer cityId;
  private Integer countryId;
}
