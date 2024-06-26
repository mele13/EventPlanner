package com.server.domains.responses;

import com.server.domains.enums.Currency;
import com.server.domains.enums.Lang;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CountryResponse {
  private Integer id;
  private String code;
  private Lang lang;
  private Currency currency;
}
