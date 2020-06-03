package com.naat.nix.menu.controller;

import java.util.ArrayList;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.naat.nix.menu.model.Cart;
import com.naat.nix.menu.model.CartFood;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartFoodService {

  @Autowired
  private CartFoodRepository repository;

  public void guardar (CartFood cartFood) {
    repository.save (cartFood);
  }

}
