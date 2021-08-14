package com.divider.Divider.dto;

import java.util.Objects;
import javax.validation.constraints.NotBlank;

public class Host {

  @NotBlank(message = "Host name has to be filled")
  private String name;

  public Host() {
  }

  public Host(String name) {
    this.name = name;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Host host = (Host) o;
    return Objects.equals(name, host.name);
  }

  @Override
  public int hashCode() {
    return Objects.hash(name);
  }
}
