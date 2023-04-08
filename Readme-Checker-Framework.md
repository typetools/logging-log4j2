# Evidence that Log4J has no regular expression errors.

1. Obtain the Log4J source code:

```
git clone https://github.com/typetools/logging-log4j.git --branch regex
cd logging-log4j
```

2. Build the Log4J API:

```
(cd log4j-api-java9 && mvn -e -B clean && mvn -e -B compile install -P checkerframework)
(cd log4j-api && mvn -e -B clean && mvn -e -B compile install -P checkerframework)
```

3. Run the Regex Checker:

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

There are two `@SuppressWarnings("regex") // BUG` annotations that indicate a potential bug:  the code uses an arbitrary string as a regular expression without checking its syntax.

The lack of error messages in the output console log (and the correctness of other `@SuppressWarnings("regex")` in the source code) proves that the project contains no other resource leaks.

4. Examine the changes made to the source code to support the Resource Leak Checker.

https://github.com/apache/logging-log4j2/compare/2.x...typetools:logging-log4j2:regex?expand=1

