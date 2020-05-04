package com.naat.nix.menu.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import com.naat.nix.order.model.Takeout;

import lombok.Data;

@Data
@Entity
public class Food {
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Id
  private Long id;

  private String name;

  private String description;

  private String imagePath;
  
  private Long price;

  @ManyToOne
  @JoinColumn
  private Category category;

  @ManyToMany
  @JoinTable(
    name="cart_contains_food",
    joinColumns = @JoinColumn(name="cart_id"),
    inverseJoinColumns = @JoinColumn(name="food_id")
  )
  private List<Cart> containingCarts;

  @ManyToMany(mappedBy = "food_items")
  private List<Takeout> containingOrders;
}