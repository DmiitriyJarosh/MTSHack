package com.divider.Divider.service;

public enum HostType {
  Tech("Технический"),
  User("Пользовательский");

  HostType(String name) {
    this.name = name;
  }

  private final String name;

  public String getName() {
    return name;
  }

  public static HostType buildFromCode(int code) {
    switch (code) {
      case 0:
        return HostType.Tech;
      case 1:
        return HostType.User;
      default:
        throw new IllegalArgumentException("Code not accepted: " + code);
    }
  }
}
