package com.server.domains;

import java.util.Date;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.server.domains.enums.AttendanceType;
import com.server.domains.enums.Currency;
import com.server.domains.enums.EventState;
import com.server.domains.enums.EventType;
import com.server.domains.enums.Lang;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "event")
public class Event {

  @Id
  @GeneratedValue()
  private Integer id;

  @CreationTimestamp
  @Temporal(TemporalType.TIMESTAMP)
  private Date createdAt;

  @UpdateTimestamp
  @Temporal(TemporalType.TIMESTAMP)
  private Date updatedAt;

  @ManyToOne
  @JoinColumn(name = "created_by")
  private User createdBy;

  @ManyToOne
  @JoinColumn(name = "owned_by")
  private User ownedBy;

  private String name;
  private String description;

  @ManyToOne
  @JoinColumn(name = "venue_id")
  private Venue venue;

  @ManyToOne
  @JoinColumn(name = "address_id")
  private Address address;

  private Lang lang;

  @Enumerated(EnumType.STRING)
  private EventType eventType;
  
  private String contact;

  @Enumerated(EnumType.STRING)
  private AttendanceType attendanceType;

  private Date startDate;
  private Date endDate;
  private boolean live;

  @OneToOne(mappedBy = "event", cascade = CascadeType.ALL, orphanRemoval = true)
  private Credentials credentials;

  private Integer limitNumber;
  private Double ticketPrice;
  private Currency ticketCurrency;
  private Date editLimit;
  private Date sellLimit;
  private Date userEditLimit;
  private boolean notification;
  private Integer pageViews;

  @OneToMany(mappedBy = "event", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<CDate> otherDates;

  @OneToMany(mappedBy = "event", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<DiscountCode> discountCodes;

  private Date cancellation;
  private Date cancellationLimit;

  @Enumerated(EnumType.STRING)
  private EventState state;
  
  private String refundsPolicy;

  @ManyToMany
  @JoinTable(
    name = "event_tag",
    joinColumns = @JoinColumn(name = "event_id"),
    inverseJoinColumns = @JoinColumn(name = "tag_id")
  )
  private List<Tag> tags;
  
  @OneToMany(mappedBy = "event", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<EventFaq> faqs;

  @OneToMany(mappedBy = "event", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<EventMenu> menus;

  @OneToMany(mappedBy = "event", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<EventUser> eventUsers;
  
  @OneToMany(mappedBy = "event", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<Comment> comments;
}
