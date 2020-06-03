package com.naat.nix.menu.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.CascadeType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.naat.nix.menu.model.Food;
import com.naat.nix.menu.model.Cart;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="CartFood")
public class CartFood {

    @Id
    @GeneratedValue
    private int idCartFood;

    @ManyToOne(cascade= CascadeType.ALL)
    @JoinColumn(name="food_id")
    private Food food;

    @ManyToOne(cascade= CascadeType.ALL)
    @JoinColumns ({
    @JoinColumn(name="email"),
    @JoinColumn(name="cart_id")})
    private Cart cart;

    @Column(name = "amount")
    private int amount;

    @Override
    public boolean equals (Object obj) {
      CartFood c = (CartFood) obj;
      if (this.food.equals (c.getFood())
         && this.cart.getCartId().equals(c.getCart().getCartId())) {
           return true;
      }
      return false;
    }
}
