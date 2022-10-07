![logo](https://i.imgur.com/m7Zr8cf.png "Password4j JCA")

[![Build](https://github.com/Password4j/password4j-jca/actions/workflows/build.yml/badge.svg)](https://github.com/Password4j/password4j-jca/actions/workflows/build.yml)
[![Maven Central](https://maven-badges.herokuapp.com/maven-central/com.password4j/password4j-jca/badge.svg?color=purple)](https://maven-badges.herokuapp.com/maven-central/com.password4j/password4j-jca)
[![Java 9 or higher](https://img.shields.io/badge/JDK-9%2B-007396)](https://docs.oracle.com/javase/9/)

[![Quality Gate Status](https://sonarcloud.io/api/project_badges/measure?project=Password4j_password4j-jca&metric=alert_status)](https://sonarcloud.io/dashboard?id=Password4j_password4j-jca)
[![Security Rating](https://sonarcloud.io/api/project_badges/measure?project=Password4j_password4j-jca&metric=security_rating)](https://sonarcloud.io/dashboard?id=Password4j_password4j-jca)
[![Reliability Rating](https://sonarcloud.io/api/project_badges/measure?project=Password4j_password4j-jca&metric=reliability_rating)](https://sonarcloud.io/dashboard?id=Password4j_password4j-jca)
[![Maintainability Rating](https://sonarcloud.io/api/project_badges/measure?project=Password4j_password4j-jca&metric=sqale_rating)](https://sonarcloud.io/dashboard?id=Password4j_password4j-jca)
[![Coverage](https://sonarcloud.io/api/project_badges/measure?project=Password4j_password4j-jca&metric=coverage)](https://sonarcloud.io/dashboard?id=Password4j_password4j-jca)

This module extends the JCA (Java Cryptography Library) with **[Argon2](https://en.wikipedia.org/wiki/Argon2)**, **[bcrypt](https://en.wikipedia.org/wiki/Bcrypt)** and **[scrypt](https://en.wikipedia.org/wiki/Scrypt)**. It is entirely based on [Password4j](https://github.com/Password4j/password4j).


```java
Password4jProvider.enable();

SecretKeyFactory factory = SecretKeyFactory.getInstance("argon2");
Argon2KeySpec spec = new Argon2KeySpec(password, salt, memory, iterations, parallelization, length, type, version);
SecretKey key =  factory.generateSecret(spec);

byte[] hash = key.getEncoded();
```

# Installation
Password4j-jca runs on **Java 9 or higher versions** by any vendor.

The artifacts are deployed to [Maven Central](https://search.maven.org/).
## ![Maven](https://i.imgur.com/2TZzobp.png?1) Maven
Add the dependency of the latest version to your `pom.xml`:
```xml
<dependency>
    <groupId>com.password4j</groupId>
    <artifactId>password4j-jca</artifactId>
    <version>1.0.2</version>
</dependency>
```

## ![Gradle](https://i.imgur.com/qtc6bXq.png?1) Gradle
Add to your `build.gradle` module dependencies:
```groovy
repositories {
    mavenCentral()
}

dependencies {
    implementation 'com.password4j:password4j-jca:1.0.2'
}
```

# Contributing
![GitHub issues](https://img.shields.io/github/issues/Password4j/password4j-jca?color=success)
![GitHub closed issues](https://img.shields.io/github/issues-closed/Password4j/password4j-jca)

Please read [CONTRIBUTING.md](CONTRIBUTING.md) for details on our code of conduct, and the process for submitting pull requests to us.

# Versioning
![SemVer 2.0.0](https://img.shields.io/badge/SemVer-2.0.0-orange)

We use [SemVer](http://semver.org/) for versioning.

For the versions available, see the [releases on this repository](https://github.com/Password4j/password4j/releases).

# Authors
![GitHub contributors](https://img.shields.io/github/contributors/Password4j/password4j-jca)

* **David Bertoldi** - *Main Maintainer* - [firaja](https://github.com/firaja)

See also the list of [contributors](https://github.com/Password4j/password4j/contributors) who participated in this project.

# License
[![License](https://img.shields.io/badge/License-Apache%202.0-blue.svg)](https://opensource.org/licenses/Apache-2.0)

This project is licensed under the Apache License 2.0 License - see the [LICENSE](LICENSE) file for details


# Changelog
![GitHub Release Date](https://img.shields.io/github/release-date/Password4j/password4j-jca)

See the [CHANGELOG.md](CHANGELOG.md) file for a more detailed description of each release.
