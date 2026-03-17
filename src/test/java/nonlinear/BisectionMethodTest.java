package nonlinear;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.example.core.NumericalResult;
import org.example.nonlinear.BisectionMethod;
import org.junit.jupiter.api.Test;

class BisectionMethodTest {

  @Test
  void shouldFindRootOfQuadraticEquation() {
    NumericalResult result = BisectionMethod.solve(
        x -> x * x - 2,
        1.0,
        2.0,
        1e-6,
        100
    );

    assertEquals(Math.sqrt(2), result.value(), 1e-5);
    assertTrue(result.converged());
  }
}
