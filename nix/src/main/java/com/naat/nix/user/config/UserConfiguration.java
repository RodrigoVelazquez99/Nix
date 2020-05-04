package com.naat.nix.user.config;

import java.util.ArrayList;

import com.naat.nix.user.controller.UserRepository;
import com.naat.nix.user.model.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * Service implementation to use custom users with the default Spring Security
 * configuration.
 */
@Service
public class UserConfiguration implements UserDetailsService{

  @Autowired
  private UserRepository dao;

  public UserDetails loadUserByUsername(String name) {
    User user = dao.findByUsername(name);
    if (user == null) {
      throw new UsernameNotFoundException(name);
    }
    return buildUser(user);
  }

  private UserWrapper buildUser(User user) {
    String username = user.getEmail();
    String password = user.getPassword();
    var authorities = new ArrayList<SimpleGrantedAuthority>();

    if(user.getAdmin() != null){
      authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
    }

    if(user.getClient() != null){
      authorities.add(new SimpleGrantedAuthority("ROLE_CLIENT"));
    }

    if(user.getDeliveryMan() != null) {
      authorities.add(new SimpleGrantedAuthority("ROLE_DELIVERYMAN"));
    }

    return new UserWrapper(user, username, password, authorities);
  }
}
