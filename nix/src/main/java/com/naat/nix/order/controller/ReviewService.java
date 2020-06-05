package com.naat.nix.order.controller;

import com.naat.nix.order.model.ScoreValue;
import com.naat.nix.user.config.UserWrapper;
import com.naat.nix.user.controller.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Manipuación de calificaciones
 */
@Service
public class ReviewService {

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private TakeoutService takeoutService;

    /**
     * Método que asigna la calificación
     * @param principal que es el usuario actual
     * @param score que es la calificaión que se va asignar
     */
    public void setScore(UserWrapper principal, Long score) {
        var c = principal.getCustomUser().getClient();
        c.setScore(score);
        clientRepository.save(c);
    }
    /**
     * Método que obtiene el score del cliente
     * @param principal que es el usuario actual
     * @return score del cliente
     */
    public Long getScore(UserWrapper principal) {
        return principal.getCustomUser().getClient().getScore();
    }
    /**
     * Método que obtiene Score de tipo long
     * @param principal que es el usuario actual
     * @return  score de tipo long
     */
    public ScoreValue getScoreEnum(UserWrapper principal) {
        return ScoreValue.valueOf(getScore(principal));
    }

    public Boolean canReview(UserWrapper principal) {
        return takeoutService.getOrders(principal).
            get("client_past_orders").iterator().hasNext();
    }
}