package com.naat.nix.user.model;

import com.naat.nix.user.model.Admin;
import com.naat.nix.user.model.Client;
import com.naat.nix.user.model.DeliveryMan;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
import javax.persistence.Transient;
import javax.persistence.OneToOne;
import javax.persistence.Column;
import javax.persistence.Table;
import javax.persistence.Entity;
import javax.persistence.Id;
import lombok.Data;
import java.util.List;

@Data
@Entity
@Table(name = "Usuario")
public class User {

  @Id
  @Column(name = "correo")
  private String email;

  @Column(name = "nombre")
  private String username;

  @Column(name = "contraseña")
  private String password;

  @Transient
  private String passwordConfirm;

  @OneToOne(mappedBy = "user")
  @NotFound(action=NotFoundAction.IGNORE)
  private Admin admin;

  @OneToOne(mappedBy = "user")
  @NotFound(action=NotFoundAction.IGNORE)
  private Client client;

  @OneToOne(mappedBy = "user")
  @NotFound(action=NotFoundAction.IGNORE)
  private DeliveryMan deliveryMan;


}
