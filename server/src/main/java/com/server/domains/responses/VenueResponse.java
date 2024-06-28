package com.server.domains.responses;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class VenueResponse {
  private Integer id;
  private String name;
  private String description;
  private String phone;
  private Integer addressId;
  private byte[] image;
}
