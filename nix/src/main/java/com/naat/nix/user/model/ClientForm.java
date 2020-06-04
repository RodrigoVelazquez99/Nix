package com.naat.nix.user.model;

import lombok.Data;

/**
 * Guardar información de formulario de registro de cliente
 */
@Data
public class ClientForm {

  /* El correo a registrar */
  private String email;

  /* El nombre del cliente */
  private String username;

  /* El password */
  private String password;

  /* La confirmacion del password */
  private String passwordConfirm;

  /* El teleofono del cliente */
  private String phone;

  /* La dirección del cliente */
  private String address;

}
