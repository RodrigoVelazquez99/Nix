package com.naat.nix.menu.controller;

import java.util.ArrayList;

import com.naat.nix.menu.model.Food;
import com.naat.nix.menu.model.Category;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

/**
 * Manipulación del menú
 */
@Controller
@RequestMapping(value = "/menu")
public class MenuController {

  /* El servicio para manejar las operaciones sobre los platillos */
  @Autowired
  FoodService foodService;

  /* El servivio para manejar las operaciones sobre categorias */
  @Autowired
  CategoryService categoryService;

  /**
   * Solicitud para ver el menu con todos los platillos.
   * @return Vista y atributos del menú
   **/
  @RequestMapping( value = "/all")
  public ModelAndView getMenu() {
    ModelAndView modelAndView = new ModelAndView("menu");
    ArrayList<Food> platillos = new ArrayList<Food>(foodService.getFoods());
    ArrayList<Category> categories = new ArrayList<>(categoryService.getCategories());
    boolean hayPlatillos = (platillos.size() == 0 )? false : true;
    modelAndView.addObject("hayPlatillos", hayPlatillos);
    modelAndView.addObject("categories", categories);
    modelAndView.addObject("actual", "Todas las categorías");
    modelAndView.addObject("menu", platillos);
    return modelAndView;
  }

  /**
    * Solicitud para obtener los platillos por categoria
    * @param category el nombre de la categoria de la cual queremos obtener sus platillos.
    */
  @RequestMapping( value = "/{category}")
  public ModelAndView getCategory (@PathVariable String category) {
    ModelAndView modelAndView = new ModelAndView ("menu");
    ArrayList<Food> platillos = foodService.getFoodsByCategory(category);
    boolean hayPlatillos = (platillos.size() == 0 )? false : true;
    ArrayList<Category> categories = new ArrayList<>(categoryService.getCategories());
    modelAndView.addObject("hayPlatillos", hayPlatillos);
    modelAndView.addObject("categories", categories);
    modelAndView.addObject("actual", category);
    modelAndView.addObject("menu", platillos);
    return modelAndView;
  }


}
