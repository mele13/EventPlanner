package com.server.domains.responses;

import lombok.Builder;
import lombok.Data;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CityResponse {
    private Integer id;
    private String name;
    private String code;
    private Integer countryId;
}
