package com.server.domains.responses;

import com.server.domains.enums.*;
import lombok.Builder;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
@Builder
public class EventResponse {
  private Integer id;
  private Date createdAt;
  private Date updatedAt;
  private Integer createdBy;
  private Integer ownedBy;
  private String name;
  private String description;
  private Integer venueId;
  private Integer addressId;
  private Lang lang;
  private EventType eventType;
  private String contact;
  private AttendanceType attendanceType;
  private Date startDate;
  private Date endDate;
  private boolean live;
  private Integer credentialsId;
  private Integer limitNumber;
  private Double ticketPrice;
  private Currency ticketCurrency;
  private Date editLimit;
  private Date sellLimit;
  private Date userEditLimit;
  private boolean notification;
  private List<Integer> otherDatesIds;
  private List<Integer> discountCodesIds;
  private Date cancellation;
  private Date cancellationLimit;
  private EventState state;
  private String refundsPolicy;
  private List<Integer> tagIds;
  private List<Integer> faqIds;
  private List<Integer> menuIds;
  private List<Integer> eventUserIds;
}
