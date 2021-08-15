package com.divider.Divider.service;

import java.util.Arrays;
import java.util.List;

public enum Algo {
  Bayes(0L, "Naive Bayes classifier"),
  XGBoost(1L, "Gradient boosting over decisive trees");

  private final String name;
  private final Long id;

  Algo(Long id, String name) {
    this.name = name;
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public Long getId() {
    return id;
  }

  public static List<Algo> getAlgoList() {
    return Arrays.asList(Bayes, XGBoost);
  }

  public static Algo getById(Long id) {
    return getAlgoList().stream()
        .filter((algo) -> algo.getId().equals(id))
        .findFirst()
        .orElseThrow(() -> new IllegalArgumentException("Algo id not supported: " + id));
  }
}
