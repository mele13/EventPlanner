package com.server.domains.responses;

import com.server.domains.enums.Role;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class EventUserResponse {
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
