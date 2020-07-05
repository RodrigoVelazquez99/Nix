package com.naat.nix.validator;

import com.naat.nix.menu.controller.CategoryService;
import com.naat.nix.menu.model.Category;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * Validar categorias.
 */
@Component
public class CategoryValidator implements Validator {

    /* Manipulación de categorias */
    @Autowired
    private CategoryService categoryService;

    /**
     * Revisar si la clase dada es la de categorias.
     */
    @Override
    public boolean supports(Class<?> aClass) {
        return Category.class.equals(aClass);
    }

    /**
     * Revisa si el objeto dado es una categoria valida.
     */
    @Override
    public void validate (Object o, Errors errors) {
        Category category = (Category) o;
        if (categoryService.getCategory(category.getCategory()) != null) {
          errors.rejectValue("category", "Duplicate.category.name");
        }
    }
}
