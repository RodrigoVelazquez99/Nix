package com.naat.nix.order.controller;

import com.naat.nix.user.config.UserWrapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/orders")
public class TakeoutController {
  @Autowired
  private TakeoutService service;

  @GetMapping
  public String getOrders(Model model, @AuthenticationPrincipal UserWrapper principal) {
    var orders = service.getOrders(principal);
    model.addAllAttributes(orders);
    return "orders";
  }
}