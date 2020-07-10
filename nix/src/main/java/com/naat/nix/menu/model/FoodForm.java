package com.naat.nix.menu.model;

import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Data;

/* Clase en la que se guardan los datos para actualizar un platillo */
@NoArgsConstructor
@Data
public class FoodForm {

  /* El nuevo precio */
  private int newPrice;

  /* La nueva descripcion */
  private String newDescription;

  /* El nuevo nombre de la imagen */
  private String newImage;

  /* El nuevo nombre */
  private String newName;

  /* La nueva categoria a la que pertenece */
  private Category newCategory;

  /* El nombre anterior */
  private String oldName;

}
