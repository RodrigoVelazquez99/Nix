package com.naat.nix.user.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

/**
 * Representación de usuarios en la base de datos.
 */
@Data
@Entity
@NoArgsConstructor
@RequiredArgsConstructor
@Table(name = "Usuario")
public class User {

  /* El correo del usuario */
  @Id
  @NonNull
  @Column(name = "correo")
  private String email;

  /* El nombre del usuario */
  @Column(name = "nombre")
  private String username;

  /* El password del usuario */
  @NonNull
  @Column(name = "contraseña")
  private String password;

  /* La referencia al admin, si es admin */
  @OneToOne(mappedBy = "user")
  @NotFound(action=NotFoundAction.IGNORE)
  private Admin admin;

  /* La referencia al cliente, si es cliente */
  @OneToOne(mappedBy = "user")
  @NotFound(action=NotFoundAction.IGNORE)
  private Client client;

  /* La referencia al repartidor, si es repartidor */
  @OneToOne(mappedBy = "user")
  @NotFound(action=NotFoundAction.IGNORE)
  private DeliveryMan deliveryMan;
}
