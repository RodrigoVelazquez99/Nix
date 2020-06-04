package com.naat.nix.menu.controller;

import java.util.ArrayList;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.naat.nix.order.model.Takeout;
import com.naat.nix.menu.model.Cart;
import com.naat.nix.menu.model.CartFood;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/* Servicio para el manejo de los platillos que se solicitaron */
@Service
public class CartFoodService {

  @Autowired
  private CartFoodRepository repository;

  /**
  * @param cartFood la orden de platillos que se va a guardar.
  */
  public void save (CartFood cartFood) {
    repository.save (cartFood);
  }

  /**
  * @param cartFood la orden de platillos a remover.
  */
  public void remove (CartFood cartFood)  {
    if (repository.existsById(cartFood.getIdCartFood())) {
      repository.delete (cartFood);
    }
  }


  /**
  * Agrega los platillos solicitados a la orden.
  * @param cartFoods los platillos a agregar.
  * @param takeout la nueva orden.
  */
  public void addTakeout (List<CartFood> cartFoods, Takeout takeout) {
    for (CartFood cartFood : cartFoods) {
      cartFood.setTakeout (takeout);
      cartFood.setCart (null);
      repository.save (cartFood);
    }
  }

}
