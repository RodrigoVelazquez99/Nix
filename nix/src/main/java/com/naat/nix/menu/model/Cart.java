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

	public boolean contains (Food food ) {
		for (CartFood cartFood : cartFoods) {
			if (cartFood.getFood().equals (food)) {
				return true;
			}
		}
		return false;
	}

  public void addFood(Food p, int cantidad) {
		for (CartFood cartFood : cartFoods) {
			if (cartFood.getFood().equals(p)) {
				cartFood.setAmount(cartFood.getAmount() + cantidad);
				return;
			}
		}
  }

	public void add (CartFood cartFood) {
		this.cartFoods.add(cartFood);
	}


	public void removeFood(Food p){
		for (CartFood cartFood : cartFoods) {
			if (cartFood.getFood().equals (p)) {
				this.cartFoods.remove (cartFood);
				return;
			}
		}
	}

	public void clean () {
		this.cartFoods.clear();
	}

}
