package com.naat.nix.user.controller;

import com.naat.nix.user.model.User;
import com.naat.nix.user.model.Client;
import com.naat.nix.user.controller.UserRepository;
import com.naat.nix.user.controller.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ClientService clientService;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;


    public User findByEmail (String email){
      return userRepository.findByEmail(email);
    }

    public User findByUsername (String name) {
      return userRepository.findByUsername(name);
    }

    public void newClient (User user) {
      Client client = new Client();
      client.setEmail(user.getEmail());
      client.setUser(user);
      user.setClient(client);
      clientService.saveClient(client);
    }

    public void saveUser (User user){
      user.setPassword (bCryptPasswordEncoder.encode(user.getPassword()));
      userRepository.save(user);
    }

}
