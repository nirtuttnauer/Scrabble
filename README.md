# Scrabble

Java coursework implementing components of a Book Scrabble game: board and tile
logic, word dictionaries, cache replacement policies, a Bloom filter, and a TCP
server with a dictionary request handler. This repository contains backend
components and an exercise harness, not a graphical or interactive Scrabble app.

## Requirements

- A JDK with both `java` and `javac` available on your `PATH` (verified with JDK 21).
- A POSIX shell for the commands below (macOS or Linux).
- No external Java libraries, Maven, or Gradle are required.

Check your installation with `java -version` and `javac -version`. On macOS,
a Homebrew JDK may need its `bin` directory added to `PATH` before these commands
work; the system Java launcher alone does not indicate that a JDK is installed.

## Compile and run the exercise harness

From the repository root:

```sh
BUILD_DIR="$(mktemp -d)"
RUN_DIR="$(mktemp -d)"
javac -d "$BUILD_DIR" src/test/*.java
(cd "$RUN_DIR" && java -cp "$BUILD_DIR" test.MainTrain)
```

Keep both directory variables in the same shell. Compilation uses the source
files rather than the committed IntelliJ output under `out/`.

The separate run directory is intentional: `MainTrain` creates or overwrites
`t1.txt`, `t2.txt`, `t3.txt`, `s1.txt`, and `s2.txt` in its working directory.
Running directly from the repository would overwrite tracked sample files.
The temporary build and run directories can be removed after inspection.

The harness opens local TCP connections using randomly selected ports from
6000 through 6999, so local networking must be permitted and the selected ports
must be available. It normally finishes by printing `done`; inspect earlier
output for diagnostics. This is an exercise harness, not a comprehensive test
suite: failures are printed rather than consistently reported with a nonzero
exit status. In particular, `testBSCH` constructs client threads without starting
them, so completion does **not** verify the dictionary handler end to end.

## Repository layout

- `src/test/`: Java sources, all in the `test` package despite including the
  application components as well as `MainTrain`.
- `Board`, `Word`, `Tile`: board placement, scoring, words, and tile management.
- `Dictionary`, `DictionaryManager`, `IOSearcher`: dictionary lookup and file search.
- `CacheManager`, `LRU`, `LFU`, `CacheReplacementPolicy`: word caches and eviction.
- `BloomFilter`: probabilistic word membership data structure.
- `MyServer`, `ClientHandler`, `BookScrabbleHandler`: TCP server and request handling.
- `src/*.pdf`: assignment documents.
- Root-level `.txt` files: sample text and harness-generated data.
- `out/`, `.idea/`, `ED1PTM.iml`: committed IDE/build artifacts; not needed for the
  command-line build above.

## Current limitations

- `Board.dictionaryLegal` currently returns `true` unconditionally; board
  placement is not connected to dictionary validation.
- The included harness does not establish correctness of all board, cache, or
  networking behavior.
- Keep the server restricted to a trusted local development environment. It
  binds using `ServerSocket(port)` without an explicit loopback address, and
  dictionary requests accept file paths without an allowlist. It is not a
  hardened public service.

These limitations describe the existing implementation; the setup instructions
do not change game or server behavior.
