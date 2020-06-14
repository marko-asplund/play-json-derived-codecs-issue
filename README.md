# Demonstrate issue related to play-json-derived-codecs upgrade

When upgrading from play-json-derived-codecs v6.0.0 to v7.0.0
Play JSON picks up a different JSON serializer in a scenario involving
sum types when codecs for both the concrete cases classes and the sealed trait are in
scope.

## play-json-1
library versions
* play-json-derived-codecs v6.0.0
* play-json v2.6.13
* play-json-extensions v0.40.2


```
play-json-derived-codecs-issue % cd play-json-1
play-json-1 % sbt
[info] welcome to sbt 1.3.12 (Azul Systems, Inc. Java 1.8.0_222)
[info] loading global plugins from $HOME/.sbt/1.0/plugins
[info] loading project definition from $HOME/code/play-json-derived-codecs-issue/play-json-1/project
[info] loading settings for project play-json-1 from build.sbt ...
[info] set current project to play-json-1 (in build file:$HOME/code/play-json-derived-codecs-issue/play-json-1/)
[info] sbt server started at local://$HOME/.sbt/1.0/server/d03091995a02ae180633/sock
sbt:play-json-1> runMain foo.DemoMain
[warn] There may be incompatibilities among your library dependencies; run 'evicted' to see detailed eviction warnings.
[info] Compiling 1 Scala source to $HOME/code/play-json-derived-codecs-issue/play-json-1/target/scala-2.12/classes ...
[warn] Compile / run / javaOptions will be ignored, Compile / run / fork is set to false
[info] running foo.DemoMain
{"Bar":{"s":"hello","i":55}}
[success] Total time: 6 s, completed Jun 14, 2020 11:12:40 AM
sbt:play-json-1> [info] shutting down sbt server
```

Resulting JSON:
```
{"Bar":{"s":"hello","i":55}}
```

## play-json-2
library versions
* play-json-derived-codecs v7.0.0
* play-json v2.8.1
* play-json-extensions v0.42.0

```
play-json-1 % cd play-json-2
play-json-2 % sbt
[info] welcome to sbt 1.3.12 (Azul Systems, Inc. Java 1.8.0_222)
[info] loading global plugins from $HOME/.sbt/1.0/plugins
[info] loading project definition from $HOME/code/play-json-derived-codecs-issue/play-json-2/project
[info] loading settings for project play-json-2 from build.sbt ...
[info] set current project to play-json-2 (in build file:$HOME/code/play-json-derived-codecs-issue/play-json-2/)
[info] sbt server started at local://$HOME/.sbt/1.0/server/3136f81ec15e805aeb34/sock
sbt:play-json-2> runMain foo.DemoMain
[info] Compiling 1 Scala source to $HOME/code/play-json-derived-codecs-issue/play-json-2/target/scala-2.12/classes ...
[warn] Compile / run / javaOptions will be ignored, Compile / run / fork is set to false
[info] running foo.DemoMain
{"s":"hello","i":55}
```

Resulting JSON:
```
{"s":"hello","i":55}
```
