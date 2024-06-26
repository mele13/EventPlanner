package com.server.services;

import com.server.repositories.EventRepository;
import com.server.repositories.UserRepository;
import com.server.domains.responses.AppStatisticsResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AppStatisticsService {

  private final UserRepository userRepository;
  private final EventRepository eventRepository;

  public AppStatisticsResponse getAppStatistics() {
    Long totalUsers = userRepository.count();
    Long totalEvents = eventRepository.count();

    return AppStatisticsResponse.builder()
        .totalUsers(totalUsers)
        .totalEvents(totalEvents)
        .build();
  }
}
