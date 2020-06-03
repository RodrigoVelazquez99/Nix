package com.naat.nix.menu.controller;

import com.naat.nix.menu.model.Category;
import org.springframework.stereotype.Repository;
import org.springframework.data.repository.CrudRepository;

/**
 * DAO para las categorías de comida
 */
@Repository
public interface CategoryRepository extends CrudRepository<Category, String>{}
