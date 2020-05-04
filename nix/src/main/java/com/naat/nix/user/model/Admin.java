package com.naat.nix.user.model;

import com.naat.nix.user.model.User;

import java.io.Serializable;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
import javax.persistence.CascadeType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import lombok.Data;

@Data
@Entity
@Table(name = "Administrador")
public class Admin implements Serializable {

  @Id
  @Column(name = "correo")
  private String email;

  @OneToOne(cascade = CascadeType.ALL)
  @PrimaryKeyJoinColumn
  @NotFound(action=NotFoundAction.IGNORE)
  private User user;

  public Admin (){
  }

  public String getEmail () {
    return this.email;
  }

  public void setEmail (String email) {
    this.email = email;
  }

  public User getUser () {
    return this.user;
  }

  public void setUser (User user) {
    this.user = user;
  }

}
