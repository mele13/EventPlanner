package com.server.auth;

import java.io.IOException;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

// import io.swagger.v3.oas.annotations.tags.Tag;
import com.server.auth.requests.LoginRequest;
import com.server.auth.requests.RegisterRequest;
import com.server.auth.responses.AuthenticationResponse;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
// @Tag(name = "Authentication")
@RequestMapping("/auth")
public class AuthenticationController {

  private final AuthenticationService authService;

  @PostMapping("/register")
  public ResponseEntity<AuthenticationResponse> register(@RequestBody RegisterRequest request) {
    return ResponseEntity.ok(authService.register(request));
  }

  @PostMapping("/login")
  public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody LoginRequest request) {
    return ResponseEntity.ok(authService.login(request));
  }

  @PostMapping("/refesh-token")
  public void refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException {
    authService.refreshToken(request, response);
  }
}
