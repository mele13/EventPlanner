package com.server.mappers;

import com.server.domains.CDate;
import com.server.domains.DiscountCode;
import com.server.domains.Event;
import com.server.domains.EventFaq;
import com.server.domains.EventMenu;
import com.server.domains.EventUser;
import com.server.domains.Tag;
import com.server.domains.requests.event.EventRequest;
import com.server.domains.responses.EventResponse;
import com.server.repositories.UserRepository;
import com.server.repositories.VenueRepository;
import com.server.repositories.AddressRepository;
import com.server.repositories.CredentialsRepository;
import com.server.repositories.TagRepository;
import com.server.repositories.EventFaqRepository;
import com.server.repositories.EventMenuRepository;
import com.server.repositories.EventUserRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class EventMapper {

  private static UserRepository userRepository;
  private static VenueRepository venueRepository;
  private static AddressRepository addressRepository;
  private static CredentialsRepository credentialsRepository;
  private static TagRepository tagRepository;
  private static EventFaqRepository faqRepository;
  private static EventMenuRepository menuRepository;
  private static EventUserRepository eventUserRepository;

  public static EventResponse generateResponse(Event event) {
    return EventResponse.builder()
        .id(event.getId())
        .createdAt(event.getCreatedAt())
        .updatedAt(event.getUpdatedAt())
        .createdBy(event.getCreatedBy() != null ? event.getCreatedBy().getId() : null)
        .ownedBy(event.getOwnedBy() != null ? event.getOwnedBy().getId() : null)
        .name(event.getName())
        .description(event.getDescription())
        .venueId(event.getVenue() != null ? event.getVenue().getId() : null)
        .addressId(event.getAddress() != null ? event.getAddress().getId() : null)
        .lang(event.getLang())
        .eventType(event.getEventType())
        .contact(event.getContact())
        .attendanceType(event.getAttendanceType())
        .startDate(event.getStartDate())
        .endDate(event.getEndDate())
        .live(event.isLive())
        .credentialsId(event.getCredentials() != null ? event.getCredentials().getId() : null)
        .limitNumber(event.getLimitNumber())
        .ticketPrice(event.getTicketPrice())
        .ticketCurrency(event.getTicketCurrency())
        .editLimit(event.getEditLimit())
        .sellLimit(event.getSellLimit())
        .userEditLimit(event.getUserEditLimit())
        .notification(event.isNotification())
        .otherDatesIds(event.getOtherDates() != null
            ? event.getOtherDates().stream().map(CDate::getId).collect(Collectors.toList())
            : null)
        .discountCodesIds(event.getDiscountCodes() != null
            ? event.getDiscountCodes().stream().map(DiscountCode::getId).collect(Collectors.toList())
            : null)
        .cancellation(event.getCancellation())
        .cancellationLimit(event.getCancellationLimit())
        .state(event.getState())
        .refundsPolicy(event.getRefundsPolicy())
        .tagIds(event.getTags() != null ? event.getTags().stream().map(Tag::getId).collect(Collectors.toList()) : null)
        .faqIds(
            event.getFaqs() != null ? event.getFaqs().stream().map(EventFaq::getId).collect(Collectors.toList()) : null)
        .menuIds(event.getMenus() != null ? event.getMenus().stream().map(EventMenu::getId).collect(Collectors.toList())
            : null)
        .eventUserIds(event.getEventUsers() != null
            ? event.getEventUsers().stream().map(EventUser::getId).collect(Collectors.toList())
            : null)
        .build();
  }

  public static Event createEvent(EventRequest request, List<CDate> otherDates, List<DiscountCode> discountCodes) {
    return Event.builder()
        .id(null)
        .createdBy(userRepository.findById(request.getCreatedBy()).orElse(null))
        .ownedBy(userRepository.findById(request.getOwnedBy()).orElse(null))
        .name(request.getName())
        .description(request.getDescription())
        .venue(venueRepository.findById(request.getVenueId()).orElse(null))
        .address(addressRepository.findById(request.getAddressId()).orElse(null))
        .lang(request.getLang())
        .eventType(request.getEventType())
        .contact(request.getContact())
        .attendanceType(request.getAttendanceType())
        .startDate(request.getStartDate())
        .endDate(request.getEndDate())
        .live(request.isLive())
        .credentials(credentialsRepository.findById(request.getCredetialsId()).orElse(null))
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
        .tags(request.getTagIds().stream().map(tagRepository::findById).filter(Optional::isPresent).map(Optional::get)
            .collect(Collectors.toList()))
        .faqs(request.getFaqIds().stream().map(faqRepository::findById).filter(Optional::isPresent).map(Optional::get)
            .collect(Collectors.toList()))
        .menus(request.getMenuIds().stream().map(menuRepository::findById).filter(Optional::isPresent)
            .map(Optional::get).collect(Collectors.toList()))
        .eventUsers(request.getEventUserIds().stream().map(eventUserRepository::findById).filter(Optional::isPresent)
            .map(Optional::get).collect(Collectors.toList()))
        .build();
  }

  public static Event updateEvent(Event event, EventRequest request, List<CDate> otherDates, List<DiscountCode> discountCodes) {
    return Event.builder()
        .createdBy(userRepository.findById(request.getCreatedBy()).orElse(null))
        .ownedBy(userRepository.findById(request.getOwnedBy()).orElse(null))
        .name(request.getName())
        .description(request.getDescription())
        .venue(venueRepository.findById(request.getVenueId()).orElse(null))
        .address(addressRepository.findById(request.getAddressId()).orElse(null))
        .lang(request.getLang())
        .eventType(request.getEventType())
        .contact(request.getContact())
        .attendanceType(request.getAttendanceType())
        .startDate(request.getStartDate())
        .endDate(request.getEndDate())
        .live(request.isLive())
        .credentials(credentialsRepository.findById(request.getCredetialsId()).orElse(null))
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
        .tags(request.getTagIds().stream().map(tagRepository::findById).filter(Optional::isPresent).map(Optional::get)
            .collect(Collectors.toList()))
        .faqs(request.getFaqIds().stream().map(faqRepository::findById).filter(Optional::isPresent).map(Optional::get)
            .collect(Collectors.toList()))
        .menus(request.getMenuIds().stream().map(menuRepository::findById).filter(Optional::isPresent)
            .map(Optional::get).collect(Collectors.toList()))
        .eventUsers(request.getEventUserIds().stream().map(eventUserRepository::findById).filter(Optional::isPresent)
            .map(Optional::get).collect(Collectors.toList()))
        .build();
  }
}
