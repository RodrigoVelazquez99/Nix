package com.naat.nix.user.util;

import com.naat.nix.user.model.DeliveryMan;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

/**
 * Mandar correos
 */
@Service
public class EmailService {

  /* Interfaz para realizar el envío */
  @Autowired
  private JavaMailSender messager;

  /* Plantilla para el cuerpo de confirmación de registro de repartidor */
  private final String deliveryRegistryTemplate =
  "¡Bienvenido al equipo de trabajo, %s! \n" +
  "Su contraseña es %s \n";

  /* Asunto para confirmación de registro de repartidor. */
  private final String deliveryRegistrySubject = "Bienvenido al equipo";

  /**
   * Envía un correo de confirmación al repartidor dado
   * @param delivery Repartidor a ser notificado
   */
  public void sendRegistryConfirmation(DeliveryMan delivery, String raw_pass) {
    send(
      delivery.getEmail(),
      deliveryRegistrySubject,
      String.format(
        deliveryRegistryTemplate,
        delivery.getUser().getUsername(),
        raw_pass
      )
    );
  }

  /**
   * Envía un correo con los datos dados
   * @param to Destinatario
   * @param subject Asunto
   * @param body Cuerpo del mensaje
   */
  public void send(String to, String subject, String body) {
    var mess = new SimpleMailMessage();
    mess.setTo(to); mess.setSubject(subject); mess.setText(body);

    messager.send(mess);
  }
}