![logo](https://i.imgur.com/m7Zr8cf.png "Password4j JCA")

[![Build Status](https://travis-ci.org/Password4j/password4j-jca.svg?branch=main)](https://travis-ci.org/Password4j/password4j-jca)
[![Maven Central](https://img.shields.io/badge/maven_central-coming_soon-purple)](https://docs.oracle.com/javase/9/)
[![Java 9 or higher](https://img.shields.io/badge/JDK-9%2B-007396)](https://docs.oracle.com/javase/9/)

[![Quality Gate Status](https://sonarcloud.io/api/project_badges/measure?project=Password4j_password4j-jca&metric=alert_status)](https://sonarcloud.io/dashboard?id=Password4j_password4j-jca)
[![Security Rating](https://sonarcloud.io/api/project_badges/measure?project=Password4j_password4j-jca&metric=security_rating)](https://sonarcloud.io/dashboard?id=Password4j_password4j-jca)
[![Reliability Rating](https://sonarcloud.io/api/project_badges/measure?project=Password4j_password4j-jca&metric=reliability_rating)](https://sonarcloud.io/dashboard?id=Password4j_password4j-jca)
[![Maintainability Rating](https://sonarcloud.io/api/project_badges/measure?project=Password4j_password4j-jca&metric=sqale_rating)](https://sonarcloud.io/dashboard?id=Password4j_password4j-jca)
[![Coverage](https://sonarcloud.io/api/project_badges/measure?project=Password4j_password4j-jca&metric=coverage)](https://sonarcloud.io/dashboard?id=Password4j_password4j-jca)

This module extends the JCA (Java Cryptography Library) with **[Argon2](https://en.wikipedia.org/wiki/Argon2)**, **[bcrypt](https://en.wikipedia.org/wiki/Bcrypt)** and **[scrypt](https://en.wikipedia.org/wiki/Scrypt)**. It is entirely based on [Password4j](https://github.com/Password4j/password4j).


```java
Password4jProvider.enable();

int memory = 4096;
int iterations = 50;
int parallelization = 2;
int length = 64;
Argon2 type = Argon2.D;

SecretKeyFactory factory = SecretKeyFactory.getInstance("argon2");
SecretKey key =  factory.generateSecret(spec);

byte[] hash = key.getEncoded();
```
