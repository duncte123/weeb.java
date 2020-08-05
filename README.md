[version]: https://api.bintray.com/packages/duncte123/weeb.java/weeb.java/images/download.svg
[download]: https://bintray.com/duncte123/weeb.java/weeb.java/_latestVersion
[ciBadge]: https://travis-ci.org/duncte123/weeb.java.svg?branch=master
[ciUrl]: https://travis-ci.org/duncte123/weeb.java

## Weeb.java [![Build Status][ciBadge]][ciUrl]
A java wrapper for [weeb.sh](https://weeb.sh/)

## TODO:
- switch from org.json to jackson\
- write write documentation and better examples

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
    <url>http://jcenter.bintray.com</url>
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
More examples are provided in the examples directory

Building the api:
```JAVA
WeebApi api = new WeebApiBuilder(TokenType.WOLKETOKENS)
                .setToken("TOKEN")
                .build();
```

Getting an image by type:
```JAVA
WeebImage imageByType = api.getRandomImage("hug").execute();
```

**NOTE:** `.execute()` is a blocking request, if you prefer asynchronous requests use `.async(Consumer)`

Getting an image by the image id:
```JAVA
api.getImageById("H1mOU3auZ").async( (image) -> {
    System.out.println(image.getUrl());
} );
```

## Libs used
- Ason: [LINK](https://github.com/afollestad/ason)
- OkHttp: [LINK](https://github.com/square/okhttp)
- Reliqua: [LINK](https://github.com/natanbc/reliqua)
