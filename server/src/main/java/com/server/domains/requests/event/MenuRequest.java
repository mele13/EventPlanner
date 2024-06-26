package com.server.domains.requests.event;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MenuRequest {
  private Integer id;
  private String appetizers;
  private String soussal;
  private String main;
  private String side;
  private String sorbet;
  private String type;
  private String dessert;
  private List<String> cocktails;
  private List<String> drinks;
}
