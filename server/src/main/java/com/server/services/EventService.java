package com.server.services;

import com.server.domains.CDate;
import com.server.domains.DiscountCode;
import com.server.domains.Event;
import com.server.domains.EventUser;
import com.server.domains.enums.ChangeType;
import com.server.domains.requests.GeneralRequest;
import com.server.domains.requests.event.EventRequest;
import com.server.domains.requests.mongo.EventChangeLogRequest;
import com.server.domains.responses.EventResponse;
import com.server.domains.responses.EventStatisticsResponse;
import com.server.mappers.EventMapper;
import com.server.mappers.mongo.EventChangeLogMapper;
import com.server.repositories.CDateRepository;
import com.server.repositories.DiscountCodeRepository;
import com.server.repositories.EventRepository;
import com.server.services.mongo.EventChangeLogService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RequiredArgsConstructor
@Service
public class EventService {

  private final EventRepository repository;
  private final CDateRepository dateRepository;
  private final DiscountCodeRepository discountCodeRepository;
  private final EventChangeLogService eventChangeLogService;

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
    final EventChangeLogRequest changeLogRequest = EventChangeLogMapper.newRequest(
        request.getId().toString(),
        ChangeType.CREATE,
        null,
        null,
        null,
        request.getCreatedBy().toString(),
        new Date());
    eventChangeLogService.addLogChange(changeLogRequest);
    return EventMapper.generateResponse(event);
  }

  public EventResponse updateEvent(EventRequest request) {
    Event existingEvent = repository.findById(request.getId())
        .orElseThrow(() -> new IllegalArgumentException("Event not found"));

    List<CDate> dates = getDates(request.getOtherDatesIds());
    List<DiscountCode> discountCodes = getDiscountCodes(request.getDiscountCodesIds());
    List<EventChangeLogRequest> changeLogs = new ArrayList<>();

    // Compare and log changes for all fields using reflection
    compareAndLogChanges(existingEvent, request, changeLogs);

    final Event updatedEvent = repository.save(EventMapper.updateEvent(existingEvent, request, dates, discountCodes));
    changeLogs.forEach(eventChangeLogService::addLogChange);
    return EventMapper.generateResponse(updatedEvent);
  }

  public void deleteEvent(GeneralRequest request) {
    repository.deleteById(request.getId());
    final EventChangeLogRequest changeLogRequest = EventChangeLogMapper.newRequest(
        request.getId().toString(),
        ChangeType.DELETE,
        null,
        null,
        null,
        request.getUserId().toString(),
        new Date());
    eventChangeLogService.addLogChange(changeLogRequest);
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

  private void compareAndLogChanges(Event existingEvent, EventRequest request, List<EventChangeLogRequest> changeLogs) {
    Field[] fields = Event.class.getDeclaredFields();
    for (Field field : fields) {
      field.setAccessible(true);
      try {
        Object oldValue = field.get(existingEvent);
        Object newValue = field.get(request);
        String fieldName = field.getName();
        logChangeIfDifferent(oldValue, newValue, fieldName, request, changeLogs);
      } catch (IllegalAccessException e) {
        e.printStackTrace();
      }
    }
  }

  private void logChangeIfDifferent(Object oldValue, Object newValue, String fieldName, EventRequest request,
      List<EventChangeLogRequest> changeLogs) {
    if ((oldValue != null && !oldValue.equals(newValue)) || (oldValue == null && newValue != null)) {
      EventChangeLogRequest changeLogRequest = EventChangeLogMapper.newRequest(
          request.getId().toString(),
          ChangeType.UPDATE,
          fieldName,
          oldValue != null ? oldValue.toString() : null,
          newValue != null ? newValue.toString() : null,
          request.getCreatedBy().toString(),
          new Date());
      changeLogs.add(changeLogRequest);
    }
  }
}
