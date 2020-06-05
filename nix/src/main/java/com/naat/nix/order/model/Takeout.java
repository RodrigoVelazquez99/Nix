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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.naat.nix.menu.model.CartFood;
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

  /* El id de la orden */
  @GeneratedValue
  @Id
  private Long id;

  /* La fecha de entrega de la orden */
  @Column(
    columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP",
    name="entrega"
  )
  private LocalDate deliveryDate;

  /* El estado de la orden */
  @Enumerated(EnumType.STRING)
  private DeliveryStatus status = DeliveryStatus.PREPARING;

  /* El precio total de la orden */
  @Column(name="precio")
  private Double price;

  /**
   * Platillos solicitados en la orden.
   */
  @OneToMany(mappedBy="takeout")
  private List<CartFood> foodItems;

  /* La dirección de entrega */
  @Column(name="domicilio")
  private String address;

  /* El cliente al que le pertenece la orden */
  @ManyToOne
  @JoinColumn(name="cliente")
  private Client client;

  /* El repartidor encargado de la entrega */
  @ManyToOne
  @JoinColumn(name="repartidor")
  private DeliveryMan deliveryMan;
}
