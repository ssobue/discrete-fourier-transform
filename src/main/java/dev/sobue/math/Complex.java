package dev.sobue.math;

/**
 * 複素数.
 *
 * @param real 実部
 * @param imaginary 虚部
 */
record Complex(double real, double imaginary) {

  Complex add(Complex other) {
    return new Complex(real + other.real, imaginary + other.imaginary);
  }

  Complex multiply(Complex other) {
    return new Complex(
        (real * other.real) - (imaginary * other.imaginary),
        (real * other.imaginary) + (imaginary * other.real));
  }

  Complex divide(double divisor) {
    return new Complex(real / divisor, imaginary / divisor);
  }
}
