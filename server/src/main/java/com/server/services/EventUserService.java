package com.server.services;

import com.server.domains.EventUser;
import com.server.domains.Event;
import com.server.domains.User;
import com.server.domains.requests.event.EventUserRequest;
import com.server.domains.Credentials;
import com.server.domains.Menu;
import com.server.domains.responses.EventUserResponse;
import com.server.mappers.EventUserMapper;
import com.server.repositories.EventUserRepository;
import com.server.repositories.EventRepository;
import com.server.repositories.UserRepository;
import com.server.repositories.CredentialsRepository;
import com.server.repositories.MenuRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class EventUserService {

  private final EventUserRepository repository;
  private final EventRepository eventRepository;
  private final UserRepository userRepository;
  private final CredentialsRepository credentialsRepository;
  private final MenuRepository menuRepository;

  public List<EventUserResponse> getAll() {
    Iterable<EventUser> eventUsers = repository.findAll();

    List<EventUserResponse> eventUserResponses = new ArrayList<>();
    eventUsers.forEach(eventUser -> eventUserResponses.add(EventUserMapper.generateResponse(eventUser)));
    return eventUserResponses;
  }

  public EventUserResponse getById(Integer id) {
    return EventUserMapper.generateResponse(repository.findById(id)
        .orElseThrow(() -> new IllegalArgumentException("EventUser not found")));
  }

  public EventUserResponse createEventUser(EventUserRequest request) {
    Event event = eventRepository.findById(request.getEventId())
        .orElseThrow(() -> new IllegalArgumentException("Event not found"));
    User user = userRepository.findById(request.getUserId())
        .orElseThrow(() -> new IllegalArgumentException("User not found"));
    Credentials credentials = credentialsRepository.findById(request.getCredentialsId())
        .orElse(null); // Credentials can be nullable
    Menu menu = menuRepository.findById(request.getMenuId())
        .orElse(null); // Menu can be nullable

    final EventUser eventUser = repository
        .save(EventUserMapper.createEventUser(request, event, user, credentials, menu));
    return EventUserMapper.generateResponse(eventUser);
  }

  public EventUserResponse updateEventUser(EventUserRequest request) {
    Optional<EventUser> optionalEventUser = repository.findById(request.getId());
    EventUser existingEventUser = optionalEventUser
        .orElseThrow(() -> new IllegalArgumentException("EventUser not found"));

    Event event = eventRepository.findById(request.getEventId())
        .orElseThrow(() -> new IllegalArgumentException("Event not found"));
    User user = userRepository.findById(request.getUserId())
        .orElseThrow(() -> new IllegalArgumentException("User not found"));
    Credentials credentials = credentialsRepository.findById(request.getCredentialsId())
        .orElse(null); // Credentials can be nullable
    Menu menu = menuRepository.findById(request.getMenuId())
        .orElse(null); // Menu can be nullable

    EventUser updatedEventUser = EventUserMapper.updateEventUser(existingEventUser, request, event, user, credentials,
        menu);
    updatedEventUser = repository.save(updatedEventUser);
    return EventUserMapper.generateResponse(updatedEventUser);
  }

  public void deleteEventUser(Integer id) {
    repository.deleteById(id);
  }
}
