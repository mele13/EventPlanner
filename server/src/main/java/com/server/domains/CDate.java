package com.server.domains;

import java.util.Date;

import com.server.domains.enums.CDateType;
import com.server.domains.enums.ValueType;

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
@Table(name = "_c_date")
public class CDate {
  
  @Id
  @GeneratedValue
  private Integer id;
  private Date date;
  private CDateType type;
  private ValueType valueType;
  private String value;
  private Date endDate;
  private String name;

  @ManyToOne
  @JoinColumn(name = "event_id")
  private Event event;
}
