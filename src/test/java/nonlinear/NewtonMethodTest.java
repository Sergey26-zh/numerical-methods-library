package nonlinear;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.example.core.DifferentiableFunction;
import org.example.core.NumericalResult;
import org.example.nonlinear.NewtonMethod;
import org.junit.jupiter.api.Test;

class NewtonMethodTest {

  @Test
  void shouldFindRootOfQuadraticEquation() {
    DifferentiableFunction function = new DifferentiableFunction() {
      @Override
      public double evaluate(double x) {
        return x * x - 2;
      }

      @Override
      public double derivative(double x) {
        return 2 * x;
      }
    };

    NumericalResult result = NewtonMethod.solve(function, 1.0, 1e-6, 100);

    assertEquals(Math.sqrt(2), result.value(), 1e-5);
    assertTrue(result.converged());
  }
}
