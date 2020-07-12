package com.naat.nix.menu.controller;

import java.util.ArrayList;
import javax.validation.Valid;

import com.naat.nix.menu.model.Food;
import com.naat.nix.menu.model.Category;
import com.naat.nix.menu.model.FoodForm;
import com.naat.nix.menu.controller.FoodService;
import com.naat.nix.menu.controller.CategoryService;
import com.naat.nix.validator.FoodFormValidator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.ModelAndView;

import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
/**
 * Controlador encargado de la manipulacion de platillos por parte del administrador.
 */
@Controller
@RequestMapping(value = "/foods")
public class FoodController {

  /* El servicio con las operaciones sobre platillos */
  @Autowired
  private FoodService foodService;

  /* El servicio con las operaciones sobre categorias */
  @Autowired
  private CategoryService categoryService;

  /* El validador para crear y editar platillos */
  @Autowired
  private FoodFormValidator foodFormValidator;

  /**
  * Obtener los platillos y crear un nuevo platillo.
  * @param model Modelo actual de la vista.
  * @return Nombre de la vista a mostrar.
  */
  @GetMapping("")
  public String getFoods (Model model) {
    model.addAttribute ("foodForm", new FoodForm());
    ArrayList<Food> foods = foodService.getFoods();
    model.addAttribute ("foods", foods);
    boolean hayPlatillos = (foods.size() == 0)? false : true;
    model.addAttribute ("hayPlatillos", hayPlatillos);
    ArrayList<Category> categories = categoryService.getCategories();
    model.addAttribute ("categories", categories);
    boolean hayCategorias = (categories.size() == 0)? false : true;
    model.addAttribute ("hayCategorias", hayCategorias);
    return "foods";
  }

  /**
  * Registrar el nuevo platillo.
  * @param model Modelo de la vista actual.
  * @param food el nuevo platillo a agregar.
  * @param bindingResult los errores que puede tener.
  */
  @PostMapping("")
  public String newFood (Model model, @ModelAttribute @Valid FoodForm food, BindingResult bindingResult) {
    /* Validamos si el platillo es único */
    foodFormValidator.validate (food, bindingResult);
    if (bindingResult.hasErrors ()) {
      /* Si no es única regresa la misma página y con un mensaje de error */
      ArrayList<Food> foods = foodService.getFoods();
      model.addAttribute ("foods", foods);
      boolean hayPlatillos = (foods.size() == 0)? false : true;
      model.addAttribute ("hayPlatillos", hayPlatillos);
      ArrayList<Category> categories = categoryService.getCategories();
      model.addAttribute ("categories", categories);
      boolean hayCategorias = (categories.size() == 0)? false : true;
      model.addAttribute ("hayCategorias", hayCategorias);
      return "foods";
    }
    foodService.save (food);
    return "redirect:/foods";
  }

  /**
  * Se quiere observar la página para editar las categorías.
  * @return el modelo para la nueva vista.
  */
  @GetMapping("/edit")
  public ModelAndView editCategories() {
    ModelAndView modelAndView = new ModelAndView ("foods_edit");
    ArrayList<Food> foods = foodService.getFoods();
    modelAndView.addObject ("foods", foods);
    boolean hayPlatillos = (foods.size() == 0)? false : true;
    modelAndView.addObject ("hayPlatillos", hayPlatillos);
    modelAndView.addObject ("foodForm", new FoodForm());
    ArrayList<Category> categories = categoryService.getCategories();
    modelAndView.addObject ("categories", categories);
    return modelAndView;
  }

  /**
  * Se guarda la edición del platillo.
  * @param model Modelo de la vista.
  * @param foodForm Plantilla con los datos para editar.
  * @param bindingResult los errores que puede tener.
  */
  @PostMapping("/edit")
  public String saveFood (Model model, @ModelAttribute @Valid FoodForm foodForm, BindingResult bindingResult) {
    /* Validamos si la plantilla es válida */
    foodFormValidator.validate (foodForm, bindingResult);
    if (bindingResult.hasErrors ()) {
      /* Si no es única la nueva categpría, regresa la misma página */
      ArrayList<Food> foods = foodService.getFoods ();
      model.addAttribute ("foods", foods);
      boolean hayPlatillos = (foods.size() == 0)? false : true;
      model.addAttribute ("hayPlatillos", hayPlatillos);
      ArrayList<Category> categories = categoryService.getCategories();
      model.addAttribute ("categories", categories);
      return "foods_edit";
    }
    foodService.update (foodForm);
    return "redirect:/foods/edit";
  }

  /**
  * Elimina el platillo identificado con el nombre.
  * @param name el nombre del platillo a eliminar.
  * @return redirige a la página para editar categorias.
  */
  @GetMapping("/edit/delete/{name}")
  public String deleteFood (@PathVariable("name") String name) {
    foodService.delete (name);
    return "redirect:/foods/edit/";
  }

}
