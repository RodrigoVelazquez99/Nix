package com.naat.nix.user.controller;

import com.naat.nix.user.model.DeliveryMan;

import org.springframework.data.repository.CrudRepository;

/**
 * DAO para repartidores
 */
public interface DeliveryManRepository extends CrudRepository<DeliveryMan, String>{

}