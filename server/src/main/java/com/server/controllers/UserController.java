package com.server.controllers;

import java.security.Principal;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.server.domains.requests.user.ChangePasswordRequest;
import com.server.domains.requests.user.ChangeRoleRequest;
import com.server.domains.requests.user.UpdateUserRequest;
import com.server.domains.responses.UserResponse;
import com.server.services.UserService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UserController {
  
  private final UserService userService;

  @DeleteMapping("/{id}")
  @PreAuthorize("hasRole('ADMIN')")
  public ResponseEntity<String> deleteUser(@PathVariable("id") Integer id) {
    userService.deleteUser(id);
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

  @GetMapping("/{id}")
  @PreAuthorize("isAuthenticated() or hasRole('ADMIN')")
  public ResponseEntity<UserResponse> getUser(@PathVariable("id") Integer id) {
    return ResponseEntity.ok(userService.getUser(id));
  }

  @GetMapping()
  public ResponseEntity<List<UserResponse>> getAll() {
    return ResponseEntity.ok(userService.getAll());
  }

  @PatchMapping("/change-role")
  @PreAuthorize("hasRole('ADMIN')")
  public ResponseEntity<String> changeRole(ChangeRoleRequest request) {
    userService.changeRole(request);
    return ResponseEntity.ok().build();
  }
}
