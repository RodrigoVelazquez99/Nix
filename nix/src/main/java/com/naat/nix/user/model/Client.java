package com.naat.nix.user.model;

import java.io.Serializable;
import javax.persistence.PrimaryKeyJoinColumn;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
import javax.persistence.CascadeType;
import javax.persistence.JoinColumn;
import javax.persistence.CollectionTable;
import javax.persistence.ElementCollection;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import java.util.List;

import lombok.Data;

@Data
@Entity
@Table(name = "Cliente")
public class Client implements Serializable {

  @Id
  @Column(name = "correo")
  private String email;

  @OneToOne(cascade = CascadeType.ALL)
  @PrimaryKeyJoinColumn
  @NotFound(action=NotFoundAction.IGNORE)
  private User user;

  @Column(name="telefono")
  private String phone;

  @Column(name="calificacion")
  private int score;

  @ElementCollection
  @CollectionTable(
    name = "Cliente_Direccion",
    joinColumns = @JoinColumn(name = "correo")
  )
  private List<String> address;


}
