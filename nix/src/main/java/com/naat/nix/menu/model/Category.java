package com.naat.nix.menu.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

/**
 * Representación de una categoría de comida en la base de datos
 */
@Getter
@Setter
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
  @OneToMany(mappedBy="category", cascade = CascadeType.ALL)
  @Column(name="categoria")
  private List<Food> foods;

  @Override
  public boolean equals (Object obj) {
    if (! (obj instanceof Category)) {
      return false;
    }
    Category other = (Category) obj;
    if (this.category.equals (other.getCategory())) {
      return true;
    }
    return false;
  }

  @Override
  public String toString() {
    return this.category;
  }

}
