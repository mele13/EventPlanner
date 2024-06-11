package com.server.security;

import java.util.Optional;

import org.springframework.data.domain.AuditorAware;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import com.server.domains.User;

public class ApplicationAuditAware implements AuditorAware<Integer> {

  @Override
  public Optional<Integer> getCurrentAuditor() {
    Authentication auth = SecurityContextHolder
                            .getContext()
                            .getAuthentication();
    if (auth == null || auth instanceof AnonymousAuthenticationToken || !auth.isAuthenticated()) {
      return Optional.empty();
    }

    User userPrincipal = (User) auth.getPrincipal();
    return Optional.ofNullable(userPrincipal.getId());
  }
}
