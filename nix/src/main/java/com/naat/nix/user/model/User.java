package com.naat.nix.user.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

/**
 * Entity representing users of our system. Extends UserDetails to use with
 * Spring Security.
 */
@Data
@NoArgsConstructor
@RequiredArgsConstructor
@Entity
public class User {
  @Id
  @NonNull
  private String email;

  @NonNull
  private String password;

  @OneToOne(mappedBy = "user")
  private Admin admin;

  @OneToOne(mappedBy = "user")
  private Client client;

  @OneToOne(mappedBy = "user")
  private DeliveryMan deliveryMan;
}