package com.naat.nix.order.controller;

<<<<<<< HEAD
import java.security.Principal;

import com.naat.nix.order.model.Takeout;
=======
import com.naat.nix.user.config.UserWrapper;
>>>>>>> follow-order

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/orders")
public class TakeoutController {
  @Autowired
  private TakeoutService service;

  @GetMapping
<<<<<<< HEAD
  public String getOrders(Model model, Principal principal) {
    var orders = service.getOrders(principal);
    model.addAllAttributes(orders);
    return "orders";
  }

  @PostMapping
  public String saveOrder(Model model, Principal principal,
   @RequestBody Takeout takeout) {
    service.saveOrder(principal, takeout);
    return "orders";
  }

  @PostMapping("/select")
  public String selectOrder(Model model, Principal principal,
   @RequestBody Takeout takeout) {
    service.selectOrder(principal, takeout);
    return "orders";
=======
  public String getOrders(Model model, @AuthenticationPrincipal UserWrapper principal) {
    var orders = service.getOrders(principal);
    model.addAllAttributes(orders);
    return "orders";
>>>>>>> follow-order
  }
}