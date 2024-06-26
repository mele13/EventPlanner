package com.server.mappers;

import com.server.domains.CDate;
import com.server.domains.enums.CDateType;
import com.server.domains.enums.ValueType;
import com.server.domains.requests.event.CDateRequest;
import com.server.domains.responses.CDateResponse;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class CDateMapper {
  public static CDateResponse generateResponse(CDate cDate) {
    return CDateResponse.builder()
        .id(cDate.getId())
        .date(cDate.getDate())
        .type(cDate.getType().name())
        .valueType(cDate.getValueType().name())
        .value(cDate.getValue())
        .endDate(cDate.getEndDate())
        .name(cDate.getName())
        .build();
  }

  public static CDate createCDate(CDateRequest request) {
    return CDate.builder()
        .id(null)
        .date(request.getDate())
        .type(CDateType.valueOf(request.getType()))
        .valueType(ValueType.valueOf(request.getValueType()))
        .value(request.getValue())
        .endDate(request.getEndDate())
        .name(request.getName())
        .build();
  }

  public static CDate updateCDate(CDate cDate, CDateRequest request) {
    return CDate.builder()
        .id(cDate.getId())
        .date(request.getDate())
        .type(CDateType.valueOf(request.getType()))
        .valueType(ValueType.valueOf(request.getValueType()))
        .value(request.getValue())
        .endDate(request.getEndDate())
        .name(request.getName())
        .build();
  }
}
