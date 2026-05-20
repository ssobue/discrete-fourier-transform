package jp.sobue.math;

import java.util.Arrays;
import java.util.Objects;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 離散フーリエ変換(DFT)を計算する.
 *
 * @author ssobue
 */
public class DiscreteFourierTransform {

  /** ロガー. */
  private static final Logger LOGGER = LoggerFactory.getLogger(DiscreteFourierTransform.class);

  /** デモ用の入力信号. */
  private static final double[] DEFAULT_SIGNAL = {1.0, 0.0, -1.0, 0.0};

  /**
   * Main method.
   *
   * @param args Command line arguments. Values are parsed as a real-valued signal.
   */
  public static void main(String[] args) {
    double[] signal = args.length == 0 ? DEFAULT_SIGNAL : parseSignal(args);
    Complex[] spectrum = transform(signal);

    LOGGER.info("Signal = {}", Arrays.toString(signal));
    LOGGER.info("Spectrum = {}", Arrays.toString(spectrum));
  }

  /**
   * 実数列を離散フーリエ変換する.
   *
   * @param samples 入力信号
   * @return 周波数成分
   */
  static Complex[] transform(double[] samples) {
    Objects.requireNonNull(samples, "samples");
    Complex[] complexSamples = new Complex[samples.length];
    for (int index = 0; index < samples.length; index++) {
      complexSamples[index] = new Complex(samples[index], 0.0);
    }
    return transform(complexSamples);
  }

  /**
   * 複素数列を離散フーリエ変換する.
   *
   * @param samples 入力信号
   * @return 周波数成分
   */
  static Complex[] transform(Complex[] samples) {
    validateSamples(samples);
    Complex[] spectrum = new Complex[samples.length];
    for (int frequency = 0; frequency < samples.length; frequency++) {
      spectrum[frequency] = calculateFrequency(samples, frequency, -1.0);
    }
    return spectrum;
  }

  /**
   * 逆離散フーリエ変換する.
   *
   * @param spectrum 周波数成分
   * @return 復元した信号
   */
  static Complex[] inverseTransform(Complex[] spectrum) {
    validateSamples(spectrum);
    Complex[] samples = new Complex[spectrum.length];
    for (int time = 0; time < spectrum.length; time++) {
      samples[time] = calculateFrequency(spectrum, time, 1.0).divide(spectrum.length);
    }
    return samples;
  }

  private static Complex calculateFrequency(Complex[] samples, int frequency, double direction) {
    Complex sum = new Complex(0.0, 0.0);
    for (int time = 0; time < samples.length; time++) {
      double angle = direction * 2.0 * Math.PI * frequency * time / samples.length;
      Complex basis = new Complex(Math.cos(angle), Math.sin(angle));
      sum = sum.add(samples[time].multiply(basis));
    }
    return sum;
  }

  private static void validateSamples(Complex[] samples) {
    Objects.requireNonNull(samples, "samples");
    if (samples.length == 0) {
      throw new IllegalArgumentException("samples must not be empty");
    }
    for (Complex sample : samples) {
      Objects.requireNonNull(sample, "sample");
    }
  }

  private static double[] parseSignal(String[] args) {
    double[] signal = new double[args.length];
    for (int index = 0; index < args.length; index++) {
      signal[index] = Double.parseDouble(args[index]);
    }
    return signal;
  }

}
