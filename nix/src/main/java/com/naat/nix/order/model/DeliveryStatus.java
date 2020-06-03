package com.naat.nix.order.model;

/**
 * Posibles estatus de una orden
 * @version 1.0
 */
public enum DeliveryStatus {

  PREPARING, READY, DELIVERING, DELIVERED;

  /**
   * Descripción en español de los estatus
   * @return Cade que describe el estatus
   */
  public String toString() {
    var s = "";
    switch(this) {
      case PREPARING: s = "En preparación"; break;
      case READY: s = "Listo para repartición"; break;
      case DELIVERING: s = "En camino"; break;
      case DELIVERED: s = "Entregado"; break;
    }
    return s;
  }
}