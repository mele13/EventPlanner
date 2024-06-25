package com.server.controllers;

import java.security.Principal;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.server.domains.requests.user.ChangePasswordRequest;
import com.server.domains.requests.user.ChangeRoleRequest;
import com.server.domains.requests.user.UserRequest;
import com.server.domains.requests.user.UpdateUserRequest;
import com.server.domains.responses.GetUserResponse;
import com.server.services.UserService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UserController {
  
  private final UserService userService;

  @DeleteMapping()
  @PreAuthorize("hasRole('ADMIN')")
  public ResponseEntity<String> deleteUser(@RequestBody UserRequest request) {
    userService.deleteUser(request);
    return ResponseEntity.ok().build();
  }

  @PatchMapping()
  @PreAuthorize("isAuthenticated()")
  public ResponseEntity<String> changePassword(@RequestBody ChangePasswordRequest request, Principal loggedInUser) {
    userService.changePassword(request, loggedInUser);
    return ResponseEntity.ok().build();
  }

  @PutMapping()
  @PreAuthorize("isAuthenticated()")
  public ResponseEntity<String> updateUser(@RequestBody UpdateUserRequest request, Principal loggedInUser) {
    userService.updateUser(request, loggedInUser);
    return ResponseEntity.ok().build();
  }

  @GetMapping()
  @PreAuthorize("isAuthenticated() or hasRole('ADMIN')")
  public ResponseEntity<GetUserResponse> getUser(UserRequest request) {
    return ResponseEntity.ok(userService.getUser(request));
  }

  @PatchMapping("/change-role")
  @PreAuthorize("hasRole('ADMIN')")
  public ResponseEntity<String> changeRole(ChangeRoleRequest request) {
    userService.changeRole(request);
    return ResponseEntity.ok().build();
  }
}
