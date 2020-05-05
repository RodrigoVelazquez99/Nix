package com.naat.nix.menu.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name="Categoria")
public class Category {

  @Id
  @Column(name="nombre")
  private String categoria;

  @OneToMany(mappedBy="categoria")
  private List<Food> foods;

  public Category(){
  }

  public Category (String categoria) {
    this.categoria = categoria;
    foods = new ArrayList<Food>();
  }

}
