package com.server.mappers;

import com.server.domains.EventUser;
import com.server.domains.User;
import com.server.domains.Credentials;
import com.server.domains.Event;
import com.server.domains.Menu;
import com.server.domains.responses.EventUserResponse;
import com.server.domains.requests.event.EventUserRequest;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class EventUserMapper {

  public static EventUserResponse generateResponse(EventUser eventUser) {
    return EventUserResponse.builder()
        .id(eventUser.getId())
        .attending(eventUser.isAttending())
        .surname(eventUser.getSurname())
        .allergies(eventUser.getAllergies())
        .nicknamePreference(eventUser.isNicknamePreference())
        .nickname(eventUser.getNickname())
        .role(eventUser.getRole())
        .eventId(eventUser.getEvent() != null ? eventUser.getEvent().getId() : null)
        .userId(eventUser.getUser() != null ? eventUser.getUser().getId() : null)
        .credentialsId(eventUser.getCredentials() != null ? eventUser.getCredentials().getId() : null)
        .menuId(eventUser.getMenu() != null ? eventUser.getMenu().getId() : null)
        .image(eventUser.getImage())
        .build();
  }

  public static EventUser createEventUser(EventUserRequest request, Event event, User user, Credentials credentials,
      Menu menu) {
    return EventUser.builder()
        .attending(request.isAttending())
        .surname(request.getSurname())
        .allergies(request.getAllergies())
        .nicknamePreference(request.isNicknamePreference())
        .nickname(request.getNickname())
        .role(request.getRole())
        .event(event)
        .user(user)
        .credentials(credentials)
        .menu(menu)
        .image(request.getImage())
        .build();
  }

  public static EventUser updateEventUser(EventUser eventUser, EventUserRequest request, Event event, User user,
      Credentials credentials, Menu menu) {
    eventUser.setAttending(request.isAttending());
    eventUser.setSurname(request.getSurname());
    eventUser.setAllergies(request.getAllergies());
    eventUser.setNicknamePreference(request.isNicknamePreference());
    eventUser.setNickname(request.getNickname());
    eventUser.setRole(request.getRole());
    eventUser.setEvent(event);
    eventUser.setUser(user);
    eventUser.setCredentials(credentials);
    eventUser.setMenu(menu);
    eventUser.setImage(request.getImage());
    return eventUser;
  }
}
