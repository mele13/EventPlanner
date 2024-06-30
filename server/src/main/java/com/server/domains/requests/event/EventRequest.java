package com.server.domains.requests.event;

import java.util.Date;
import java.util.List;

import com.server.domains.enums.AttendanceType;
import com.server.domains.enums.Currency;
import com.server.domains.enums.EventState;
import com.server.domains.enums.EventType;
import com.server.domains.enums.Lang;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EventRequest {
  private Integer id;
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
  private Integer credetialsId;
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
  private String template;
  private List<String> palette;
}
