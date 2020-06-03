package com.naat.nix.order.model;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.naat.nix.menu.model.Food;
import com.naat.nix.user.model.Client;
import com.naat.nix.user.model.DeliveryMan;

import lombok.Data;

/**
 * Representación de una órden de comida para la base de datos
 */
@Data
@Entity
@Table(name="Orden")
public class Takeout {

  @GeneratedValue
  @Id
  private Long id;

  @Column(
    columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP",
    name="entrega"
  )
  private LocalDate deliveryDate;

  @Enumerated(EnumType.STRING)
  private DeliveryStatus status = DeliveryStatus.PREPARING;

  @Column(name="precio")
  private Double price;

  @ManyToMany
  @JoinTable(
    name="takeout_contains_food",
    joinColumns = @JoinColumn(name="takeout_id"),
    inverseJoinColumns = @JoinColumn(name="food_id")
  )
  @Column(name="platillos")
  private List<Food> foodItems;

  @ManyToOne
  @JoinColumn(name="cliente")
  private Client client;

  @ManyToOne
  @JoinColumn(name="repartidor")
  private DeliveryMan deliveryMan;
}