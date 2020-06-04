package com.naat.nix.user.model;

import lombok.Data;

/**
 * Guardar información de formulario de registro de cliente
 */
@Data
public class ClientForm {

  private String email;

  private String username;

  private String password;

  private String passwordConfirm;

  private String phone;

  private String address;

}
