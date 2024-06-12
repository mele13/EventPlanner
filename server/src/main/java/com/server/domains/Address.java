package com.server.domains;

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
@Table(name = "address")
public class Address {
  
  @Id
  @GeneratedValue
  private Integer id;
  private String street;
  private String number;
  private String postalCode;
  private String state;
  private String addressDetails;

  @ManyToOne
  @JoinColumn(name = "city_id")
  private City city;

  @ManyToOne
  @JoinColumn(name = "country_id")
  private Country country;
}
