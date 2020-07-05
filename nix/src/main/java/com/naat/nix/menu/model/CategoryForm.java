package com.naat.nix.menu.model;

import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Data;

/* Clase en la que se guardan los datos para actualizar una categoria */
@NoArgsConstructor
@Data
public class CategoryForm {

  /* El nuevo nombre de la categoria */
  private String newCategory;

  /* El nombre anterior de la categoria */
  private String oldCategory;

}
