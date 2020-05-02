package com.naat.nix.order.controller;

import java.security.Principal;
import java.util.HashMap;
import java.util.Map;

import com.naat.nix.order.model.DeliveryStatus;
import com.naat.nix.order.model.Takeout;
import com.naat.nix.user.config.UserWrapper;
import com.naat.nix.user.model.Client;
import com.naat.nix.user.model.DeliveryMan;
import com.naat.nix.user.model.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Service to manage takeout information.
 */
@Service
public class TakeoutService {

  @Autowired
  private TakeoutRepository takeoutDao;

  public Map<String, Iterable<Takeout>> getOrders(UserWrapper principal) {
    User user = principal.getCustomUser();

    HashMap<String, Iterable<Takeout>> orders = new HashMap<>();

    if(user.getClient() != null){
      var cli_orders = getClientOrders(user.getClient());
      orders.put("client_orders", cli_orders);
    }

    if(user.getDeliveryMan() != null) {
      var del_orders = getDeliveryManOrders(user.getDeliveryMan());
      orders.put("delivery_orders", del_orders);

      var ready_orders = getReadyOrders();
      orders.put("ready_orders", ready_orders);
    }

    if(user.getAdmin() != null) {
      var prep_orders = getPreparingOrders();
      orders.put("prep_orders", prep_orders);
    }

    return orders;
  }

  public Iterable<Takeout> getClientOrders(Client client) {
    return takeoutDao.findByClientEmail(client.getUser().getEmail());
  }

  public Iterable<Takeout> getDeliveryManOrders(DeliveryMan man) {
    return takeoutDao.findByDeliveryManEmail(man.getUser().getEmail());
  }

  public Iterable<Takeout> getPreparingOrders() {
    return takeoutDao.findByStatus(DeliveryStatus.PREPARING);
  }

  public Iterable<Takeout> getReadyOrders() {
    return takeoutDao.findByStatus(DeliveryStatus.READY);
  }
}