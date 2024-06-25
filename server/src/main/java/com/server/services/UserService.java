package com.server.services;

import java.security.Principal;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.server.domains.User;
import com.server.domains.requests.user.ChangePasswordRequest;
import com.server.domains.requests.user.ChangeRoleRequest;
import com.server.domains.requests.user.UserRequest;
import com.server.domains.requests.user.UpdateUserRequest;
import com.server.domains.responses.GetUserResponse;
import com.server.repositories.TokenRepository;
import com.server.repositories.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {
  
  private final UserRepository userRepository;
  private final TokenRepository tokenRepository;
  private final PasswordEncoder passwordEncoder;
  
  public void changePassword(ChangePasswordRequest request, Principal loggedInUser) {
    User user = (User) ((UsernamePasswordAuthenticationToken) loggedInUser).getPrincipal();

    // New password different than current
    if (!request.getNewPassowrd().equals(request.getConfirmationPassword())) {
      throw new IllegalStateException("Passwords do not match.");
    }

    // Current password verification
    if (!passwordEncoder.matches(request.getCurrentPassword(), user.getPassword())) {
      throw new IllegalStateException("Provided an incorrect password.");
    }

    user.setPassword(passwordEncoder.encode(request.getNewPassowrd()));
    userRepository.save(user);
  }

  public void deleteUser(UserRequest request) {
    tokenRepository.deleteByUserId(request.getId());
    userRepository.findById(request.getId()).ifPresentOrElse(userRepository::delete, () -> {
      throw new IllegalStateException("User not found");
    });
  }

  public void updateUser(UpdateUserRequest request, Principal loggedInUser) {
    User user = (User) ((UsernamePasswordAuthenticationToken) loggedInUser).getPrincipal();

    if (request.getName() != null) user.setName(request.getName());
    if (request.getSurname() != null) user.setSurname(request.getSurname());
    if (request.getAlias() != null) user.setAlias(request.getAlias());
    if (request.getPhone() != null) user.setPhone(request.getPhone());

    userRepository.save(user);
  }

  public void changeRole(ChangeRoleRequest request) {
    User user = userRepository.findById(request.getId())
      .orElseThrow(() -> new IllegalStateException("User not found"));

    user.setRole(request.getRole());
    userRepository.save(user);
  }

  public GetUserResponse getUser(UserRequest request) {
    User user = userRepository.findById(request.getId())
      .orElseThrow(() -> new IllegalStateException("User not found"));
    
    return GetUserResponse.builder()
      .id(user.getId())
      .name(user.getName())
      .username(user.getUsername())
      .email(user.getEmail())
      .alias(user.getAlias())
      .phone(user.getPhone())
      .role(user.getRole())
      .build();
  }
}
