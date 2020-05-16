package com.naat.nix.user.controller;

import com.naat.nix.user.model.Client;

import org.springframework.data.repository.CrudRepository;

public interface ClientRepository extends CrudRepository<Client, String>{
  public Client findByEmail(String email);
}
