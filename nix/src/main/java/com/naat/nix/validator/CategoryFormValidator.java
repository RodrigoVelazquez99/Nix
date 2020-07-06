package com.naat.nix.validator;

import com.naat.nix.menu.controller.CategoryService;
import com.naat.nix.menu.model.CategoryForm;
import com.naat.nix.menu.model.Category;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * Validar plantillas para editar categorias.
 */
@Component
public class CategoryFormValidator implements Validator {

    /* Manipulación de categorias */
    @Autowired
    private CategoryService categoryService;

    /**
     * Revisar si la clase dada es la de categorias.
     */
    @Override
    public boolean supports(Class<?> aClass) {
        return CategoryForm.class.equals(aClass);
    }

    /**
     * Revisa si el objeto dado es una plantilla válida, una plantilla
     * es válida si el nuevo nombre no pertenece a otra categoría.
     */
    @Override
    public void validate (Object o, Errors errors) {
        CategoryForm categoryForm = (CategoryForm) o;
        Category other = categoryService.getCategory(categoryForm.getNewCategory());
        if (other != null && !categoryForm.getNewCategory().equals (categoryForm.getOldCategory())) {
          errors.rejectValue("newCategory", "Duplicate.categoryForm.name");
        }
    }
}
