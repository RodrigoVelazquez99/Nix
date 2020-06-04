package com.naat.nix.order.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.naat.nix.order.model.DeliveryStatus;
import com.naat.nix.order.model.Takeout;
import com.naat.nix.user.config.UserWrapper;
import com.naat.nix.user.model.Client;
import com.naat.nix.user.model.DeliveryMan;
import com.naat.nix.user.model.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;

/**
 * Servicio para manipular y obtener información de órdenes
 */
@Service
public class TakeoutService {

  /* DAO de las órdenes */
  @Autowired
  private TakeoutRepository takeoutDao;

  /**
   * Obtiene las órdenes de un usuario
   * @param principal Usuario actual
   * @return Diccionario con listas de órdenes clasificadas por estatus
   */
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
    }

    return orders;
  }

  /**
   * Obtiene las órdenes de un cliente
   * @param principal Usuario actual (un cliente)
   * @return Diccionario con listas de órdenes divididas en actuales y pasadas
   */
  @Secured("ROLE_CLIENT")
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

  /**
   * Obtiene las órdenes de un repartidor
   * @param principal Usuario actual (un repartidor)
   * @return Diccionario con listas de órdenes divididas en actuales y pasadas
   */
  @Secured("ROLE_DELIVERYMAN")
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

  /**
   * Obtiene las órdenes por estatus
   * @return Diccionario con listas de órdenes clasificadas por estatus
   */
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

  /**
   * Clasifica órdenes en dos dependiendo si tiene un estatus dado o no
   * @param orders Órdenes a clasificar
   * @param status Estaus dado para clasificar órdenes
   * @return Lista con dos listas: órdenes con y sin el estatus dado
   */
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
  }
  
  /**
   * Avanza el estatus de una orden
   * @param principal Usuario actual (administrador o repartidor)
   * @param takeoutId Identificador de la orden a actualizar
   * @throws Exception Si la orden no está en la base de datos
   */
  @Secured({"ROLE_DELIVERYMAN", "ROLE_ADMIN"})
  public void updateStatusOrder(UserWrapper principal, Long takeoutId) throws Exception {
    User user = principal.getCustomUser();
    Takeout takeout = takeoutDao.findById(takeoutId).orElseThrow(
      () -> new Exception("Takeout not in database")
    );

    if(user.getAdmin() != null) {
      switch(takeout.getStatus()) {
        case PREPARING:
          takeout.setStatus(DeliveryStatus.READY);
          break;
        default:
          throw new Exception("Invalid admin status change");
      }
    }

    if(user.getDeliveryMan() != null) {
      switch(takeout.getStatus()) {
        case DELIVERING : 
          takeout.setStatus(DeliveryStatus.DELIVERED);
          break;
        default:
          throw new Exception("Invalid delivery admin status change");
      }
    }

    takeoutDao.save(takeout);
  }

  /**
   * Asigna una orden a un repartidor
   * @param principal Usuario actual (un repartidor)
   * @param takeoutId Identificador de la orden a seleccionar
   * @throws Exception Si la orden no está en la base de datos o si ya está seleccionada
   */
  @Secured("ROLE_DELIVERYMAN")
  public void selectOrder(UserWrapper principal, Long takeoutId) throws Exception {
    User user = principal.getCustomUser();
    Takeout takeout = takeoutDao.findById(takeoutId).orElseThrow(
      () -> new Exception("Takeout not in database")
    );

    Boolean valid = 
      user.getDeliveryMan() != null && takeout.getDeliveryMan() == null
      && takeout.getStatus() == DeliveryStatus.READY;

    if(valid) {
      takeout.setDeliveryMan(user.getDeliveryMan());
      takeout.setStatus(DeliveryStatus.DELIVERING);
      takeoutDao.save(takeout);
    } else {
      throw new Exception("Takeout already selected");
    }
  }

  /**
   * Guarda una orden en la base de datos
   * @param t Orden a guardar
   */
  public void save(Takeout t) {
    takeoutDao.save(t);
  }
}