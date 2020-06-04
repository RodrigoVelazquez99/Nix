package com.naat.nix.menu.model;

import java.io.Serializable;
import javax.persistence.Embeddable;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import lombok.Data;

/* Representa la llave primaria compuesta del objeto Cart */
@Data
@Embeddable
public class CartID implements Serializable {

	private static final long serialVersionUID = 1L;

	@GeneratedValue
    @Column(name="id_carrito")
    int idCarrito;

    @Column(name="correo")
    String email;

    public CartID() {
    }

    public CartID (int id, String email) {
      this.idCarrito = id;
      this.email = email;
    }

}
