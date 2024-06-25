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
@Table(name = "event_menu")
public class EventMenu {
  
  @Id
  @GeneratedValue
  private Integer id;
  private String menuInfo;
  private String menuWarn;

  @ManyToOne
  @JoinColumn(name = "event_id", nullable = false)
  private Event event;
  
  @ManyToOne
  @JoinColumn(name = "menu_id", nullable = false)
  private Menu menu;

    
}
