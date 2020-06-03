package com.naat.nix.user.controller;

import com.naat.nix.user.model.User;

import org.springframework.data.repository.CrudRepository;

/**
 * DAO para usuarios
 */
public interface UserRepository extends CrudRepository<User, String>{
    User findByEmail (String email);
    User findByUsername (String name);
}
