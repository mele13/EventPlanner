package com.server.domains;

import com.server.domains.enums.Role;

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
@Table(name = "event_user")
public class EventUser {

  @Id
  @GeneratedValue
  private Integer id;
  private boolean attending;
  private String surname;
  private String allergies;
  private boolean nicknamePreference;
  private String nickname;

  @Enumerated(EnumType.STRING)
  private Role role;

  @ManyToOne
  @JoinColumn(name = "event_id")
  private Event event;

  @ManyToOne
  @JoinColumn(name = "user_id", nullable = false)
  private User user;

  @OneToOne
  @JoinColumn(name = "credentials_id")
  private Credentials credentials;

  @ManyToOne
  @JoinColumn(name = "menu_id")
  private Menu menu;

  // private List<String> songs;

  @Lob
  private byte[] image;
}
