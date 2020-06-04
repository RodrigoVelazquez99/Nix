package com.naat.nix.menu.controller;

import java.sql.SQLIntegrityConstraintViolationException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.naat.nix.menu.model.Cart;
import com.naat.nix.menu.model.CartFood;
import com.naat.nix.menu.controller.CartFoodService;
import com.naat.nix.menu.model.CartID;
import com.naat.nix.menu.model.Food;
import com.naat.nix.order.controller.TakeoutService;
import com.naat.nix.order.model.Takeout;
import com.naat.nix.user.model.Client;
import com.naat.nix.user.controller.ClientService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Manipulaciones de los carritos
 */
@Service
public class CartService {

  /* Manejador de entidades para crear consultas en SQL puro */
  @PersistenceContext
  EntityManager entityManager;

  /* DAO para los carritos */
  @Autowired
  private CartRepository repository;

  @Autowired
  private CartFoodService cartFoodService;

  /* Manejo de órdenes */
	@Autowired
	TakeoutService takeoutService;

  @Autowired
  ClientService clientService;

  /**
   * Obtiene todos los carritos existentes
   * @return Lista de todos los carritos
   */
  public ArrayList<Cart> getCarts(){
    ArrayList<Cart> carritos = (ArrayList<Cart>) repository.findAll();
    return carritos;
  }

  /**
   * Obtiene el carrito por su identificador
   * @param id Identificador del carritoc (como credencial embedida)
   * @return Carrito con el identificador proporcionado
   */
  public Cart getCartById(CartID id) {
    Optional<Cart> cart = repository.findById(id);
    if (cart.isPresent()) {
      return cart.get();
    }
    return null;
  }

  /**
   * Obtiene el carrito por el corree del usuario
   * @param correo_usuario Correo del usuario
   * @return Carrito del usuario con el correo proporcionado
   */
  public Cart getCartByEmail(String correo_usuario) {
    Query query = entityManager.createQuery("FROM Cart c WHERE c.cartId.email =: correo", Cart.class);
    query.setParameter("correo", correo_usuario);
    ArrayList<Cart> carts = (ArrayList<Cart>) query.getResultList();
    if (carts.size() == 0) {
      return null;
    }
    return carts.get(0);
  }

  /**
   * Guarda el carrito en la base de datos
   * @param c el carrito a guardar
   * @return Carrito que se acaba de guardar
   */
  public Cart save(Cart c) {
    Cart n = repository.save(c);
    return n;
  }

  /**
   * Elimina el carrito si se encuentra en la base de datos
   * @param c el carrito a elimnar
   */

  public void delete(Cart c) {
    Optional<Cart> carrito = repository.findById(c.getCartId());
    if (carrito.isPresent()) {
      repository.deleteById(c.getCartId());
    }
  }

  /**
  * Si el carrito se encuentra en la base de datos, lo actualiza
  * @param c el carrito que se actualiza
  */
  public void update(Cart c)  {
    Optional<Cart> carrito = repository.findById(c.getCartId());
    Cart a = null;
    if (carrito.isPresent()) {
      a = carrito.get();

      for (CartFood acf : a.getCartFoods()) {
        cartFoodService.remove (acf);
      }

      for (CartFood cf : c.getCartFoods()) {
        cartFoodService.save (cf);
      }

      a.setCartId (c.getCartId());
      a.setCartFoods(c.getCartFoods());
      repository.save(a);
    }
  }

  /**
   * Crea una orden con los contenidos de carrito
   * @param c Carrito con los platillos
   */
  public void order(Cart c, Client client) {
    var cl = clientService.findByEmail (client.getEmail());
    var foods = c.getCartFoods();
		var price = totalPrice(c);
		var order = new Takeout();
		order.setFoodItems(foods);
		order.setDeliveryDate(LocalDate.now());
		order.setPrice(price);
    order.setClient(cl);
    // siempre elegir primera dirección
		order.setAddress(cl.getAddress().get(0));
		takeoutService.save(order);
    cartFoodService.addTakeout(foods, order);
    c.clean();
    save (c);
  }

  /**
	 * Calcula el precio total de los contenidos del carrito actual
	 * @return Precio total
	 */
	public double totalPrice(Cart c) {
    double total = 0;
    for (CartFood f : c.getCartFoods()) {
        total += f.getFood().getPrice() * f.getAmount();
    }
    return total;
}

}
