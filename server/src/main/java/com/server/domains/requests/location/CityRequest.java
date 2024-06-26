package com.server.domains.requests.location;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CityRequest {
  private Integer id;
  private String name;
  private String code;
  private Integer countryId;
}
