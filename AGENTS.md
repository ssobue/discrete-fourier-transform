# Repository Guidelines

## Project Structure & Module Organization
- `pom.xml`: Maven configuration (Java 25, Checkstyle, PMD, Surefire, Exec).
- `src/main/java/jp/sobue/math/DiscreteFourierTransform.java`: Main implementation.
- `src/test/java/...`: JUnit 5 tests (add here).
- `.github/workflows/main.yaml`: CI runs `mvn --batch-mode verify`.
- `target/`: Build artifacts (generated).

## Build, Test, and Development Commands
- Build and run: `mvn clean verify`
  - Compiles, runs Checkstyle/PMD, executes tests, and runs the app via exec plugin.
- Run only tests: `mvn test`
- Package (skip tests): `mvn -DskipTests package`
- Run app during development: `mvn -q exec:java`
- Static analysis only: `mvn -q checkstyle:check pmd:check`

Prerequisite: Use JDK 25 (`actions/setup-java@v5` sets this in CI).

## Coding Style & Naming Conventions
- Style enforced by Checkstyle (Google checks) and PMD; builds fail on violations.
- Indentation and formatting: follow Google Java Style; use UTF-8 encoding.
- Naming: `PascalCase` classes, `camelCase` methods/fields, `UPPER_SNAKE_CASE` constants.
- Packages under `jp.sobue.math`; keep classes small and focused.

## Testing Guidelines
- Framework: JUnit Jupiter (JUnit 5).
- Location: `src/test/java/...` mirroring package structure.
- Naming: Class ends with `Test` (e.g., `DiscreteFourierTransformTest`).
- Run: `mvn test` or as part of `mvn verify`.
- Aim for fast, deterministic tests; cover core math and edge cases. No coverage threshold enforced yet.

## Commit & Pull Request Guidelines
- Commits: concise, imperative present tense (e.g., "Add DFT calculator").
- Branches: `feature/...`, `fix/...`, `chore/...`, `deps/...` are encouraged.
- PRs: include a clear summary, rationale, before/after notes, how to run, and linked issues. Ensure CI is green.

## Notes on Performance & CI
- DFT is O(n^2); prefer small sample counts locally when iterating.
- CI executes `mvn --batch-mode verify` on push and pull requests, and runs SonarCloud
  analysis only when the workflow has a usable token; keep builds quick and deterministic.
