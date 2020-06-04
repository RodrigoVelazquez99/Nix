package com.naat.nix.user.controller;

import java.util.List;

import com.naat.nix.user.model.Client;
import com.naat.nix.user.model.ClientForm;
import com.naat.nix.user.model.DeliveryMan;
import com.naat.nix.user.model.User;
import com.naat.nix.user.util.EmailService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * Manipulacion de usuarios
 */
@Service
public class UserService {

    /* DAO para usuarios */
    @Autowired
    private UserRepository userRepository;

    /* DAO para repartidores */
    @Autowired
    private DeliveryManRepository deliveryRepository;

    /* DAO para clientes */
    @Autowired
    private ClientRepository clientRepository;

    /* Encriptador para contraseñas */
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    /* Servicio para mandar correos */
    @Autowired
    private EmailService emailService;

    /**
     * Obtener un usuario usando su correo
     * @param email Correo del usuario
     * @return Usuario con el correo dado
     */
    public User findByEmail (String email){
      return userRepository.findByEmail(email);
    }

    /**
     * Obtener un usuario usando su nombre de usuario
     * @param name nombre de usuario
     * @return Usuario con el nombre dado
     */
    public User findByUsername (String name) {
      return userRepository.findByUsername(name);
    }

    /**
     * Crear y guarda un cliente nuevo usando un usuario y la informaciń de un
     * formulario de registro de cliente
     * @param user Usuario
     * @param clientForm Formulario de registro de cliente
     * @return Cliente nuevo
     */
    public Client newClient (User user, ClientForm clientForm) {
      Client client = new Client();
      client.setEmail(user.getEmail());
      client.setUser(user);
      client.setPhone(clientForm.getPhone());
      client.setAddress(List.of(clientForm.getAddress()));
      user.setClient(client);
      clientRepository.save(client);
      return client;
    }

    /**
     * Crea y guarda un repartidor nuevo usando un usuario
     * @param user Usuario
     * @return Repartidor nuevo
     */
    public DeliveryMan newDelivery(User user, String raw_pass) {
      DeliveryMan deliveryMan = new DeliveryMan();
      deliveryMan.setEmail(user.getEmail());
      deliveryMan.setUser(user);
      user.setDeliveryMan(deliveryMan);
      deliveryRepository.save(deliveryMan);

      emailService.sendRegistryConfirmation(deliveryMan, raw_pass);

      return deliveryMan;
    }

    /**
     * Guarda las modificacione a un usuario en la base de datos
     * @param user Usuario
     */
    public void saveUser (User user){
      user.setPassword (bCryptPasswordEncoder.encode(user.getPassword()));
      userRepository.save(user);
    }

}
