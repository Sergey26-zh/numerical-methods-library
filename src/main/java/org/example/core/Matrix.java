package org.example.core;

import java.util.Arrays;

public record Matrix(double[][] values) {

  public Matrix {
    if (values == null || values.length == 0) {
      throw new IllegalArgumentException("Матрица не должна быть пустой.");
    }

    int cols = values[0].length;
    if (cols == 0) {
      throw new IllegalArgumentException("Матрица не должна быть пустой.");
    }

    double[][] copy = new double[values.length][cols];
    for (int i = 0; i < values.length; i++) {
      if (values[i] == null || values[i].length != cols) {
        throw new IllegalArgumentException("Все строки матрицы должны иметь одинаковую длину.");
      }
      copy[i] = values[i].clone();
    }
    values = copy;
  }

  public int rows() {
    return values.length;
  }

  public int cols() {
    return values[0].length;
  }

  public double get(int row, int col) {
    return values[row][col];
  }

  @Override
  public String toString() {
    return Arrays.deepToString(values);
  }
}
