package com.server.domains.responses;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class MenuResponse {
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