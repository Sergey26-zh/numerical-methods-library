package org.example.core;

import java.util.Arrays;

public record Vector(double[] values) {

  public Vector {
    if (values == null || values.length == 0) {
      throw new IllegalArgumentException("Вектор не должен быть пустым.");
    }
    values = values.clone();
  }

  public int size() {
    return values.length;
  }

  public double get(int index) {
    return values[index];
  }

  @Override
  public String toString() {
    return Arrays.toString(values);
  }
}
