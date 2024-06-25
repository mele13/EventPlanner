package com.server.domains;

import java.util.Date;

import com.server.domains.enums.DiscountType;

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
@Table(name = "_discount_codes")
public class DiscountCode {

  @Id
  @GeneratedValue
  private Integer id;
  private String code;
  private DiscountType type;
  private double value;
  private boolean limited;
  private Date limitDate;
  private Integer limitTimes;

  @ManyToOne
  @JoinColumn(name = "event_id")
  private Event event;
}
