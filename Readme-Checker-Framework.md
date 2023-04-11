# Evidence about Log4J null pointer errors.
# This is not yet evidence that Log4J has no null pointer errors, because Log4J has null pointer errors.

1. Obtain the Log4J source code:

```
git clone https://github.com/typetools/logging-log4j.git --branch nullness
cd logging-log4j
```

3. Run the Nullness Checker:

```
cd log4j-core
mvn -e -B clean && mvn -e -B compile -P checkerframework
```


The output is

```
... [irrelevant lines omitted]

[INFO] ------------------------------------------------------------------------
[INFO] BUILD SUCCESS
[INFO] ------------------------------------------------------------------------

...
```

Search for "nullness" within a `@SuppressWarnings` annotation.

Some of these indicate null pointer errors.

Eventually, the lack of error messages in the output console log (and the correctness of `@SuppressWarnings("nullness")` in the source code) proves that the project contains no null pointer errors.

4. Examine the changes made to the source code to support the Resource Leak Checker.

https://github.com/apache/logging-log4j2/compare/2.x...typetools:logging-log4j2:nullness?expand=1

5. In addition, some nullness bugs have been fixed after we reported them:
https://github.com/apache/logging-log4j2/pull/1422
https://github.com/apache/logging-log4j2/pull/1423
