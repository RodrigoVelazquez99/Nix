package com.naat.nix.menu.model;

import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Data;

/* Clase en la que se guardan los datos para actualizar un platillo */
@NoArgsConstructor
@Data
public class FoodForm {

  /* El id del platillo */
  private int id;

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

  /* El precio anterior */
  private int oldPrice;

  /* La descripcion anterior */
  private String oldDescription;

  /* La imagen anterior */
  private String oldImage;

  /* El nombre anterior */
  private String oldName;

  /* La categoria a la que pertenecia */
  private Category oldCategory;


}
