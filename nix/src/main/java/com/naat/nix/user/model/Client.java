package com.naat.nix.user.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import com.naat.nix.order.model.Takeout;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import lombok.Data;

/**
 * Representación de clientes en la base de datos
 */
@Data
@Entity
@Table(name = "Cliente")
public class Client implements Serializable {

  private static final long serialVersionUID = 1L;
  /* El correo del cliente */
  @Id
  @Column(name = "correo")
  private String email;

  /* La referencia al usuario */
  @OneToOne(cascade = CascadeType.ALL)
  @PrimaryKeyJoinColumn
  @NotFound(action=NotFoundAction.IGNORE)
  private User user;

  /* El telefono del cliente */
  @Column(name="telefono")
  private String phone;

  /* La calificación que recibe el negocio */
  @Column(name="calificacion")
  private int score;

  /* Las direcciones del cliente */
  @ElementCollection
  @CollectionTable(
    name = "Cliente_Direccion",
    joinColumns = @JoinColumn(name = "correo")
  )
  private List<String> address;

  /* Las ordenes que ha hecho */
  @OneToMany(mappedBy = "client")
  private List<Takeout> orders;
}
