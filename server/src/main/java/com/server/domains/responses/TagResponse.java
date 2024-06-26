package com.server.domains.responses;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TagResponse {
  private Integer id;
  private String name;
  private String shortName;
}
