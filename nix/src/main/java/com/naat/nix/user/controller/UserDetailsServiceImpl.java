package com.naat.nix.user.controller;

import com.naat.nix.user.model.User;
import com.naat.nix.user.controller.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String name) {
        User user = userRepository.findByUsername(name);
        if (user == null) throw new UsernameNotFoundException(name);

        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
        if (user.getAdmin() != null) {
          grantedAuthorities.add(new SimpleGrantedAuthority ("ROLE_ADMIN"));
        }
        if (user.getClient() != null){
          grantedAuthorities.add(new SimpleGrantedAuthority ("ROLE_CLIENT"));
        }
        if (user.getDeliveryMan() != null) {
          grantedAuthorities.add(new SimpleGrantedAuthority ("ROLE_DELIVERYMAN"));
        }

        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), grantedAuthorities);
    }
}
