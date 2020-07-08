package com.naat.nix.menu.controller;

import com.naat.nix.menu.model.Food;
import com.naat.nix.menu.model.Category;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * DAO para los platillos
 */
@Repository
public interface FoodRepository extends CrudRepository<Food, Integer>{
  List<Food> findByCategory (Category category);
  Food findByName (String name);
}
