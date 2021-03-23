[version]: https://img.shields.io/maven-metadata/v?metadataUrl=https%3A%2F%2Fduncte123.jfrog.io%2Fartifactory%2Fmaven%2Fme%2Fduncte123%2FweebJava%2Fmaven-metadata.xml
[download]: https://duncte123.jfrog.io/ui/packages/gav:%2F%2Fme.duncte123:weebJava
[ciBadge]: https://travis-ci.org/duncte123/weeb.java.svg?branch=master
[ciUrl]: https://travis-ci.org/duncte123/weeb.java

## Weeb.java [![Build Status][ciBadge]][ciUrl]
A java wrapper for [weeb.sh](https://weeb.sh/)

## TODO:
- write documentation and better examples

## Instalation & documentation
You can install the wrapper with the following dependency managers.

The repo can be downloaded from jcenter.

The docs are available <a href="https://jitpack.io/com/github/duncte123/weeb.java/master-SNAPSHOT/javadoc/" target="_blank">here</a>

The current latest version is: [ ![version][] ][download]

#### Maven
```XML
<repository>
    <id>duncte123</id>
    <name>duncte123-maven</name>
    <url>https://duncte123.jfrog.io/artifactory/maven</url>
</repository>

<dependency>
  <groupId>me.duncte123</groupId>
  <artifactId>weebJava</artifactId>
  <version>[LATEST-VERSION]</version>
</dependency>
```

#### Gradle
```GRADLE
repositories {
    maven {
        url = 'https://duncte123.jfrog.io/artifactory/maven/'
    }
}
dependencies {
    compile 'me.duncte123:weebJava:[LATEST-VERSION]'
}
```
Make sure to replace `[LATEST-VERSION]` with whatever the latest version is


## Examples
Examples for all methods can be found in the `test_but_stop_running` directory

## Libs used
- Jackson: [LINK](https://github.com/FasterXML/jackson-databind)
- OkHttp: [LINK](https://github.com/square/okhttp)
- Reliqua: [LINK](https://github.com/duncte123/reliqua)
