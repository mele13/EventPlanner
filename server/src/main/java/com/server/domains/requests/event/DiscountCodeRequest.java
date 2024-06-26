package com.server.domains.requests.event;

import com.server.domains.enums.DiscountType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DiscountCodeRequest {
  private Integer id;
  private String code;
  private DiscountType type;
  private double value;
  private boolean limited;
  private Date limitDate;
  private Integer limitTimes;
}
