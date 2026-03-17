package linear;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import org.example.linear.GaussianElimination;
import org.junit.jupiter.api.Test;

class GaussianEliminationTest {

  @Test
  void shouldSolveLinearSystem() {
    double[][] a = {
        {2, 1, -1},
        {-3, -1, 2},
        {-2, 1, 2}
    };
    double[] b = {8, -11, -3};

    double[] solution = GaussianElimination.solve(a, b);

    assertArrayEquals(new double[]{2, 3, -1}, solution, 1e-9);
  }
}
