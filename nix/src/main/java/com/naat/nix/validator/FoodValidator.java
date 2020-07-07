package com.naat.nix.validator;

import com.naat.nix.menu.controller.FoodService;
import com.naat.nix.menu.model.Food;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * Validar platillos.
 */
@Component
public class FoodValidator implements Validator {

    /* Manipulación de platillos */
    @Autowired
    private FoodService foodService;

    /**
     * Revisar si la clase dada es la de platillos.
     */
    @Override
    public boolean supports(Class<?> aClass) {
        return Food.class.equals(aClass);
    }

    /**
     * Revisa si el objeto dado es un platillo válido.
     */
    @Override
    public void validate (Object o, Errors errors) {
        Food food = (Food) o;
        Food other = foodService.getFood(food.getName());
        if (other != null) {
          errors.rejectValue("name", "Duplicate.food.name");
        }
    }
}
