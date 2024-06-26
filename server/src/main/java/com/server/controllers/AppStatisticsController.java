package com.server.controllers;

import com.server.domains.responses.AppStatisticsResponse;
import com.server.services.AppStatisticsService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/statistics")
public class AppStatisticsController {

  private final AppStatisticsService appStatisticsService;

  @GetMapping()
  public AppStatisticsResponse getAppStatistics() {
    return appStatisticsService.getAppStatistics();
  }
}
