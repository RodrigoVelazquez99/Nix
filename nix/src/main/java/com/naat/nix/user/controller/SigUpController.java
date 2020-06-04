package com.naat.nix.user.controller;

import javax.validation.Valid;

import com.naat.nix.user.model.User;
import com.naat.nix.user.model.ClientForm;
import com.naat.nix.validator.UserValidator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * Manejo de URL para el rigistro de usuarios
 */
@Controller
public class SigUpController {

      /**
       * Manipulación de usuarios
       */
      @Autowired
      private UserService userService;

      /**
       * Revisión de potenciales usuarios
       */
      @Autowired
      private SecurityService securityService;

      /**
       * Validar información de usuarios
       */
      @Autowired
      private UserValidator userValidator;

      /**
       * Crear formulario vacío para clientes
       * @param modelo Modelo actual de la vista
       * @return Nombre de la vista a mostrar
       */
      @GetMapping("/signup")
      public String registration(Model model) {
          model.addAttribute("clientForm", new ClientForm());
          return "signup";
      }

      /**
       * Procesar formulario de clientes
       * Intenta crear nuevo cliente con la información.
       * @param clientForm Datos del formulario
       * @param bindingResult Resultados de la validación del formulario
       * @return Nombre de la vista a mostrar
       */
      @PostMapping("/signup")
      public String registration(@ModelAttribute @Valid ClientForm clientForm, BindingResult bindingResult) {
          User user = new User();
          user.setEmail(clientForm.getEmail());
          user.setUsername(clientForm.getUsername());
          user.setPassword(clientForm.getPassword());

          userValidator.validate(user, bindingResult);

          if (bindingResult.hasErrors()) {
            return "signup";
          }

          userService.saveUser(user);

          userService.newClient(user, clientForm);

          securityService.autoLogin(user.getUsername(), user.getPassword());

          return "redirect:/";
      }

      /**
       * Crear formulario vacío para registrar repartidores
       * @param modelo Modelo actual de la vista
       * @return Nombre de la vista a mostrar
       */
      @GetMapping("/signup/delivery")
      @Secured("ROLE_ADMIN")
      public String deliveryRegistration(Model model) {
        model.addAttribute("user", new User());
        return "delivery_signup";
      }

      /**
       * Procesar formulario de repartidores
       * Intenta crear nuevo repartidor con la información.
       * @param user Usuario con datos del formulario
       * @param bindingResult Resultados de la validación del formulario
       * @return Nombre de la vista a mostrar
       */
      @PostMapping("signup/delivery")
      @Secured("ROLE_ADMIN")
      public String deliveryRegistration(@ModelAttribute @Valid User user, BindingResult result) {
        userValidator.validate(user, result);

        if(result.hasErrors()) {
          return "delivery_signup";
        }

        String raw_pass = user.getPassword();

        userService.saveUser(user);

        userService.newDelivery(user, raw_pass);

        return "redirect:/";
      }
}
