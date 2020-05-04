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

import java.util.List;

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

  public User(){
  }

  public String getEmail() {
    return this.email;
  }

  public void setEmail (String email){
    this.email = email;
  }

  public String getUsername () {
    return this.username;
  }

  public void setUsername (String username) {
    this.username = username;
  }

  public String getPassword (){
    return this.password;
  }

  public void setPassword (String password) {
    this.password = password;
  }

  public String getPasswordConfirm (){
    return this.passwordConfirm;
  }

  public void setPasswordConfirm (String passwordConfirm) {
    this.passwordConfirm = passwordConfirm;
  }

  public Admin getAdmin (){
    return this.admin;
  }

  public void setAdmin (Admin admin) {
    this.admin = admin;
  }

  public Client getClient () {
    return this.client;
  }

  public void setClient (Client client) {
    this.client = client;
  }

  public DeliveryMan getDeliveryMan (){
    return this.deliveryMan;
  }

  public void setDeliveryMan (DeliveryMan deliveryMan) {
    this.deliveryMan = deliveryMan;
  }

}
