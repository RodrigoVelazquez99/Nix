package com.naat.nix.menu.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

/**
 * Representación de una categoría de comida en la base de datos
 */
@Data
@NoArgsConstructor
@RequiredArgsConstructor
@Entity
@Table(name="Categoria")
public class Category {

  /* Nombre de la categoria */
  @Id
  @NonNull
  @Column(name="nombre")
  private String category;

  /* Los alimentos que pertenecen a esta categoria */
  @OneToMany(mappedBy="category")
  @Column(name="categoria")
  private List<Food> foods;
}
