package com.naat.nix.user.config;

import javax.annotation.PostConstruct;

import com.naat.nix.user.controller.AdminRepository;
import com.naat.nix.user.model.Admin;
import com.naat.nix.user.model.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class UserInitialization {

  @Autowired
  private PasswordEncoder encoder;

  @Autowired
  private AdminRepository adminDao;

  @PostConstruct
  public void addUsers() {
    var admin = new User("admin@naat.io", encoder.encode("admin123"));

    var a = new Admin(admin);
    admin.setAdmin(a);
    adminDao.save(a);
  }
}
