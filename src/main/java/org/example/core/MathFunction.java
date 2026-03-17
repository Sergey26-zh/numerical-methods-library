package org.example.core;

/**
 * Интерфейс, представляющий математическую функцию одной переменной.
 */
@FunctionalInterface
public interface MathFunction {

  double evaluate(double x);
}
