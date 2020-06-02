package com.naat.nix.user.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

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
