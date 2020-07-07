package com.naat.nix.validator;

import com.naat.nix.menu.controller.FoodService;
import com.naat.nix.menu.model.FoodForm;
import com.naat.nix.menu.model.Food;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * Validar plantillas para editar platillos.
 */
@Component
public class FoodFormValidator implements Validator {

    /* Manipulación de platillos */
    @Autowired
    private FoodService foodService;

    /**
     * Revisar si la clase dada es la de platillos.
     */
    @Override
    public boolean supports(Class<?> aClass) {
        return FoodForm.class.equals(aClass);
    }

    /**
     * Revisa si el objeto dado es una plantilla válida, una plantilla
     * es válida si el nuevo nombre no pertenece a otro platillo.
     */
    @Override
    public void validate (Object o, Errors errors) {
        FoodForm foodForm = (FoodForm) o;
        Food other = foodService.getFood(foodForm.getNewName());
        if (other != null && !foodForm.getNewName().equals (foodForm.getOldName())) {
          errors.rejectValue("newName", "Duplicate.foodForm.name");
        }
    }
}
