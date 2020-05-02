package com.naat.nix.order.model;

/**
 * Enumeration representing the status of a takeout order.
 * @version 1.0
 */
public enum DeliveryStatus {
  /**
   * Still preparing the order.
   */
  PREPARING,

  /**
   * Order ready to be taken.
   */
  READY,
  
  /**
   * Order in the way.
   */
  DELIVERING,
  
  /**
   * Order already delivered.
   */
  DELIVERED;
}