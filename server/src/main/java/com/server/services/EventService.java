package com.server.services;

import com.server.domains.CDate;
import com.server.domains.DiscountCode;
import com.server.domains.Event;
import com.server.domains.EventUser;
import com.server.domains.requests.event.EventRequest;
import com.server.domains.responses.EventResponse;
import com.server.domains.responses.EventStatisticsResponse;
import com.server.mappers.EventMapper;
import com.server.repositories.CDateRepository;
import com.server.repositories.DiscountCodeRepository;
import com.server.repositories.EventRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class EventService {

  private final EventRepository repository;
  private final CDateRepository dateRepository;
  private final DiscountCodeRepository discountCodeRepository;

  public List<EventResponse> getAll() {
    Iterable<Event> events = repository.findAll();

    List<EventResponse> eventResponses = new ArrayList<>();
    events.forEach(event -> eventResponses.add(EventMapper.generateResponse(event)));
    return eventResponses;
  }

  public EventResponse getById(Integer id) {
    return EventMapper.generateResponse(repository.findById(id)
        .orElseThrow(() -> new IllegalArgumentException("Event not found")));
  }

  public EventResponse createEvent(EventRequest request) {
    List<CDate> dates = getDates(request.getOtherDatesIds());
    List<DiscountCode> discountCodes = getDiscountCodes(request.getDiscountCodesIds());

    final Event event = repository.save(EventMapper.createEvent(request, dates, discountCodes));
    return EventMapper.generateResponse(event);
  }

  public EventResponse updateEvent(EventRequest request) {
    Event existingEvent = repository.findById(request.getId())
        .orElseThrow(() -> new IllegalArgumentException("Event not found"));

    List<CDate> dates = getDates(request.getOtherDatesIds());
    List<DiscountCode> discountCodes = getDiscountCodes(request.getDiscountCodesIds());

    final Event updatedEvent = repository.save(EventMapper.updateEvent(existingEvent, request, dates, discountCodes));
    return EventMapper.generateResponse(updatedEvent);
  }

  public void deleteEvent(Integer id) {
    repository.deleteById(id);
  }

  public EventStatisticsResponse getEventStatistics(Integer eventId) {
    Event event = repository.findById(eventId)
        .orElseThrow(() -> new IllegalArgumentException("Event not found"));

    Long attendeesCount = calculateAttendeesCount(event);
    Long commentsCount = calculateCommentsCount(event);
    Long pageViews = event.getPageViews() != null ? event.getPageViews() : 0L;

    return EventStatisticsResponse.builder()
        .eventId(event.getId())
        .attendeesCount(attendeesCount)
        .commentsCount(commentsCount)
        .pageViews(pageViews)
        // Agregar más campos según sea necesario
        .build();
  }

  // ------------------ Private functions ------------------
  public List<CDate> getDates(List<Integer> otherDatesIds) {
    List<CDate> dates = new ArrayList<>();
    otherDatesIds.forEach(dateId -> {
      CDate date = dateRepository.findById(dateId)
          .orElseThrow(() -> new IllegalArgumentException("CDate not found"));
      dates.add(date);
    });
    return dates;
  }

  public List<DiscountCode> getDiscountCodes(List<Integer> discountCodesIds) {
    List<DiscountCode> discountCodes = new ArrayList<>();
    discountCodesIds.forEach(discountCodeId -> {
      DiscountCode discountCode = discountCodeRepository.findById(discountCodeId)
          .orElseThrow(() -> new IllegalArgumentException("Discount code not found"));
      discountCodes.add(discountCode);
    });
    return discountCodes;
  }

  private Long calculateAttendeesCount(Event event) {
    return event.getEventUsers().stream()
        .filter(EventUser::isAttending)
        .count();
  }

  private Long calculateCommentsCount(Event event) {
    return (long) event.getComments().size();
  }
}
