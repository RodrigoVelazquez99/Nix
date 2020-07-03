package com.naat.nix.menu.controller;

import java.util.ArrayList;

import com.naat.nix.menu.model.Food;
import com.naat.nix.menu.model.Category;

import com.naat.nix.user.config.UserWrapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.ModelAndView;


/**
 * Controlador encargado de la manipulacion de categorias por parte del administrador.
 */
@Controller
@RequestMapping(value = "/categories")
public class CategoryController {

  /* El servicio con las operaciones sobre categorias */
  @Autowired
  CategoryService categoryService;

  /**
  * Crear una categoria nueva.
  * @param model Modelo actual de la vista.
  * @return Nombre de la vista a mostrar.
  */
  @GetMapping("")
  public String getCategories (Model model) {
    model.addAttribute ("category", new Category());
    return "categories";
  }

  /**
  * Registrar la nueva categoria.
  * @param category la nueva categoria.
  * @return la redirección a la página para ver categorias.
  */
  @PostMapping("")
  public String newCategory (@ModelAttribute Category category) {
    categoryService.save (category);
    return "redirect:/categories";
  }

}
