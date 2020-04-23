package com.naat.nix.order.controller;

import java.security.Principal;
import java.util.ArrayList;

import com.naat.nix.order.model.Takeout;
import com.naat.nix.user.model.User;

import org.springframework.beans.factory.annotation.Autowired;
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
  public String getOrders(Model model, Principal principal) {
    User user = (User) principal;

    Iterable<Takeout> orders = new ArrayList<Takeout>(); // Provisional default value

    if(user.getClient() != null){
      orders = service.getClientOrders(user.getClient());
      model.addAttribute("client_orders", orders);
    }
    
    return "client_orders";
  }
}