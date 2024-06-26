package com.server.mappers;

import com.server.domains.DiscountCode;
import com.server.domains.requests.event.DiscountCodeRequest;
import com.server.domains.responses.DiscountCodeResponse;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class DiscountCodeMapper {
  public static DiscountCodeResponse generateResponse(DiscountCode discountCode) {
    return DiscountCodeResponse.builder()
        .id(discountCode.getId())
        .code(discountCode.getCode())
        .type(discountCode.getType())
        .value(discountCode.getValue())
        .limited(discountCode.isLimited())
        .limitDate(discountCode.getLimitDate())
        .limitTimes(discountCode.getLimitTimes())
        .build();
  }

  public static DiscountCode createDiscountCode(DiscountCodeRequest request) {
    return DiscountCode.builder()
        .id(null)
        .code(request.getCode())
        .type(request.getType())
        .value(request.getValue())
        .limited(request.isLimited())
        .limitDate(request.getLimitDate())
        .limitTimes(request.getLimitTimes())
        .build();
  }

  public static DiscountCode updateDiscountCode(DiscountCode discountCode, DiscountCodeRequest request) {
    return DiscountCode.builder()
        .id(discountCode.getId())
        .code(request.getCode())
        .type(request.getType())
        .value(request.getValue())
        .limited(request.isLimited())
        .limitDate(request.getLimitDate())
        .limitTimes(request.getLimitTimes())
        .build();
  }
}
