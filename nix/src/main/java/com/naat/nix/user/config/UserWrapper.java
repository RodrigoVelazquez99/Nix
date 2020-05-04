package com.naat.nix.user.config;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import lombok.Getter;
import lombok.Setter;


public class UserWrapper extends User {
  private static final long serialVersionUID = 1L;

  private com.naat.nix.user.model.User customUser;

  public UserWrapper(com.naat.nix.user.model.User user, String username,
  String password, Collection<? extends GrantedAuthority> authorities) {
    super(username, password, authorities);
    this.customUser = user;
  }

  public com.naat.nix.user.model.User getCustomUser(){
    return this.customUser;
  }

  public void setCustomUser (com.naat.nix.user.model.User user) {
    this.customUser = user;
  }


}
