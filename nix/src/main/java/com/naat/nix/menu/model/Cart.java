package com.naat.nix.menu.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import lombok.Data;

/**
 * Representación del carrito en la base de datos
 */
@Data
@Entity
@Table(name="Carrito")
public class Cart {

	@EmbeddedId
	private CartID cartId;

	@ManyToMany
	@JoinTable(
		name="Agregar",
		joinColumns={@JoinColumn(name="email"),
								@JoinColumn(name="id_carrito")},
		inverseJoinColumns=@JoinColumn(name="id_platillo")
	)
	private List<Food> foods;

	public Cart(){		
	}

	public Cart (CartID cartId) {
		this.cartId = cartId;
		this.foods = new ArrayList<Food>();
  }

	public void add(Food p) {
		this.foods.add(p);
	}

	public void delete(Food p){
		this.foods.remove(p);
	}

	public Food deleteByName(String p) {
		for (int i = 0; i < this.foods.size(); i++) {
			Food foo = this.foods.get(i);
			String nombre = foo.getName();
			if (nombre.equals(p)) {
				return foods.remove(i);
			}
		}
		return null;
	}

	public void clear() {
		this.foods.clear();
	}

	public Food getFood(String nombre) {
		for (int i = 0; i < foods.size(); i++) {
			Food f = foods.get(i);
			if (f.getName().equals(nombre)) {
				return f;
			}
		}
		return null;
	}

}
