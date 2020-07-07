package com.naat.nix.menu.controller;

import java.util.ArrayList;
import java.util.Optional;

import com.naat.nix.menu.model.Category;
import com.naat.nix.menu.model.Food;
import com.naat.nix.menu.model.CategoryForm;
import com.naat.nix.menu.controller.FoodService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/* Servicio para el manejo de operaciones sobre categorias */
@Service
public class CategoryService {

  /* DAO para las operaciones sobre categorias */
  @Autowired
  CategoryRepository repository;

  /* DAO para las operaciones sobre plaitllos */
  @Autowired
  FoodService foodService;

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

  /**
  * Obtiene una categoria mediante su identificador.
  * @param category el nombre de la categoria.
  */
  public Category getCategory (String category) {
    Optional<Category> op = repository.findById (category);
    if (op.isPresent()) {
      return op.get();
    }
    return null;
  }

  /**
  * A partir de la forma actualiza la categoria con su nuevo nombre.
  * @param categoryForm la forma que contiene los datos del nombre anterior
  * y el nuevo nombre de la categoria.
  */
  public void update (CategoryForm categoryForm) {
    if (categoryForm.getNewCategory().equals (categoryForm.getOldCategory())) {
      return;
    }
    Category old = getCategory (categoryForm.getOldCategory ());
    repository.delete (old);
    Category nw = new Category();
    nw.setCategory (categoryForm.getNewCategory ());
    repository.save (nw);
  }

  /**
  * Elimina una categoria dado su nombre y a todos los platillos
  * que pertenecen a esa categoria clasificalos en la categoria por defecto "Sin categoria".
  * @param name el nombre de la categoria a eliminar.
  */
  public void delete (String name) {
    Category d = getCategory (name);
    Category defaultCategory = getCategory ("Sin categoría");
    ArrayList<Food> foods = new ArrayList<Food> (d.getFoods());
    for (Food food : foods) {
      food.setCategory (defaultCategory);
      foodService.update(food);
    }
    d.setFoods(new ArrayList<Food>());
    repository.delete (d);
  }

}
