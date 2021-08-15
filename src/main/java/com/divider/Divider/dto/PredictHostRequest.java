package com.divider.Divider.dto;

import java.util.Objects;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class PredictHostRequest {

  @NotBlank(message = "Host name has to be filled")
  private String name;

  @NotNull
  private Long algoId;

  public PredictHostRequest() {
  }

  public PredictHostRequest(String name, Long algoId) {
    this.name = name;
    this.algoId = algoId;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Long getAlgoId() {
    return algoId;
  }

  public void setAlgoId(Long algoId) {
    this.algoId = algoId;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    PredictHostRequest that = (PredictHostRequest) o;
    return Objects.equals(name, that.name) && Objects.equals(algoId, that.algoId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(name, algoId);
  }
}
