## Weeb.java
A java wrapper for [weeb.sh](https://weeb.sh/)

## Instalation & documentation
You can install the wrapper with the following dependency managers
The repo can be downloaded from jitpack
The docs are available [here](https://jitpack.io/com/github/duncte123/weeb.java/master-SNAPSHOT/javadoc/)


#### Maven
```XML
<repositories>
    <repository>
        <id>jitpack.io</id>
        <url>https://jitpack.io</url>
    </repository>
</repositories>

<dependency>
    <groupId>com.github.duncte123</groupId>
    <artifactId>weeb.java</artifactId>
    <version>master-SNAPSHOT</version>
</dependency>
```

#### Gradle
```GRADLE
repositories {
    jcenter()
    maven { url 'https://jitpack.io' }
}
dependencies {
    compile 'com.github.duncte123:weeb.java:master-SNAPSHOT'
}
```
To force gradle to get a new download when you build use `gradle build --refresh-dependencies`


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
