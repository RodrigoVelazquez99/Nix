package com.naat.nix.user.config;

import javax.annotation.PostConstruct;

import com.naat.nix.user.controller.AdminRepository;
import com.naat.nix.user.controller.ClientRepository;
import com.naat.nix.user.controller.DeliveryManRepository;
import com.naat.nix.user.model.Admin;
import com.naat.nix.user.model.Client;
import com.naat.nix.user.model.DeliveryMan;
import com.naat.nix.user.model.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class UserInitialization {

  @Autowired
  private PasswordEncoder enconder;

  @Autowired
  private AdminRepository adminDao;

  @Autowired
  private ClientRepository clientDao;

  @Autowired
  private DeliveryManRepository deliverDao;

  @PostConstruct
  public void addUsers() {
    User admin = new User("admin@naat.io", enconder.encode("password"));

    User client = new User("client@naat.io", enconder.encode("password"));

    User deliver = new User("delivery@naat.io", enconder.encode("password"));

    var a = new Admin(admin);
    admin.setAdmin(a);
    adminDao.save(a);
    
    var c = new Client(client);
    client.setClient(c);
    clientDao.save(c);

    var d = new DeliveryMan(deliver);
    deliver.setDeliveryMan(d);
    deliverDao.save(d);
  }
}