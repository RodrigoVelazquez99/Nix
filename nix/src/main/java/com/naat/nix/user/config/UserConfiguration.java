package com.naat.nix.user.config;

import java.util.ArrayList;

import com.naat.nix.user.controller.UserRepository;
import com.naat.nix.user.model.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * Construir usuario de Spring con un usuario de la aplicación (en la base de datos)
 */
@Service
public class UserConfiguration implements UserDetailsService{

  /* DAO para manejar usuarios*/
  @Autowired
  private UserRepository dao;

  /**
   * Obtener un usuario de Spring usando usando un email
   * @param email Identificador del usuario de Spring
   * @return Usuario de Spring
   */
  public UserDetails loadUserByUsername(String email) {
    User user = dao.findByEmail(email);
    if (user == null) {
      throw new UsernameNotFoundException(email);
    }
    return buildUser(user);
  }

  /**
   * Crear instancia de usuario de Spring usando un usuario de la aplicación
   * @param user Usuario de la aplicación
   * @return Usuario de Spring
   */
  private UserWrapper buildUser(User user) {
    String username = user.getEmail();
    String password = user.getPassword();
    var authorities = new ArrayList<SimpleGrantedAuthority>();

    if(user.getAdmin() != null){
      authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
    }

    if(user.getClient() != null){
      authorities.add(new SimpleGrantedAuthority("ROLE_CLIENT"));
    }

    if(user.getDeliveryMan() != null) {
      authorities.add(new SimpleGrantedAuthority("ROLE_DELIVERYMAN"));
    }

    return new UserWrapper(user, username, password, authorities);
  }
}
