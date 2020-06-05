package com.naat.nix.menu.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.naat.nix.order.model.Takeout;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
* Clase que representa a la solicitud de un platillo con la cantidad del mismo.
*/
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="CartFood")
public class CartFood {

    /* Llave primaria */
    @Id
    @GeneratedValue
    private int idCartFood;

    /* El platillo solicitado */
    @ManyToOne
    @JoinColumn(name="food_id")
    private Food food;

    /* El carrito que contiene al platillo */
    @ManyToOne
    @JoinColumns ({
    @JoinColumn(name="email"),
    @JoinColumn(name="cart_id")})
    private Cart cart;

    /* La cantidad de articulos solicitados del platillo */
    @Column(name = "amount")
    private int amount;

    /* La orden que contiene a este platillo solicitado */
    @ManyToOne
    @JoinColumn( name="takeout")
    private Takeout takeout;
}
