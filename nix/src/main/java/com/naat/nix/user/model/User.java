package com.naat.nix.user.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import org.springframework.security.core.userdetails.UserDetails;

import lombok.Data;

/**
 * Entity representing users of our system. Extends UserDetails to use with
 * Spring Security.
 */
@Data
@Entity
public class User {
  @Id
  private String email;

  private String password;

  @OneToOne(mappedBy = "user")
  private Admin admin;

  @OneToOne(mappedBy = "user")
  private Client client;

  @OneToOne(mappedBy = "user")
  private DeliveryMan deliveryMan;
}