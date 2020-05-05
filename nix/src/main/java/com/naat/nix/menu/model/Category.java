package com.naat.nix.menu.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.Data;

@Data
@Entity
public class Category {
  @Id
  private String name;

  @OneToMany(mappedBy = "category")
  private List<Food> food_items;
}