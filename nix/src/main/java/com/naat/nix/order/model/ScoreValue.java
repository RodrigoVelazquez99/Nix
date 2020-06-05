package com.naat.nix.order.model;

import java.util.HashMap;
import java.util.Map;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * Posibles valores para calificar
 */
@RequiredArgsConstructor
public enum ScoreValue {

  BAD(Long.valueOf(0)),

  CAN_BE_BETTER(Long.valueOf(1)),

  REGULAR(Long.valueOf(2)),

  GOOD(Long.valueOf(3)),

  EXCELLENT(Long.valueOf(4));

  @Getter
  private final Long value;

  private static Map<Long, ScoreValue> map = new HashMap<>();

  static {
    for (ScoreValue score : ScoreValue.values()) {
      map.put(score.value, score);
    }
  }

  /**
   * Método que mapea el valor
   * @param val valor
   * @return el valor en map
   */

  public static ScoreValue valueOf(Long val) {

    return map.get(val);
  }

  @Override
  public String toString() {
    String s;
    switch (this) {
      case BAD: s = "Malo"; break;
      case CAN_BE_BETTER: s = "Mejorable"; break;
      case REGULAR: s = "Regular"; break;
      case GOOD: s = "Bueno"; break;
      case EXCELLENT: s = "Excelente"; break;
      default: s = "";
    }
    return s;
  }
}