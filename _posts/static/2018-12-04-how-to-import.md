---
layout: post
wiki: true
published: true
title: How to import
permalink: wiki/how-to-import
description: Add it to your project
---

You have two ways to import the GLPI Java Library to your Android project.

## External Library Manager

### Maven

```xml
<dependency>
  <groupId>org.glpi</groupId>
  <artifactId>api</artifactId>
  <version>1.0.0</version>
  <type>pom</type>
</dependency>
```

### Gradle

```groovy
compile 'org.glpi:api:1.0.0@jar'
```

### Apache Ivy

```
<dependency org='org.glpi' name='api' rev='1.0.0'>
  <artifact name='api' ext='pom' ></artifact>
</dependency>
```

You can also find us on [**Bintray repository**](https://bintray.com/glpi-project/teclib-repository/java-library-glpi).

## Manually

In your ```setting.gradle``` add the following line:

```groovy
include ':my-project', ':java-library-glpi'
```

And in your ```build.gradle``` add:

```groovy
implementation project(':java-library-glpi')
```