# Discrete Fourier Transform

[![Quality Gate Status](https://sonarcloud.io/api/project_badges/measure?project=ssobue_discrete-fourier-transform&metric=alert_status)](https://sonarcloud.io/summary/new_code?id=ssobue_discrete-fourier-transform&branch=master)
[![Coverage](https://sonarcloud.io/api/project_badges/measure?project=ssobue_discrete-fourier-transform&metric=coverage)](https://sonarcloud.io/summary/new_code?id=ssobue_discrete-fourier-transform&branch=master)

This project calculates the Discrete Fourier Transform (DFT). It is a compact
Maven project written in Java.

## Repository Structure
```
discrete-fourier-transform/
  ├── pom.xml          # Maven build configuration
  ├── src/main/java/   # Java source code
  │   └── jp/sobue/math/DiscreteFourierTransform.java
  └── .github/         # CI settings (GitHub Actions)
```
GitHub Actions runs `mvn --batch-mode verify` with SonarQube Cloud analysis on
each push and pull request.

## Main Components
### DiscreteFourierTransform.java
- Calculates forward DFT for real or complex-valued samples.
- Calculates inverse DFT to reconstruct the original signal.
- Represents complex values with a compact Java record.
- After execution it prints the input signal and calculated spectrum.

### pom.xml
- Uses Java 25.
- Includes Checkstyle and PMD for static analysis.
- Includes SonarQube Cloud project properties for CI analysis.
- `exec-maven-plugin` executes `DiscreteFourierTransform` during the `verify`
  phase.

## Key Points
1. **Build and Run**
   - Execute `mvn verify` to compile the code, run Checkstyle/PMD, and run
     `DiscreteFourierTransform`.
2. **Development Style**
   - Coding standards are enforced with Checkstyle and PMD. Builds fail if
     violations remain.
3. **Continuous Integration**
   - GitHub Actions automatically runs the Maven build and SonarQube Cloud
     analysis on each push and pull request.
4. **Code Size**
   - Only one Java file is included, making it easy to understand the project
     quickly.

## Further Learning
- **DFT Basics** - Review how a time-domain signal is decomposed into
  frequency-domain components.
- **Inverse Transform** - Compare the forward and inverse formulas to see how
  the original signal is reconstructed.
- **Maven Plugin Configuration** - Explore the various plugins in `pom.xml`,
  such as Checkstyle, PMD, and the exec plugin.
- **CI/CD Workflow** - The GitHub Actions workflow is minimal and can be
  extended to include tests or artifact publishing.

Start by reading `pom.xml` and `DiscreteFourierTransform.java`, then run
`mvn verify` to see the project in action. The code base is small, so getting
familiar with it should not take long.
