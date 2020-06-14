# Demonstrate issues related to play-json-derived-codecs upgrade

When upgrading from play-json-derived-codecs v6.0.0 to v7.0.0
Play JSON picks up a different JSON serializer in a scenario involving
sum types when codecs for both the concrete cases classes and the sealed trait are in
scope.

## library versions

project `play-json-1`
* play-json-derived-codecs v6.0.0
* play-json v2.6.13
* play-json-extensions v0.40.2

project `play-json-2`
library versions
* play-json-derived-codecs v7.0.0
* play-json v2.8.1
* play-json-extensions v0.42.0


## issue #1

### project `play-json-1`

```
play-json-derived-codecs-issue % cd play-json-1
play-json-1 % sbt 'runMain foo.DemoMain1'
[info] welcome to sbt 1.3.12 (Azul Systems, Inc. Java 1.8.0_222)
[info] loading global plugins from $HOME/.sbt/1.0/plugins
[info] loading project definition from $HOME/code/play-json-derived-codecs-issue/play-json-1/project
[info] loading settings for project play-json-1 from build.sbt ...
[info] set current project to play-json-1 (in build file:$HOME/code/play-json-derived-codecs-issue/play-json-1/)
[warn] Multiple main classes detected.  Run 'show discoveredMainClasses' to see the list
[warn] Compile / run / javaOptions will be ignored, Compile / run / fork is set to false
[info] running foo.DemoMain1
{"Bar1":{"s":"hello","i":55}}
[success] Total time: 1 s, completed Jun 14, 2020 2:58:36 PM

```

Resulting JSON:
```
{"Bar":{"s":"hello","i":55}}
```

### project `play-json-2`

```
play-json-derived-codecs-issue % cd play-json-2
play-json-2 % sbt 'runMain foo.DemoMain1'
[info] welcome to sbt 1.3.12 (Azul Systems, Inc. Java 1.8.0_222)
[info] loading global plugins from $HOME/.sbt/1.0/plugins
[info] loading project definition from $HOME/code/play-json-derived-codecs-issue/play-json-2/project
[info] loading settings for project play-json-2 from build.sbt ...
[info] set current project to play-json-2 (in build file:$HOME/code/play-json-derived-codecs-issue/play-json-2/)
[info] Compiling 1 Scala source to $HOME/code/play-json-derived-codecs-issue/play-json-2/target/scala-2.12/classes ...
[warn] Compile / run / javaOptions will be ignored, Compile / run / fork is set to false
[info] running foo.DemoMain1
{"s":"hello","i":55}
[success] Total time: 7 s, completed Jun 14, 2020 3:01:54 PM
```

Resulting JSON:
```
{"s":"hello","i":55}
```

## issue #2

### project `play-json-1`
```
play-json-derived-codecs-issue % cd play-json-1
play-json-1 % sbt 'runMain foo.DemoMain2'
[info] welcome to sbt 1.3.12 (Azul Systems, Inc. Java 1.8.0_222)
[info] loading global plugins from $HOME/.sbt/1.0/plugins
[info] loading project definition from $HOME/code/play-json-derived-codecs-issue/play-json-1/project
[info] loading settings for project play-json-1 from build.sbt ...
[info] set current project to play-json-1 (in build file:$HOME/code/play-json-derived-codecs-issue/play-json-1/)
[warn] Multiple main classes detected.  Run 'show discoveredMainClasses' to see the list
[warn] Compile / run / javaOptions will be ignored, Compile / run / fork is set to false
[info] running foo.DemoMain2
{"Bar2":{"s":"hello","i":55}}
[success] Total time: 1 s, completed Jun 14, 2020 3:03:07 PM
```

Result: code compiles and runs.

### project `play-json-2`
```
play-json-derived-codecs-issue % git apply -R demo2-comment.diff
play-json-derived-codecs-issue % cd play-json-2
play-json-2 % sbt 'runMain foo.DemoMain2'
[info] welcome to sbt 1.3.12 (Azul Systems, Inc. Java 1.8.0_222)
[info] loading global plugins from $HOME/.sbt/1.0/plugins
[info] loading project definition from $HOME/code/play-json-derived-codecs-issue/play-json-2/project
[info] loading settings for project play-json-2 from build.sbt ...
[info] set current project to play-json-2 (in build file:$HOME/code/play-json-derived-codecs-issue/play-json-2/)
[info] Compiling 1 Scala source to $HOME/code/play-json-derived-codecs-issue/play-json-2/target/scala-2.12/classes ...
[error] $HOME/code/play-json-derived-codecs-issue/play-json-2/src/main/scala/foo/Demo.scala:43:25: No Json serializer found for type foo.Demo2.Bar2. Try to implement an implicit Writes or Format for this type.
[error]     val b2 = Json.toJson(Bar2("hello", 55))
[error]                         ^
[error] one error found
[error] (Compile / compileIncremental) Compilation failed
[error] Total time: 4 s, completed Jun 14, 2020 3:04:42 PM
```

Result: the same code doesn't compile.