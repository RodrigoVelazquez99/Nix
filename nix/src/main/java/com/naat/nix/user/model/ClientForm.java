package com.naat.nix.user.model;

import lombok.Data;

/**
 * Form for client signup
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
