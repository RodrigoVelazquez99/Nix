package com.naat.nix.user.controller;

import com.naat.nix.user.model.Client;
import com.naat.nix.user.model.ClientForm;
import com.naat.nix.user.model.DeliveryMan;
import com.naat.nix.user.model.User;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private DeliveryManRepository deliveryRepository;

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;


    public User findByEmail (String email){
      return userRepository.findByEmail(email);
    }

    public User findByUsername (String name) {
      return userRepository.findByUsername(name);
    }

    public Client newClient (User user, ClientForm clientForm) {
      Client client = new Client();
      client.setEmail(user.getEmail());
      client.setUser(user);
      client.setPhone(clientForm.getPhone());
      client.setAddress(new ArrayList<String>());
      client.getAddress().add(clientForm.getAddress());
      user.setClient(client);
      clientRepository.save(client);
      return client;
    }

    public Client newClient (User user) {
      Client client = new Client();
      client.setEmail(user.getEmail());
      client.setUser(user);
      client.setAddress(new ArrayList<String>());
      user.setClient(client);
      clientRepository.save(client);
      return client;
    }


    public DeliveryMan newDelivery(User user) {
      DeliveryMan deliveryMan = new DeliveryMan();
      deliveryMan.setEmail(user.getEmail());
      deliveryMan.setUser(user);
      user.setDeliveryMan(deliveryMan);
      deliveryRepository.save(deliveryMan);
      return deliveryMan;
    }

    public void saveUser (User user){
      user.setPassword (bCryptPasswordEncoder.encode(user.getPassword()));
      userRepository.save(user);
    }

}
