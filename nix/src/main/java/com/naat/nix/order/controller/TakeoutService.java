package com.naat.nix.order.controller;

<<<<<<< HEAD
import java.security.Principal;
import java.util.HashMap;
=======
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
>>>>>>> follow-order
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

<<<<<<< HEAD
  public Map<String, Iterable<Takeout>> getOrders(Principal principal) {
    User user = ((UserWrapper)principal).getCustomUser();

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
=======
  public Map<String, Iterable<Takeout>> getOrders(UserWrapper principal) {
    User user = principal.getCustomUser();

    var orders = new HashMap<String, Iterable<Takeout>>();

    if(user.getClient() != null){
      orders.putAll(getClientOrders(user.getClient()));
    }

    if(user.getDeliveryMan() != null) {
      orders.putAll(getDeliveryManOrders(user.getDeliveryMan()));

      orders.put("ready_orders", getOrdersByStatus().get("ready_orders"));
    }

    if(user.getAdmin() != null) {
      orders.putAll(getOrdersByStatus());
>>>>>>> follow-order
    }

    return orders;
  }

<<<<<<< HEAD
  public Iterable<Takeout> getClientOrders(Client client) {
    return takeoutDao.findByClientEmail(client.getUser().getEmail());
=======
  public HashMap<String, Iterable<Takeout>> getClientOrders(Client client) {
    var orders = classifyOrders(
      takeoutDao.findByClientEmail(client.getEmail()),
      DeliveryStatus.DELIVERED
    );

    var classifiedOrders = new HashMap<String, Iterable<Takeout>>();
    classifiedOrders.put("client_past_orders", orders.get(0));
    classifiedOrders.put("client_current_orders", orders.get(1));
    return classifiedOrders;
  }

  public HashMap<String, Iterable<Takeout>> getDeliveryManOrders(
  DeliveryMan man) {
    var orders = classifyOrders(
      takeoutDao.findByDeliveryManEmail(man.getEmail()),
      DeliveryStatus.DELIVERED
    );

    var classifiedOrders = new HashMap<String, Iterable<Takeout>>();
    classifiedOrders.put("delivery_past_orders", orders.get(0));
    classifiedOrders.put("delivery_current_orders", orders.get(1));
    return classifiedOrders;
  }

  public HashMap<String, Iterable<Takeout>> getOrdersByStatus() {
    var classifiedOrders = new HashMap<String, Iterable<Takeout>>();

    for(var status : DeliveryStatus.values()) {
      classifiedOrders.put(
        status.name().toLowerCase() + "_orders",
        takeoutDao.findByStatus(status)
      );
    }
    return classifiedOrders;
  }

  private List<Iterable<Takeout>> classifyOrders(Iterable<Takeout> orders,
  DeliveryStatus status) {

    var ordersOne = new ArrayList<Takeout>();
    var ordersAnother = new ArrayList<Takeout>();

    orders.forEach(
      ord -> {
        if(ord.getStatus() == status) {
          ordersOne.add(ord);
        } else {
          ordersAnother.add(ord);
        }
      }
    );

    return List.of(
      (Iterable<Takeout>)ordersOne,
      (Iterable<Takeout>)ordersAnother
    );
>>>>>>> follow-order
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
  
  public void saveOrder(Principal principal, Takeout takeout) {
    User user = ((UserWrapper)principal).getCustomUser();
    if(user.getDeliveryMan() != null || user.getAdmin() != null) {
      takeoutDao.save(takeout);
    }
  }

  public void selectOrder(Principal principal, Takeout takeout) {
    User user = ((UserWrapper)principal).getCustomUser();

    if(user.getDeliveryMan() != null && takeout.getDeliveryMan() == null) {
      takeout.setDeliveryMan(user.getDeliveryMan());
      takeoutDao.save(takeout);
    }
  }
}