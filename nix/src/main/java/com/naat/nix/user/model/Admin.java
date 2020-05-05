package com.naat.nix.user.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import lombok.Data;
import lombok.NonNull;

@Data
@Entity
@Table(name = "Administrador")
public class Admin {
  @Id
  private String email;

  @OneToOne(cascade = CascadeType.ALL)
  @NonNull
  @PrimaryKeyJoinColumn
  @NotFound(action=NotFoundAction.IGNORE)
  private User user;
}
