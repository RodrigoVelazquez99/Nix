package com.naat.nix.menu.controller;

import com.naat.nix.menu.model.CartFood;
import org.springframework.stereotype.Repository;
import org.springframework.data.repository.CrudRepository;

@Repository
public interface CartFoodRepository extends CrudRepository<CartFood, Integer>{}
