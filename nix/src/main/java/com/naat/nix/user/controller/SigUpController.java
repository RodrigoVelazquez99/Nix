package com.naat.nix.user.controller;

import com.naat.nix.user.controller.SecurityService;
import com.naat.nix.user.controller.UserService;
import com.naat.nix.validator.UserValidator;
import com.naat.nix.user.config.UserWrapper;
import com.naat.nix.user.model.User;
import javax.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
public class SigUpController {

      @Autowired
      private UserService userService;

      @Autowired
      private SecurityService securityService;

      @Autowired
      private UserValidator userValidator;

      @GetMapping("/signup")
      public String registration(Model model) {
          model.addAttribute("user", new User());
          return "signup";
      }

      @PostMapping("/signup")
      public String registration(@ModelAttribute @Valid User user, BindingResult bindingResult) {
          userValidator.validate(user, bindingResult);

          if (bindingResult.hasErrors()) {
            return "signup";
          }

          userService.saveUser(user);

          userService.newClient(user);

          securityService.autoLogin(user.getUsername(), user.getPassword());

          return "redirect:/";
      }
}
