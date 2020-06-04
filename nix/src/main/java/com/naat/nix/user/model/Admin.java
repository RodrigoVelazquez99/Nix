package com.naat.nix.user.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

/**
 * Representación de administrador en la base de datos
 */
@Data
@NoArgsConstructor
@RequiredArgsConstructor
@Entity
@Table(name = "Administrador")
public class Admin {
  /* El correo del administrador */
  @Id
  private String email;

  /* La referencia al usuario */
  @OneToOne(cascade = CascadeType.ALL)
  @NonNull
  @NotFound(action=NotFoundAction.IGNORE)
  @MapsId
  private User user;
}
