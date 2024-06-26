package com.server.domains.responses;

import com.server.domains.enums.DiscountType;
import lombok.Builder;
import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DiscountCodeResponse {
  private Integer id;
  private String code;
  private DiscountType type;
  private double value;
  private boolean limited;
  private Date limitDate;
  private Integer limitTimes;
}
