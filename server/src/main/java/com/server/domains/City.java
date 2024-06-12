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
@Table(name = "city")
public class City {
  
  @Id
  @GeneratedValue
  private Integer id;
  private String name;
  private String code;

  @ManyToOne
  @JoinColumn(name = "country_id")
  private Country country;
}
