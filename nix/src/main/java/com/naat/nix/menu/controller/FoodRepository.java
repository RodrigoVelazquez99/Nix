package com.naat.nix.menu.controller;

import com.naat.nix.menu.model.Food;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * DAO para los platillos
 */
@Repository
public interface FoodRepository extends CrudRepository<Food, Integer>{}
