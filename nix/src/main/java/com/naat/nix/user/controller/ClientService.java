package com.naat.nix.user.controller;

import com.naat.nix.user.model.Client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

/**
 * Manipulaciómn de clientes
 */
@Service
public class ClientService {

    /**
     * DAO de clientes
     */
    @Autowired
    private ClientRepository clientRepository;

    /**
     * Obtener un cliente usando su correo
     * @param email Correo del cliente
     * @return Cliente con el correo dado
     */
    public Client findByEmail (String email){
      return clientRepository.findByEmail(email);
    }

    /**
     * Guarda un cliente en la base de datos
     * @param client Cliente a modificar
     */
    public void saveClient (Client client) {
      clientRepository.save(client);
    }

    /**
    * Obtiene las direcciones de un cliente.
    * @param client Cliente del cual queremos obtener direcciones
    */
    public ArrayList<String> getAddresses (Client client) {
      Client cur = clientRepository.findByEmail (client.getEmail());
      return new ArrayList<String>(cur.getAddress());
    }


}
