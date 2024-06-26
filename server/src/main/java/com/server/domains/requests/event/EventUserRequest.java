package com.server.domains.requests.event;

import com.server.domains.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EventUserRequest {
  private Integer id;
  private boolean attending;
  private String surname;
  private String allergies;
  private boolean nicknamePreference;
  private String nickname;
  private Role role;
  private Integer eventId;
  private Integer userId;
  private Integer credentialsId;
  private Integer menuId;
  private byte[] image;
}
