package com.naat.nix.menu.controller;

import java.util.ArrayList;
import javax.validation.Valid;

import com.naat.nix.menu.model.Food;
import com.naat.nix.menu.model.Category;
import com.naat.nix.menu.model.CategoryForm;
import com.naat.nix.validator.CategoryValidator;

import com.naat.nix.user.config.UserWrapper;

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


/**
 * Controlador encargado de la manipulacion de categorias por parte del administrador.
 */
@Controller
@RequestMapping(value = "/categories")
public class CategoryController {

  /* El servicio con las operaciones sobre categorias */
  @Autowired
  private CategoryService categoryService;

  /* Validar las categorias */
  @Autowired
  private CategoryValidator categoryValidator;

  /**
  * Crear una categoria nueva.
  * @param model Modelo actual de la vista.
  * @return Nombre de la vista a mostrar.
  */
  @GetMapping("")
  public String getCategories (Model model) {
    model.addAttribute ("category", new Category());
    ArrayList<Category> categories = categoryService.getCategories();
    model.addAttribute ("categories", categories);
    boolean hayCategorias = (categories.size() == 0)? false : true;
    model.addAttribute ("hayCategorias", hayCategorias);
    return "categories";
  }

  /**
  * Registrar la nueva categoria.
  * @param category la nueva categoria.
  * @return la redirección a la página para ver categorias.
  */
  @PostMapping("")
  public String newCategory (Model model, @ModelAttribute @Valid Category category, BindingResult bindingResult) {
    /* Validamos si la categoria es única */
    categoryValidator.validate (category, bindingResult);
    if (bindingResult.hasErrors()) {
      /* Si no es unica regresa la misma página y con un mensaje indicando el error */
      ArrayList<Category> categories = categoryService.getCategories();
      model.addAttribute ("categories", categories);
      boolean hayCategorias = (categories.size() == 0)? false : true;
      model.addAttribute ("hayCategorias", hayCategorias);
      return "categories";
    }
    categoryService.save (category);
    return "redirect:/categories";
  }

  /**
  * Se quiere observar la página para editar las categorias.
  */
  @GetMapping("/edit")
  public ModelAndView editCategories() {
    ModelAndView modelAndView = new ModelAndView ("categories_edit");
    ArrayList<Category> categories = categoryService.getCategories();
    modelAndView.addObject ("categories", categories);
    boolean hayCategorias = (categories.size() == 0)? false : true;
    modelAndView.addObject ("hayCategorias", hayCategorias);
    modelAndView.addObject ("categoryForm", new CategoryForm());
    return modelAndView;
  }

  /**
  * Se guarda la edición de la categoría.
  */
  @PostMapping("/edit")
  public String saveCategory (@ModelAttribute CategoryForm categoryForm) {
    categoryService.update (categoryForm);
    return "redirect:/categories/edit";
  }

  /**
  * Elimina la categoria identificada con un nombre.
  * @param name el nombre de la categoria a eliminar.
  * @return redirige a la página para editar categorias.
  */
  @GetMapping("edit/delete/{name}")
  public String deleteCategory (@PathVariable("name") String name ) {
    categoryService.delete (name);
    return "redirect:/categories/edit";
  }


}
