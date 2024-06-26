package com.server.domains.responses;

import lombok.Builder;
import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CDateResponse {
  private Integer id;
  private Date date;
  private String type;
  private String valueType;
  private String value;
  private Date endDate;
  private String name;
}
