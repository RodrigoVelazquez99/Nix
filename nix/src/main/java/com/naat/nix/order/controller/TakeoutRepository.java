package com.naat.nix.order.controller;

import com.naat.nix.order.model.DeliveryStatus;
import com.naat.nix.order.model.Takeout;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * DAO de las órdenes
 */
@Repository
public interface TakeoutRepository extends CrudRepository<Takeout, Long>{

  Iterable<Takeout> findByClientEmail(String email);

  Iterable<Takeout> findByDeliveryManEmail(String emal);

  Iterable<Takeout> findByStatus(DeliveryStatus status);
}