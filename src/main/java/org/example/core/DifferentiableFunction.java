package org.example.core;

/**
 * Интерфейс для представления дифференцируемой функции.
 */
public interface DifferentiableFunction extends MathFunction {

  double derivative(double x);
}
