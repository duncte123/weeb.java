[version]: https://api.bintray.com/packages/duncte123/weeb.java/weeb.java/images/download.svg
[download]: https://bintray.com/duncte123/weeb.java/weeb.java/_latestVersion

## Weeb.java
A java wrapper for [weeb.sh](https://weeb.sh/)

## Instalation & documentation
You can install the wrapper with the following dependency managers.

The repo can be downloaded from jcenter.

The docs are available [here](https://jitpack.io/com/github/duncte123/weeb.java/master-SNAPSHOT/javadoc/)

The current latest version is: [ ![version][] ][download]

#### Maven
```XML
<repository>
    <id>jcenter</id>
    <name>jcenter-bintray</name>
    <url>http://jcenter.bintray.com</url>
</repository>

<dependency>
  <groupId>ml.duncte123</groupId>
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
    compile 'ml.duncte123:weebJava:[LATEST-VERSION]'
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
WeebImage imageByType = api.getRandomImage("hug");
```

Getting an image by the image id:
```JAVA
WeebImage imageID = api.getImageById("H1mOU3auZ");
```

## Libs used
- Ason: [LINK](https://github.com/afollestad/ason)
- OkHttp: [LINK](https://github.com/square/okhttp)
