package com.server.auth.requests;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ChangePasswordRequest {
  private String currentPassword;
  private String newPassowrd;
  private String confirmationPassword;  
}
