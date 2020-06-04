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

@Service
public class CartFoodService {

  @Autowired
  private CartFoodRepository repository;

  public void guardar (CartFood cartFood) {
    repository.save (cartFood);
  }

  public void eliminar (CartFood cartFood)  {
    if (repository.existsById(cartFood.getIdCartFood())) {
      repository.delete (cartFood);
    }
  }

  public void agregaOrden (List<CartFood> cartFoods, Takeout takeout) {
    for (CartFood cartFood : cartFoods) {
      cartFood.setTakeout (takeout);
      cartFood.setCart (null);
      repository.save (cartFood);
    }
  }

}
