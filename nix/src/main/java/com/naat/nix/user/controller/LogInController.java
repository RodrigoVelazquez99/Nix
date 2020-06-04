package com.naat.nix.user.controller;

import com.naat.nix.user.config.UserWrapper;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Manejando URL de login
 */
@Controller
public class LogInController {

    /**
     * Agregar elementos a la vista de login
     * @param model Modelo de la vista actual
     * @param error Mensaje al ser redirigido al no ingresar las credenciales adecuadas
     * @param logout Mensaje al hacer logout
     * @return Nombre de la vista que se mostrará
     */
    @GetMapping("/login")
    public String login(Model model, String error, String logout) {
        if (error != null){
          model.addAttribute("error", "El correo o contraseña son incorrectos");
        }
        if (logout != null){
          model.addAttribute("message", "Vuelve pronto");
        }
        return "login";
    }

    /**
     * Redireccionar a la página de inicio dependiendo del tipo de usuario
     * @param model Modelo de la vista actual
     * @param user Usuario actual
     * @return Nombre de la vista que se mostrará
     */
    @GetMapping("/")
    public String index(Model model, @AuthenticationPrincipal UserWrapper user) {
        String email = user.getCustomUser().getEmail();
        model.addAttribute("currentUsername", email);
        if(user.getCustomUser().getClient() != null) {
          return "redirect:/menu";
        }
        return "redirect:/orders";
    }
}
