package com.naat.nix.menu.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;

/**
 * Representación del carrito en la base de datos
 */
@Data
@Entity
@Table(name="Carrito")
public class Cart {

	/**
	* Llave compuesta por el correo del cliente y
	* el identificador del carrito.
	*/
	@EmbeddedId
	private CartID cartId;

	/* Solicitudes de platillos que se han hecho */
	@OneToMany(mappedBy = "cart")
	private List<CartFood> cartFoods;


	public Cart(){
	}

	public Cart (CartID cartId) {
	  	this.cartId = cartId;
	  	this.cartFoods = new ArrayList<CartFood>();
  }

	/**
	* Revisa si el alimento esta en la lista de platillos solicitados.
	* @param food el alimento a revisar.
	* @return true si esta en la lista, false en otro caso.
	*/
	public boolean contains (Food food ) {
		for (CartFood cartFood : cartFoods) {
			if (cartFood.getFood().equals (food)) {
				return true;
			}
		}
		return false;
	}

	/**
	* Agrega la cantidad de platillos de un alimento
	* que ya se encuentra solicitado.
	* @param food el alimento solicitado.
	* @param cantidad la cantidad de articulos del mismo.
	*/
  public void addFood(Food food, int cantidad) {
		for (CartFood cartFood : cartFoods) {
			if (cartFood.getFood().equals(food)) {
				cartFood.setAmount(cartFood.getAmount() + cantidad);
				return;
			}
		}
  }

	/**
	* Agrega la solicitud de un platillo.
	* @param cartFood la solictud a agregar.
	*/
	public void addFoodCart (CartFood cartFood) {
		this.cartFoods.add(cartFood);
	}


	/**
	* Remueve la solicitud de platillo de un alimento.
	* @param food el alimento del cual queremos borrar su solicitud.
	*/
	public void removeFood(Food food){
		for (CartFood cartFood : cartFoods) {
			if (cartFood.getFood().equals (food)) {
				this.cartFoods.remove (cartFood);
				return;
			}
		}
	}

	/**
	* Remueve las solicitudes de platillos del carrito.
	*/
	public void clean () {
		this.cartFoods.clear();
	}

}
