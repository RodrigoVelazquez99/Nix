package com.naat.nix.menu.controller;

import java.util.ArrayList;

import com.naat.nix.menu.model.Food;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
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

  /**
   * Solicitud para ver el menu
   * @return Vista y atributos del menú
   **/
  @GetMapping
  public ModelAndView getMenu() {
    ModelAndView modelAndView = new ModelAndView("menu");
    ArrayList<Food> platillos = new ArrayList<Food>(foodService.getFoods());
    modelAndView.addObject("menu", platillos);
    return modelAndView;
  }
}
