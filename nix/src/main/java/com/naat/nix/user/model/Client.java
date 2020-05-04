package com.naat.nix.user.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.MapsId;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.naat.nix.order.model.Takeout;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@NoArgsConstructor
@RequiredArgsConstructor
@Entity
public class Client {
  @Id
  private String email;

  @OneToOne
  @MapsId
  @NonNull
  private User user;

  @OneToMany(mappedBy = "client")
  private List<Takeout> orders;
} 
