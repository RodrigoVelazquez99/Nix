package com.naat.nix.user.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

/**
 * Configuraciones no por omisión para la seguridad de los usuarios
 */
@Service
public class SecurityService {

    /* Manejar credenciales */
    @Autowired
    private AuthenticationManager authenticationManager;

    /* Obtener ususario de Spring desde usuarios de la aplicación */
    @Autowired
    private UserDetailsService userDetailsService;

    /* Logger */
    final Logger logger = LoggerFactory.getLogger(SecurityService.class);


    /**
     * Obtener usuario actual
     * @return correo del usuario actual
     */
    public String findLoggedInUsername() {
        Object userDetails = SecurityContextHolder.getContext().getAuthentication().getDetails();
        if (userDetails instanceof UserDetails) {
            return ((UserDetails)userDetails).getUsername();
        }

        return null;
    }


    /**
     * Autentificar el usuario con las credenciales dadas
     * @param username Correo
     * @param password Contraseña
     */
    public void autoLogin(String username, String password) {
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(userDetails, password, userDetails.getAuthorities());

        authenticationManager.authenticate(usernamePasswordAuthenticationToken);

        if (usernamePasswordAuthenticationToken.isAuthenticated()) {
            SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
        }
    }
}
