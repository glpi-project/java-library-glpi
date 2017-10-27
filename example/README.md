This is a simple example to explain how to used the GLPI API Library

Please add this script on Gradle app and Sync the project:

```
repositories {
    maven {
        url 'https://bintray.com/glpi-project/maven-repository'
    }
}
```

```
compile 'org.glpi:api:0.1.0'@aar
```

Now you can run your app in simulator or device then to check the log in terminal type this:

```
adb shell logcat | grep -E "org.glpi.api"
```