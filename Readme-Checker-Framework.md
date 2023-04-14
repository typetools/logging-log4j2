# Evidence about Log4J resource leaks.
# This is not yet evidence that Log4J has no resource leaks, because Log4J has resource leaks.

1. Obtain the Log4J source code:

```
git clone https://github.com/typetools/logging-log4j.git --branch resourceleak
cd logging-log4j
```

3. Run the Resource Leak Checker:

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

Search for "mustcall" and "resourceleak" within a `@SuppressWarnings` annotation.

Some of these indicate resource leaks.

Eventually, the lack of error messages in the output console log (and the correctness of `@SuppressWarnings("resourceleak")` in the source code) proves that the project contains no resource leaks.

4. Examine the changes made to the source code to support the Resource Leak Checker.

https://github.com/apache/logging-log4j2/compare/2.x...typetools:logging-log4j2:resourceleak?expand=1

5. In addition, some resource leaks have been fixed after we reported them:
https://github.com/apache/logging-log4j2/pull/1415#issuecomment-1502225714
