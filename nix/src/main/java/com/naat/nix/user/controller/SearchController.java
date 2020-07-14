package com.naat.nix.user.controller;

import java.util.ArrayList;

import com.naat.nix.menu.model.Food;
import com.naat.nix.menu.model.Category;
import com.naat.nix.menu.controller.FoodService;
import com.naat.nix.menu.controller.CategoryService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * Controlador encargado de la busqueda de platillos.
 */
@Controller
@RequestMapping(value = "/search")
public class SearchController {

  /* El servicio con las operaciones sobre platillos */
  @Autowired
  private FoodService foodService;

  /* El servicio con las operaciones sobre categorias */
  @Autowired
  private CategoryService categoryService;

  /**
  * Obtener los platillos de la busqueda.
  * @param entry el platillo que busca el usuario.
  * @return la vista a mostrar.
  */
  @GetMapping("")
  public ModelAndView getFoods (@RequestParam(value="entrySearch", required=true) String entry) {
    ModelAndView modelAndView = new ModelAndView ("search");
    ArrayList<Food> foods = foodService.getCoincidences(entry);
    modelAndView.addObject ("menu", foods);
    boolean hayPlatillos = (foods.size() == 0)? false : true;
    modelAndView.addObject ("hayPlatillos", hayPlatillos);
    ArrayList<Category> categories = categoryService.getCategories();
    modelAndView.addObject ("categories", categories);
    modelAndView.addObject ("actual", "Resultados de la busqueda : " + entry);
    return modelAndView;
  }

}
