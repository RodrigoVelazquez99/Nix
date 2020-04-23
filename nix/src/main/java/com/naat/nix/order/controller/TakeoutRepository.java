package com.naat.nix.order.controller;

import com.naat.nix.order.model.Takeout;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TakeoutRepository extends CrudRepository<Takeout, Long>{

  /**
   * Filtering orders by clint
   * @param id
   * @return Iterable of all the client takeout orders.
   */
  Iterable<Takeout> findByClientEmail(String email);
}