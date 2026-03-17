package org.example.nonlinear;

import org.example.core.MathFunction;
import org.example.core.NumericalResult;
import org.example.exception.ConvergenceException;

/**
 * Реализация метода бисекции (метода половинного деления).
 */
public final class BisectionMethod {

  private BisectionMethod() {
  }

  public static NumericalResult solve(
      MathFunction function,
      double left,
      double right,
      double tolerance,
      int maxIterations
  ) {
    validateInput(function, left, right, tolerance, maxIterations);

    double fLeft = function.evaluate(left);
    double fRight = function.evaluate(right);

    if (fLeft * fRight > 0) {
      throw new IllegalArgumentException(
          "На концах отрезка функция должна иметь разные знаки."
      );
    }

    double mid = left;
    int iteration = 0;
    double error = Math.abs(right - left);

    while (iteration < maxIterations) {
      mid = (left + right) / 2.0;
      double fMid = function.evaluate(mid);
      error = Math.abs(right - left) / 2.0;

      if (Math.abs(fMid) < tolerance || error < tolerance) {
        return new NumericalResult(mid, iteration + 1, error, true);
      }

      if (fLeft * fMid < 0) {
        right = mid;
        fRight = fMid;
      } else {
        left = mid;
        fLeft = fMid;
      }

      iteration++;
    }

    throw new ConvergenceException("Метод бисекции не сошёлся за заданное число итераций.");
  }

  private static void validateInput(
      MathFunction function,
      double left,
      double right,
      double tolerance,
      int maxIterations
  ) {
    if (function == null) {
      throw new IllegalArgumentException("Функция не должна быть null.");
    }
    if (left >= right) {
      throw new IllegalArgumentException("Левая граница должна быть меньше правой.");
    }
    if (tolerance <= 0) {
      throw new IllegalArgumentException("Точность должна быть положительной.");
    }
    if (maxIterations <= 0) {
      throw new IllegalArgumentException("Число итераций должно быть положительным.");
    }
  }
}
