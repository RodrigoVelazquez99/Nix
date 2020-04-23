package com.naat.nix.user.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.naat.nix.order.model.Takeout;

import lombok.Data;

@Data
@Entity
public class Client {
  @Id
  @OneToOne
  @JoinColumn(name = "user_email")
  private User user;

  @OneToMany(mappedBy = "client")
  private List<Takeout> orders;
}