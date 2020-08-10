[version]: https://api.bintray.com/packages/duncte123/weeb.java/weeb.java/images/download.svg
[download]: https://bintray.com/duncte123/weeb.java/weeb.java/_latestVersion
[ciBadge]: https://travis-ci.org/duncte123/weeb.java.svg?branch=master
[ciUrl]: https://travis-ci.org/duncte123/weeb.java

## Weeb.java [![Build Status][ciBadge]][ciUrl]
A java wrapper for [weeb.sh](https://weeb.sh/)

## TODO:
- switch from org.json to jackson
- write documentation and better examples

## Instalation & documentation
You can install the wrapper with the following dependency managers.

The repo can be downloaded from jcenter.

The docs are available <a href="https://jitpack.io/com/github/duncte123/weeb.java/master-SNAPSHOT/javadoc/" target="_blank">here</a>

The current latest version is: [ ![version][] ][download]

#### Maven
```XML
<repository>
    <id>jcenter</id>
    <name>jcenter-bintray</name>
    <url>https://jcenter.bintray.com</url>
</repository>

<dependency>
  <groupId>me.duncte123</groupId>
  <artifactId>weebJava</artifactId>
  <version>[LATEST-VERSION]</version>
  <type>pom</type>
</dependency>
```

#### Gradle
```GRADLE
repositories {
    jcenter()
}
dependencies {
    compile 'me.duncte123:weebJava:[LATEST-VERSION]'
}
```
Make sure to replace `[LATEST-VERSION]` with whatever the latest version is


## Examples
Examples for all methods can be found in the `test_but_stop_running` directory

## Libs used
- Ason: [LINK](https://github.com/afollestad/ason)
- OkHttp: [LINK](https://github.com/square/okhttp)
- Reliqua: [LINK](https://github.com/natanbc/reliqua)
