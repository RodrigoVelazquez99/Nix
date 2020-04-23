package com.naat.nix.order.controller;

import com.naat.nix.order.model.Takeout;
import com.naat.nix.user.model.Client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Service to manage takeout information.
 */
@Service
public class TakeoutService {

  @Autowired
  private TakeoutRepository takeoutDao;

  public Iterable<Takeout> getClientOrders(Client client) {
    return takeoutDao.findByClientEmail(client.getUser().getEmail());
  }
}