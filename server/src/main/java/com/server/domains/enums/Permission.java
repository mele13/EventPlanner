package com.server.domains.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Permission {

  ADMIN_READ("admin:read"),
  ADMIN_UPDATE("admin:update"),
  ADMIN_CREATE("admin:create"),
  ADMIN_DELETE("admin:delete"),

  EVENT_READ("event:read"),
  EVENT_UPDATE("event:update"),
  EVENT_CREATE("event:create"),
  EVENT_DELETE("event:delete"),
  EVENT_ASSIGN_ROLES("event:assign_roles"),

  USER_READ("user:read"),
  USER_UPDATE("user:update"),
  USER_UPDATE_OWN("user:update_own"),

  VENDOR_READ("vendor:read"),
  VENDOR_UPDATE("vendor:update"),
  VENDOR_UPDATE_OWN("vendor:update_own"),
  VENDOR_CREATE("vendor:create"),
  VENDOR_DELETE("vendor:delete"),
  
  FINANCE_READ("finance:read"),
  ADMINISTRATION_READ("administration:read"),
  ADMINISTRATION_UPDATE("administration:update");
  
  private final String permission;
}
