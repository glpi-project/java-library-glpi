# GLPI API Client Library for Java

This library specifically designed for Java, features several functionalities common to all GLPI APIs

## Example

This is a simple example to explain how to use the GLPI API Library

Please add this script on Gradle app and Sync the project:

```script
repositories {
    maven {
        url 'https://bintray.com/glpi-project/maven-repository'
    }
}
```

```console
compile 'org.glpi:api:0.1.0'@aar
```

Now you can run your app in simulator or device.

To check the log, type this in terminal:

```console
adb shell logcat | grep -E "org.glpi.api"
```