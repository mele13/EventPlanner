package com.server.domains.enums;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.security.core.authority.SimpleGrantedAuthority;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum Role {

  // App roles
  USER(
    "User",
    Set.of(
      Permission.USER_READ,
      Permission.USER_UPDATE_OWN
    )
  ),
  ADMIN("Admin",
    Set.of(
      Permission.ADMIN_READ,
      Permission.ADMIN_UPDATE,
      Permission.ADMIN_CREATE,
      Permission.ADMIN_DELETE,
      Permission.EVENT_READ,
      Permission.EVENT_UPDATE,
      Permission.EVENT_CREATE,
      Permission.EVENT_DELETE,
      Permission.EVENT_ASSIGN_ROLES,
      Permission.USER_READ,
      Permission.USER_UPDATE,
      Permission.USER_UPDATE_OWN,
      Permission.VENDOR_READ,
      Permission.VENDOR_UPDATE,
      Permission.VENDOR_UPDATE_OWN,
      Permission.VENDOR_CREATE,
      Permission.VENDOR_DELETE
    )
  ),
  VENDOR(
    "Vendor",
    Set.of(
      Permission.USER_READ,
      Permission.USER_UPDATE_OWN,
      Permission.VENDOR_READ,
      Permission.VENDOR_UPDATE_OWN
    )
  ),

  // Event roles
  EVT_OWNER(
    "Event Owner",
    Set.of(
      Permission.EVENT_READ,
      Permission.EVENT_UPDATE,
      Permission.EVENT_CREATE,
      Permission.EVENT_DELETE,
      Permission.EVENT_ASSIGN_ROLES,
      Permission.USER_READ,
      Permission.USER_UPDATE_OWN,
      Permission.VENDOR_READ,
      Permission.FINANCE_READ,
      Permission.ADMINISTRATION_READ,
      Permission.ADMINISTRATION_UPDATE
    )
  ),
  EVT_ORGANIZER(
    "Event Organizer",
    Set.of(
      Permission.EVENT_READ,
      Permission.EVENT_UPDATE,
      Permission.EVENT_CREATE,
      Permission.EVENT_DELETE,
      Permission.EVENT_ASSIGN_ROLES
    )
  ),
  EVT_COLLABORATOR(
    "Event Collaborator",
    Set.of(Permission.EVENT_READ,
      Permission.EVENT_UPDATE
    )
  ),
  
  EVT_USER(
    "Event User",
    Set.of(Permission.EVENT_READ,
      Permission.USER_UPDATE_OWN)),
  EVT_ADMIN(
    "Event Admin",
    Set.of(
      Permission.EVENT_READ,
      Permission.EVENT_UPDATE,
      Permission.EVENT_CREATE,
      Permission.EVENT_DELETE,
      Permission.EVENT_ASSIGN_ROLES,
      Permission.USER_READ,
      Permission.USER_UPDATE_OWN,
      Permission.VENDOR_READ,
      Permission.FINANCE_READ,
      Permission.ADMINISTRATION_READ,
      Permission.ADMINISTRATION_UPDATE
    )
  ),
  EVT_VENDOR(
    "Event Vendor",
    Set.of(Permission.EVENT_READ,
      Permission.USER_UPDATE_OWN,
      Permission.VENDOR_UPDATE_OWN
    )
  );

  @Getter
  private final String displayName;
  
  @Getter
  private final Set<Permission> Permissions;

  public List<SimpleGrantedAuthority> getAuthorities() {
    List<SimpleGrantedAuthority> authorities = getPermissions()
        .stream()
        .map(permission -> new SimpleGrantedAuthority(permission.getPermissions()))
        .collect(Collectors.toList());
    authorities.add(new SimpleGrantedAuthority("ROLE_" + this.name()));
    return authorities;
  }
}
