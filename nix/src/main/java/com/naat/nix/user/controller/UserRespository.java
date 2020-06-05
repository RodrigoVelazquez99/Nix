package com.naat.nix.user.controller;

import com.naat.nix.user.model.User;

import org.springframework.data.repository.CrudRepository;

public interface UserRespository extends CrudRepository<User, String>{

}