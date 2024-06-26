package com.server.mappers;

import com.server.domains.EventMenu;
import com.server.domains.Event;
import com.server.domains.Menu;
import com.server.domains.requests.event.EventMenuRequest;
import com.server.domains.responses.EventMenuResponse;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class EventMenuMapper {

  public static EventMenuResponse generateResponse(EventMenu eventMenu) {
    return EventMenuResponse.builder()
        .id(eventMenu.getId())
        .menuInfo(eventMenu.getMenuInfo())
        .menuWarn(eventMenu.getMenuWarn())
        .eventId(eventMenu.getEvent() != null ? eventMenu.getEvent().getId() : null)
        .menuId(eventMenu.getMenu() != null ? eventMenu.getMenu().getId() : null)
        .build();
  }

  public static EventMenu createEventMenu(EventMenuRequest request, Event event, Menu menu) {
    return EventMenu.builder()
        .menuInfo(request.getMenuInfo())
        .menuWarn(request.getMenuWarn())
        .event(event)
        .menu(menu)
        .build();
  }

  public static EventMenu updateEventMenu(EventMenu eventMenu, EventMenuRequest request, Event event, Menu menu) {
    eventMenu.setMenuInfo(request.getMenuInfo());
    eventMenu.setMenuWarn(request.getMenuWarn());
    eventMenu.setEvent(event);
    eventMenu.setMenu(menu);
    return eventMenu;
  }
}
