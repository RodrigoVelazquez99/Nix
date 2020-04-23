package com.naat.nix.user.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.naat.nix.order.model.Takeout;

import lombok.Data;

@Data
@Entity
public class DeliveryMan {
  @Id
  private String email;

  @OneToMany(mappedBy = "deliveryMan")
  private List<Takeout> orders;
}