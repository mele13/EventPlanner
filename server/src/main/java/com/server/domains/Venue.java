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
@Table(name = "venue")
public class Venue {
  
  @Id
  @GeneratedValue
  private Integer id;
  private String name;
  private String description;
  private String phone;
  private byte[] image;

  @ManyToOne
  @JoinColumn(name = "address_id")
  private Address address;
}
