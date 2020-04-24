package com.naat.nix.menu.model;

import com.naat.nix.menu.model.Food;
import com.naat.nix.menu.model.CartID;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EmbeddedId;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Carrito")
public class Cart {

	@EmbeddedId
	private CartID cartId;

	@ManyToMany
	@JoinTable(
		name="Agregar",
		joinColumns={@JoinColumn(name="correo"),
								 @JoinColumn(name="id_carrito")},
		inverseJoinColumns=@JoinColumn(name="id_platillo")
	)
	private List<Food> platillos;

	public Cart () {
	}

	public Cart (CartID cartId) {
	  	this.cartId = cartId;
	  	this.platillos = new ArrayList<Food>();
  }

	public CartID getCartId() {
		return this.cartId;
	}

	public void setCartId(CartID cartId){
		this.cartId = cartId;
	}

  	public List<Food> getPlatillos() {
	    return this.platillos;
  	}

	public void setPlatillos(List<Food> p) {
		this.platillos = p;
	}

  	public void agregar(Food p) {
	  	this.platillos.add(p);
  	}

	public void eliminar(Food p){
	    this.platillos.remove(p);
	}

	public Food eliminar(String p) {
		for (int i = 0; i < this.platillos.size(); i++) {
			Food foo = this.platillos.get(i);
			String nombre = foo.getNombre();
			if (nombre.equals(p)) {
				return platillos.remove(i);
			}
		}
		return null;
	}

	public void limpiar() {
		this.platillos.clear();
	}

	public Food getFood(String nombre) {
		for (int i = 0; i < platillos.size(); i++) {
			Food f = platillos.get(i);
			if (f.getNombre().equals(nombre)) {
				return f;
			}
		}
		return null;
	}

	public List<Food> copiaPlatillos() {
		ArrayList<Food> c = new ArrayList<Food>();
		for (int i = 0; i < this.platillos.size() ;i++) {
			Food f = new Food(this.platillos.get(i));
			c.add(f);
		}
		return c;
	}

}
