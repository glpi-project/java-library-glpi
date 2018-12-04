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

Soon to be published

<!-- ### Maven

```xml
<dependency>
    <groupId>org.flyve</groupId>
    <artifactId>inventory</artifactId>
    <version>1.0.0</version>
    <type>pom</type>
</dependency>
```

### Gradle

```groovy
compile 'org.flyve:inventory:1.0.0'
```

### Apache Ivy

```xml
<dependency org='org.flyve' name='inventory' rev='0.1.0'>
    <artifact name='inventory' ext='pom' ></artifact>
</dependency>
```

You can also find us on [**Bintray repository**](https://bintray.com/flyve-mdm/glpi/java-library-glpi).
-->

## Manually

In your ```setting.gradle``` add the following line:

```groovy
include ':my-project', ':java-library-glpi'
```

And in your ```build.gradle``` add:

```groovy
implementation project(':java-library-glpi')
```