package com.naat.nix.user.controller;

import java.security.Principal;
import javax.validation.Valid;
import com.naat.nix.user.model.User;
import com.naat.nix.user.controller.SecurityService;
import com.naat.nix.user.controller.UserService;
import com.naat.nix.validator.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
public class UserController {

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

    @GetMapping("/")
    public String index(Model model, Principal principal) {
        String email = principal.getName();
        model.addAttribute("currentUsername", email);
        return "index";
    }
}
