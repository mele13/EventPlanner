package com.server.domains;

import java.util.List;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "menu")
public class Menu {

  @Id
  @GeneratedValue
  private Integer id;
  private String appetizers;
  private String soussal; // Soups or Salads
  private String main;
  private String side;
  private String sorbet;
  private String type;
  private String dessert;

  @ElementCollection
  private List<String> cocktails;

  @ElementCollection
  private List<String> drinks;
}
