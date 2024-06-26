package com.server.services;

import com.server.domains.EventMenu;
import com.server.domains.Event;
import com.server.domains.Menu;
import com.server.domains.requests.event.EventMenuRequest;
import com.server.domains.responses.EventMenuResponse;
import com.server.mappers.EventMenuMapper;
import com.server.repositories.EventMenuRepository;
import com.server.repositories.EventRepository;
import com.server.repositories.MenuRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class EventMenuService {

  private final EventMenuRepository repository;
  private final EventRepository eventRepository;
  private final MenuRepository menuRepository;

  public List<EventMenuResponse> getAll() {
    Iterable<EventMenu> eventMenus = repository.findAll();

    List<EventMenuResponse> eventMenuResponses = new ArrayList<>();
    eventMenus.forEach(eventMenu -> eventMenuResponses.add(EventMenuMapper.generateResponse(eventMenu)));
    return eventMenuResponses;
  }

  public EventMenuResponse getById(Integer id) {
    return EventMenuMapper.generateResponse(repository.findById(id)
        .orElseThrow(() -> new IllegalArgumentException("EventMenu not found")));
  }

  public EventMenuResponse createEventMenu(EventMenuRequest request) {
    Event event = eventRepository.findById(request.getEventId())
        .orElseThrow(() -> new IllegalArgumentException("Event not found"));
    Menu menu = menuRepository.findById(request.getMenuId())
        .orElseThrow(() -> new IllegalArgumentException("Menu not found"));

    final EventMenu eventMenu = repository.save(EventMenuMapper.createEventMenu(request, event, menu));
    return EventMenuMapper.generateResponse(eventMenu);
  }

  public EventMenuResponse updateEventMenu(EventMenuRequest request) {
    Optional<EventMenu> optionalEventMenu = repository.findById(request.getId());
    EventMenu existingEventMenu = optionalEventMenu
        .orElseThrow(() -> new IllegalArgumentException("EventMenu not found"));

    Event event = eventRepository.findById(request.getEventId())
        .orElseThrow(() -> new IllegalArgumentException("Event not found"));
    Menu menu = menuRepository.findById(request.getMenuId())
        .orElseThrow(() -> new IllegalArgumentException("Menu not found"));

    EventMenu updatedEventMenu = EventMenuMapper.updateEventMenu(existingEventMenu, request, event, menu);
    updatedEventMenu = repository.save(updatedEventMenu);
    return EventMenuMapper.generateResponse(updatedEventMenu);
  }

  public void deleteEventMenu(Integer id) {
    repository.deleteById(id);
  }
}
