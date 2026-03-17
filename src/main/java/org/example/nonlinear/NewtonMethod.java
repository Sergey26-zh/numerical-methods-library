package org.example.nonlinear;

import org.example.core.DifferentiableFunction;
import org.example.core.NumericalResult;
import org.example.exception.ConvergenceException;

/**
 * Реализация метода Ньютона для решения нелинейных уравнений.
 */
public final class NewtonMethod {

  private static final double DERIVATIVE_EPS = 1e-12;

  private NewtonMethod() {
  }

  public static NumericalResult solve(
      DifferentiableFunction function,
      double initialGuess,
      double tolerance,
      int maxIterations
  ) {
    validateInput(function, tolerance, maxIterations);

    double current = initialGuess;
    int iteration = 0;
    double error = Double.MAX_VALUE;

    while (iteration < maxIterations) {
      double fx = function.evaluate(current);
      double dfx = function.derivative(current);

      if (Math.abs(dfx) < DERIVATIVE_EPS) {
        throw new ArithmeticException("Производная слишком близка к нулю.");
      }

      double next = current - fx / dfx;
      error = Math.abs(next - current);

      if (error < tolerance || Math.abs(fx) < tolerance) {
        return new NumericalResult(next, iteration + 1, error, true);
      }

      current = next;
      iteration++;
    }

    throw new ConvergenceException("Метод Ньютона не сошёлся за заданное число итераций.");
  }

  private static void validateInput(
      DifferentiableFunction function,
      double tolerance,
      int maxIterations
  ) {
    if (function == null) {
      throw new IllegalArgumentException("Функция не должна быть null.");
    }
    if (tolerance <= 0) {
      throw new IllegalArgumentException("Точность должна быть положительной.");
    }
    if (maxIterations <= 0) {
      throw new IllegalArgumentException("Число итераций должно быть положительным.");
    }
  }
}
