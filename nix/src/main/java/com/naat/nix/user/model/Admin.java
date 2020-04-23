package com.naat.nix.user.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import lombok.Data;

@Data
@Entity
public class Admin {
  @Id
  @OneToOne
  @JoinColumn(name = "user_email")
  private User user;
}