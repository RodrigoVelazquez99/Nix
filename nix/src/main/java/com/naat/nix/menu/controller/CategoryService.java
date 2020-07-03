package com.naat.nix.menu.controller;

import java.util.ArrayList;
import java.util.Optional;

import com.naat.nix.menu.model.Category;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/* Servicio para el manejo de operaciones sobre categorias */
@Service
public class CategoryService {

  /* DAO para las operaciones sobre categorias */
  @Autowired
  CategoryRepository repository;

  /**
  * Obtiene todas las categorias existentes.
  * @return todas las categorias.
  */
  public ArrayList<Category> getCategories () {
    ArrayList<Category> categories = (ArrayList<Category>) repository.findAll();
    return categories;
  }

  /**
  * Guarda la nueva categoria.
  * @param category la nueva categoria. 
  */
  public void save (Category category) {
    repository.save (category);
  }

}
