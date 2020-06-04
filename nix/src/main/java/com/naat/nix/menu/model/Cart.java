package com.naat.nix.menu.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.CascadeType;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name="Carrito")
public class Cart {

	@EmbeddedId
	private CartID cartId;

	@OneToMany(mappedBy = "cart")
	private List<CartFood> cartFoods;


	public Cart(){
	}

	public Cart (CartID cartId) {
	  	this.cartId = cartId;
	  	this.cartFoods = new ArrayList<CartFood>();
  }

	public boolean contiene (Food food ) {
		for (CartFood cartFood : cartFoods) {
			if (cartFood.getFood().equals (food)) {
				return true;
			}
		}
		return false;
	}

  public void sumar(Food p) {
		for (CartFood cartFood : cartFoods) {
			if (cartFood.getFood().equals(p)) {
				cartFood.setAmount(cartFood.getAmount() + 1);
				return;
			}
		}
  }

	public void agregar (CartFood cartFood) {
		this.cartFoods.add(cartFood);
	}


	public void eliminar(Food p){
		for (CartFood cartFood : cartFoods) {
			if (cartFood.getFood().equals (p)) {
				this.cartFoods.remove (cartFood);
				return;
			}
		}
	}

	public void limpiar() {
		this.cartFoods.clear();
	}

}
