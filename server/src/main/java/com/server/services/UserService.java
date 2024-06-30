package com.server.services;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.server.domains.Event;
import com.server.domains.EventUser;
import com.server.domains.User;
import com.server.domains.enums.UserEventRelationship;
import com.server.domains.requests.user.ChangePasswordRequest;
import com.server.domains.requests.user.ChangeRoleRequest;
import com.server.domains.requests.user.UpdateUserRequest;
import com.server.domains.responses.UserEventsResponse;
import com.server.domains.responses.UserResponse;
import com.server.repositories.EventRepository;
import com.server.repositories.EventUserRepository;
import com.server.repositories.TokenRepository;
import com.server.repositories.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {
  
  private final UserRepository repository;
  private final TokenRepository tokenRepository;
  private final PasswordEncoder passwordEncoder;
  private final EventRepository eventRepository;
  private final EventUserRepository eventUserRepository;
  
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
    repository.save(user);
  }

  public void deleteUser(Integer id) {
    tokenRepository.deleteByUserId(id);
    repository.findById(id).ifPresentOrElse(repository::delete, () -> {
      throw new IllegalStateException("User not found");
    });
  }

  public void updateUser(UpdateUserRequest request, Principal loggedInUser) {
    User user = (User) ((UsernamePasswordAuthenticationToken) loggedInUser).getPrincipal();

    if (request.getName() != null) user.setName(request.getName());
    if (request.getSurname() != null) user.setSurname(request.getSurname());
    if (request.getAlias() != null) user.setAlias(request.getAlias());
    if (request.getPhone() != null) user.setPhone(request.getPhone());

    repository.save(user);
  }

  public void changeRole(ChangeRoleRequest request) {
    User user = repository.findById(request.getId())
      .orElseThrow(() -> new IllegalStateException("User not found"));

    user.setRole(request.getRole());
    repository.save(user);
  }

  public UserResponse getUser(Integer id) {
    User user = repository.findById(id)
      .orElseThrow(() -> new IllegalStateException("User not found"));
    
    return UserResponse.builder()
      .id(user.getId())
      .name(user.getName())
      .username(user.getUsername())
      .email(user.getEmail())
      .alias(user.getAlias())
      .phone(user.getPhone())
      .role(user.getRole())
      .build();
  }

  public List<UserResponse> getAll() {
    Iterable<User> users = repository.findAll();

    List<UserResponse> userResponses = new ArrayList<>();
    users.forEach(user -> userResponses.add(UserResponse.builder()
        .id(user.getId())
        .name(user.getName())
        .username(user.getUsername())
        .email(user.getEmail())
        .alias(user.getAlias())
        .phone(user.getPhone())
        .role(user.getRole())
        .build()));
    return userResponses;
  }

  public List<UserEventsResponse> getUsersEvents(Integer userId) {
    List<UserEventsResponse> userEventsResponses = new ArrayList<>();

    // Owned events
    List<Event> ownedEvents = eventRepository.findByOwnerId(userId);
    ownedEvents.forEach(event -> {
      userEventsResponses.add(UserEventsResponse.builder()
              .eventId(event.getId())
              .relationship(UserEventRelationship.MANAGES)
              .eventName(event.getName())
              .eventDescription(event.getDescription())
              .build());
    });

    // Participates on
    List<EventUser> eventUsers = eventUserRepository.findByUserId(userId);
    eventUsers.forEach(eventUser -> {
      Event event = eventUser.getEvent();
      userEventsResponses.add(UserEventsResponse.builder()
              .eventId(eventUser.getEvent().getId())
              .relationship(UserEventRelationship.PARTICIPATES)
              .eventName(event.getName())
              .eventDescription(event.getDescription())
              .build());
    });

    return userEventsResponses;
  }
}
