package com.server.domains;

import java.util.Set;

import com.server.domains.enums.Currency;
import com.server.domains.enums.Lang;

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
@Table(name = "country")
public class Country {

  @Id
  @GeneratedValue
  private Integer id;
  private String code;

  @Enumerated(EnumType.STRING)
  private Lang lang;

  @Enumerated(EnumType.STRING)
  private Currency currency;

  @OneToMany(fetch = FetchType.LAZY)
  private Set<City> cities;
  
}
