package com.naat.nix.order.controller;

import javax.validation.Valid;

import com.naat.nix.order.model.Review;
import com.naat.nix.order.model.ScoreValue;
import com.naat.nix.user.config.UserWrapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Control de las URL para calificar el servicio.
 */
@Controller
@RequestMapping(value = "/review")
public class ReviewController {

  @Autowired
  private ReviewService reviewService;

  /**
   * Método que añade datos a la vista
   * @param m Modelo actual
   * @param user Usuario actual
   * @return Nombre de la plantilla de la vista
   */
  @GetMapping
  @Secured("ROLE_CLIENT")
  public String review(Model m, @AuthenticationPrincipal UserWrapper user) {
    m.addAttribute("current_score", reviewService.getScoreEnum(user));
    m.addAttribute("review", new Review());
    m.addAttribute("scores", ScoreValue.values());
    m.addAttribute("can_review", reviewService.canReview(user));
    return "reviews";
  }
  /**
   * Metodo que toma la informacion ingresada
   * @param m Modelo actual
   * @param user Usuario actual
   * @@param review información de formulario
   * @return La plantilla con el valor
   */
  @PostMapping
  @Secured("ROLE_CLIENT")
  public String review(Model m, @AuthenticationPrincipal UserWrapper user,
  @Valid Review review) {
    if(review.getScore() != null) {
      reviewService.setScore(user, review.getScore().getValue());
    }
    return "redirect:/review";
  }
}