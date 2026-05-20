package dev.sobue.math;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class DiscreteFourierTransformTest {

  private static final double TOLERANCE = 1.0E-10;

  @Test
  @DisplayName("Run application: command-line samples should execute the logged result flow")
  void runApplication() {
    assertDoesNotThrow(() -> DiscreteFourierTransform.main(new String[] {"1", "0", "-1", "0"}));
  }

  @Test
  @DisplayName("Create calculator: the default constructor should be available for package-level use")
  void createCalculator() {
    DiscreteFourierTransform calculator = new DiscreteFourierTransform();

    assertNotNull(calculator);
  }

  @Test
  @DisplayName("Transform impulse: a unit impulse should produce a flat spectrum")
  void transformImpulse() {
    Complex[] spectrum = DiscreteFourierTransform.transform(new double[] {1.0, 0.0, 0.0, 0.0});

    assertComplexEquals(new Complex(1.0, 0.0), spectrum[0]);
    assertComplexEquals(new Complex(1.0, 0.0), spectrum[1]);
    assertComplexEquals(new Complex(1.0, 0.0), spectrum[2]);
    assertComplexEquals(new Complex(1.0, 0.0), spectrum[3]);
  }

  @Test
  @DisplayName("Transform alternating signal: energy should appear in the Nyquist bin")
  void transformAlternatingSignal() {
    Complex[] spectrum = DiscreteFourierTransform.transform(new double[] {1.0, -1.0, 1.0, -1.0});

    assertComplexEquals(new Complex(0.0, 0.0), spectrum[0]);
    assertComplexEquals(new Complex(0.0, 0.0), spectrum[1]);
    assertComplexEquals(new Complex(4.0, 0.0), spectrum[2]);
    assertComplexEquals(new Complex(0.0, 0.0), spectrum[3]);
  }

  @Test
  @DisplayName("Invert spectrum: inverse DFT should reconstruct the original signal")
  void invertSpectrum() {
    Complex[] expected = {
      new Complex(1.0, 0.0),
      new Complex(0.0, 0.0),
      new Complex(-1.0, 0.0),
      new Complex(0.0, 0.0)
    };

    Complex[] spectrum = DiscreteFourierTransform.transform(expected);
    Complex[] actual = DiscreteFourierTransform.inverseTransform(spectrum);

    assertEquals(expected.length, actual.length);
    for (int index = 0; index < expected.length; index++) {
      assertComplexEquals(expected[index], actual[index]);
    }
  }

  @Test
  @DisplayName("Reject invalid samples: empty and null input should fail fast")
  void rejectInvalidSamples() {
    assertThrows(NullPointerException.class, () -> DiscreteFourierTransform.transform((double[]) null));
    assertThrows(
        IllegalArgumentException.class,
        () -> DiscreteFourierTransform.transform(new Complex[0]));
    assertThrows(
        NullPointerException.class,
        () -> DiscreteFourierTransform.transform(new Complex[] {null}));
  }

  @Test
  @DisplayName("Calculate complex number: arithmetic should combine real and imaginary parts")
  void calculateComplexNumber() {
    Complex left = new Complex(1.0, 2.0);
    Complex right = new Complex(3.0, 4.0);

    assertEquals(new Complex(4.0, 6.0), left.add(right));
    assertEquals(new Complex(-5.0, 10.0), left.multiply(right));
    assertEquals(new Complex(0.5, 1.0), left.divide(2.0));
  }

  private static void assertComplexEquals(Complex expected, Complex actual) {
    assertEquals(expected.real(), actual.real(), TOLERANCE);
    assertEquals(expected.imaginary(), actual.imaginary(), TOLERANCE);
  }
}
