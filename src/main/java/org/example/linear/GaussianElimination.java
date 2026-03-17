package org.example.linear;

import org.example.exception.SingularMatrixException;

/**
 * Реализация метода Гаусса для решения системы линейных уравнений.
 */
public final class GaussianElimination {

  private static final double EPS = 1e-12;

  private GaussianElimination() {
  }

  public static double[] solve(double[][] a, double[] b) {
    validateInput(a, b);

    int n = b.length;
    double[][] matrix = copyMatrix(a);
    double[] vector = b.clone();

    for (int pivot = 0; pivot < n; pivot++) {
      int maxRow = findMaxRow(matrix, pivot);

      if (Math.abs(matrix[maxRow][pivot]) < EPS) {
        throw new SingularMatrixException("Матрица вырождена или близка к вырожденной.");
      }

      swapRows(matrix, vector, pivot, maxRow);

      for (int row = pivot + 1; row < n; row++) {
        double factor = matrix[row][pivot] / matrix[pivot][pivot];

        for (int col = pivot; col < n; col++) {
          matrix[row][col] -= factor * matrix[pivot][col];
        }

        vector[row] -= factor * vector[pivot];
      }
    }

    return backSubstitution(matrix, vector);
  }

  private static void validateInput(double[][] a, double[] b) {
    if (a == null || b == null) {
      throw new IllegalArgumentException("Матрица и вектор не должны быть null.");
    }
    if (a.length == 0 || b.length == 0) {
      throw new IllegalArgumentException("Матрица и вектор не должны быть пустыми.");
    }
    if (a.length != b.length) {
      throw new IllegalArgumentException("Размерность матрицы и вектора должна совпадать.");
    }

    int n = a.length;
    for (double[] row : a) {
      if (row == null || row.length != n) {
        throw new IllegalArgumentException("Матрица должна быть квадратной.");
      }
    }
  }

  private static double[][] copyMatrix(double[][] source) {
    double[][] copy = new double[source.length][];
    for (int i = 0; i < source.length; i++) {
      copy[i] = source[i].clone();
    }
    return copy;
  }

  private static int findMaxRow(double[][] matrix, int pivot) {
    int maxRow = pivot;
    for (int row = pivot + 1; row < matrix.length; row++) {
      if (Math.abs(matrix[row][pivot]) > Math.abs(matrix[maxRow][pivot])) {
        maxRow = row;
      }
    }
    return maxRow;
  }

  private static void swapRows(double[][] matrix, double[] vector, int i, int j) {
    if (i == j) {
      return;
    }

    double[] tempRow = matrix[i];
    matrix[i] = matrix[j];
    matrix[j] = tempRow;

    double tempValue = vector[i];
    vector[i] = vector[j];
    vector[j] = tempValue;
  }

  private static double[] backSubstitution(double[][] matrix, double[] vector) {
    int n = vector.length;
    double[] result = new double[n];

    for (int row = n - 1; row >= 0; row--) {
      double sum = vector[row];

      for (int col = row + 1; col < n; col++) {
        sum -= matrix[row][col] * result[col];
      }

      if (Math.abs(matrix[row][row]) < EPS) {
        throw new SingularMatrixException("Нулевой элемент на диагонали.");
      }

      result[row] = sum / matrix[row][row];
    }

    return result;
  }
}
