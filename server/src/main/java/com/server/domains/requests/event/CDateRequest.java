package com.server.domains.requests.event;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CDateRequest {
  private Integer id;
  private Date date;
  private String type;
  private String valueType;
  private String value;
  private Date endDate;
  private String name;
}
