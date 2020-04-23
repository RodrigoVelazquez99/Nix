package com.naat.nix.user.controller;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

/**
 * Service implementation to use custom users with the default Spring Security
 * configuration.
 */
@Service
public class CustomUserDetailsService implements UserDetailsService{

  public UserDetails loadUserByUsername(String name) {
    return null;
  }
}