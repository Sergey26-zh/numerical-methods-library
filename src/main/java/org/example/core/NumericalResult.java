package org.example.core;

/**
 * Класс, представляющий результат выполнения численного метода.
 * @param value найденное приближённое значение решения
 * @param iterations количество выполненных итераций
 * @param error оценка погрешности
 * @param converged признак успешной сходимости метода
 */
public record NumericalResult(
    double value,
    int iterations,
    double error,
    boolean converged
) {

}
