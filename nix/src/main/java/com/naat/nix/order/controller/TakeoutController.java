package com.naat.nix.order.controller;

import com.naat.nix.user.config.UserWrapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Controla las URL para manejar órdenes
 */
@Controller
@RequestMapping(value = "/orders")
public class TakeoutController {
  /* Servicio donde se obtiene todo lo necesario para realizar las peticiones */
  @Autowired
  private TakeoutService service;

  /**
   * Añade todas las órdenes pertinentes a la vista
   * @param model Modelo actual
   * @param principal Usuario actual
   * @return Nombre de la plantilla de la vista
   */
  @GetMapping
  public String getOrders(Model model, @AuthenticationPrincipal UserWrapper principal) {
    var orders = service.getOrders(principal);
    model.addAllAttributes(orders);
    return "orders";
  }

  /**
   * Añade a la vista las órdenes que un repartidor puede seleccionar
   * @param model Modelo actual
   * @param principal Usuario actual (un repartidor)
   * @return Nombre de la plantilla de la vista
   */
  @GetMapping("/select/{takeoutId}")
  public String selectOrder(Model model, @PathVariable Long takeoutId,
      @AuthenticationPrincipal UserWrapper principal) throws Exception {

    service.selectOrder(principal, takeoutId);
    return "redirect:/orders";
  }

  /**
   * Añade a la vista las órdenes cuyo estatus se puede actualizar
   * @param model Modelo actual
   * @param principal Usuario actual (repartidor o administrador)
   * @return Nombre de la plantilla de la vista
   */
  @GetMapping("update/{takeoutId}")
  public String updateOrder(Model model, @PathVariable Long takeoutId,
    @AuthenticationPrincipal UserWrapper principal) throws Exception {

    service.updateStatusOrder(principal, takeoutId);
    return "redirect:/orders";
  }
}