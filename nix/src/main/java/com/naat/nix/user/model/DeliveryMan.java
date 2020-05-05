package com.naat.nix.user.model;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@NoArgsConstructor
@RequiredArgsConstructor
@Entity
@Table(name = "Repartidor")
public class DeliveryMan implements Serializable {

  private static final long serialVersionUID = 1L;

  @Id
  @Column(name = "correo")
  private String email;

  @OneToOne(cascade = CascadeType.ALL)
  @PrimaryKeyJoinColumn
  @NotFound(action=NotFoundAction.IGNORE)
  private User user;
}
