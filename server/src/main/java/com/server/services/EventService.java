package com.server.services;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import com.server.domains.Address;
import com.server.domains.CDate;
import com.server.domains.Credentials;
import com.server.domains.DiscountCode;
import com.server.domains.Event;
import com.server.domains.EventFaq;
import com.server.domains.EventMenu;
import com.server.domains.EventUser;
import com.server.domains.Tag;
import com.server.domains.User;
import com.server.domains.Venue;
import com.server.domains.requests.event.CreateEventRequest;
import com.server.repositories.AddressRepository;
import com.server.repositories.CDateRepository;
import com.server.repositories.CredentialsRepository;
import com.server.repositories.DiscountCodeRepository;
import com.server.repositories.EventFaqRepository;
import com.server.repositories.EventMenuRepository;
import com.server.repositories.EventRepository;
import com.server.repositories.EventUserRepository;
import com.server.repositories.TagRepository;
import com.server.repositories.UserRepository;
import com.server.repositories.VenueRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EventService {

  private final EventRepository eventRepository;
  private final UserRepository userRepository;
  private final VenueRepository venueRepository;
  private final AddressRepository addressRepository;
  private final CredentialsRepository credentialsRepository;
  private final TagRepository tagRepository;
  private final EventFaqRepository faqRepository;
  private final EventMenuRepository menuRepository;
  private final EventUserRepository eventUserRepository;
  private final CDateRepository cDateRepository;
  private final DiscountCodeRepository discountCodeRepository;

  public void createEvent(CreateEventRequest request) {
    validateCreateEventRequest(request);

    User createdBy = getUserById(request.getCreatedBy(), "User not found");
    User ownedBy = getUserById(request.getOwnedBy(), "User not found");

    Venue venue = getVenueById(request.getVenueId());
    Address address = getAddressById(request.getAddressId());
    Credentials credentials = getCredentialsById(request.getCredetialsId());

    List<Tag> tags = findEntitiesByIds(request.getTagIds(), tagRepository);
    List<EventFaq> faqs = findEntitiesByIds(request.getFaqIds(), faqRepository);
    List<EventMenu> menus = findEntitiesByIds(request.getMenuIds(), menuRepository);
    List<EventUser> eventUsers = findEntitiesByIds(request.getEventUserIds(), eventUserRepository);
    List<CDate> otherDates = findEntitiesByIds(request.getOtherDatesIds(), cDateRepository);
    List<DiscountCode> discountCodes = findEntitiesByIds(request.getDiscountCodesIds(), discountCodeRepository);

    Event event = buildEvent(request, createdBy, ownedBy, venue, address, credentials, tags, faqs, menus, eventUsers,
        otherDates, discountCodes);
    eventRepository.save(event);
  }

  // ---------------------- Private functions ----------------------
  private void validateCreateEventRequest(CreateEventRequest request) {
    if (request.getVenueId() == null && request.getAddressId() == null) {
      throw new IllegalArgumentException("Either a venue or an address must be provided");
    }
  }

  private User getUserById(Integer userId, String errorMessage) {
    Optional<User> optionalUser = userRepository.findById(userId);
    return optionalUser.orElseThrow(() -> new IllegalArgumentException(errorMessage));
  }

  private Venue getVenueById(Integer venueId) {
    if (venueId == null) {
      return null;
    }
    return venueRepository.findById(venueId)
        .orElseThrow(() -> new IllegalArgumentException("Venue not found"));
  }

  private Address getAddressById(Integer addressId) {
    if (addressId == null) {
      return null;
    }
    return addressRepository.findById(addressId)
        .orElseThrow(() -> new IllegalArgumentException("Address not found"));
  }

  private Credentials getCredentialsById(Integer credentialsId) {
    if (credentialsId == null) {
      return null;
    }
    return credentialsRepository.findById(credentialsId)
        .orElseThrow(() -> new IllegalArgumentException("Credentials not found"));
  }

  private <T> List<T> findEntitiesByIds(List<Integer> ids, CrudRepository<T, Integer> repository) {
    if (ids == null || ids.isEmpty()) {
      return null;
    }
    return StreamSupport.stream(repository.findAllById(ids).spliterator(), false)
        .collect(Collectors.toList());
  }

  private Event buildEvent(CreateEventRequest request, User createdBy, User ownedBy, Venue venue,
      Address address, Credentials credentials, List<Tag> tags, List<EventFaq> faqs,
      List<EventMenu> menus, List<EventUser> eventUsers, List<CDate> otherDates,
      List<DiscountCode> discountCodes) {
    return Event.builder()
        .createdAt(new Date())
        .createdBy(createdBy)
        .ownedBy(ownedBy)
        .name(request.getName())
        .description(request.getDescription())
        .venue(venue)
        .address(address)
        .lang(request.getLang())
        .eventType(request.getEventType())
        .contact(request.getContact())
        .attendanceType(request.getAttendanceType())
        .startDate(request.getStartDate())
        .endDate(request.getEndDate())
        .live(request.isLive())
        .credentials(credentials)
        .limitNumber(request.getLimitNumber())
        .ticketPrice(request.getTicketPrice())
        .ticketCurrency(request.getTicketCurrency())
        .editLimit(request.getEditLimit())
        .sellLimit(request.getSellLimit())
        .userEditLimit(request.getUserEditLimit())
        .notification(request.isNotification())
        .otherDates(otherDates)
        .discountCodes(discountCodes)
        .cancellation(request.getCancellation())
        .cancellationLimit(request.getCancellationLimit())
        .state(request.getState())
        .refundsPolicy(request.getRefundsPolicy())
        .tags(tags)
        .faqs(faqs)
        .menus(menus)
        .eventUsers(eventUsers)
        .build();
  }
}
