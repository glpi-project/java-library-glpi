---
layout: post
wiki: true
published: true
title: Examples per API Method
permalink: wiki/examples-per-api-method
description: Code examples per API Method
---
## Usage

Here we present you the code examples of the library per API Method:

* [initSession()](#iS)
* [killSession()](#kS)
* [getMyProfiles()](#gMP)
* [getActiveProfile()](#gAP)
* [changeActiveProfile()](#cAP)
* [getMyEntities()](#gME)
* [getActiveEntities()](#gAE)
* [changeActiveEntities()](#cAE)
* [getFullSession()](#gFS)
* [getGlpiConfig()](#gGC)
* [getItem()](#gI)
* [getAllItems()](#gAI)
* [getSubItems()](#gSI)
* [addItems()](#aI)
* [updateItems()](#uI)
* [deleteItems()](#dI)
* [recoveryPassword()](#ryP)
* [resetPassword()](#rP)

## Examples

* <a name="iS"></a> initSession(): this is an example of how to init session using the API Client.

    * initSessionByCredentials: this way you require the user and password to access GLPI

```java
 final GLPI glpi = new GLPI(MainActivity.this, BuildConfig.GLPI_URL);

            @Override
            public void onClick(View v) {
                glpi.initSessionByCredentials(BuildConfig.GLPI_USER, BuildConfig.GLPI_PASSWORD, new GLPI.InitSessionCallback() {
                    @Override
                    public void onResponse(InitSession response) {
                        FlyveLog.i("initSession: %s", response.getSessionToken());
                    }

                    @Override
                    public void onFailure(String errorMessage) {
                        FlyveLog.e("initSession: %s", errorMessage);
                    }
                });
            }
```
* <a name="kS"></a> killSession(): close the user session

```java
 final GLPI glpi = new GLPI(MainActivity.this, BuildConfig.GLPI_URL);

            @Override
            public void onClick(View v) {

                glpi.killSession(new GLPI.VoidCallback() {
                    @Override
                    public void onResponse(String response) {
                        FlyveLog.i("killSession: %s", response.toString());
                    }

                    @Override
                    public void onFailure(String errorMessage) {
                        FlyveLog.e("killSession: %s", errorMessage);
                    }
                });
            }
```
* <a name="gMP"></a> getMyProfiles(): return the profiles associated to the user

```java

glpi.getMyProfiles(new GLPI.JsonObjectCallback() {
                    @Override
                    public void onResponse(JsonObject response) {
                        FlyveLog.i("getMyProfiles: %s", response.toString());
                    }

                    @Override
                    public void onFailure(String errorMessage) {
                        FlyveLog.e("getMyProfiles: %s", errorMessage);
                    }
                });
```
* <a name="gAP"></a> getActiveProfile(): return the current active profile

```java
glpi.getActiveProfile(new GLPI.JsonObjectCallback() {
                    @Override
                    public void onResponse(JsonObject response) {
                        FlyveLog.i("getActiveProfile: %s", response.toString());
                    }

                    @Override
                    public void onFailure(String errorMessage) {
                        FlyveLog.e("getActiveProfile: %s", errorMessage);
                    }
                });
```
* <a name="cAP"></a> changeActiveProfile(): change active profile to one of the profiles obtained with 'getMyProfiles()'

```java

 glpi.changeActiveProfile("9", new GLPI.VoidCallback() {
                    @Override
                    public void onResponse(String response) {
                        FlyveLog.i("changeActiveProfile: %s", response);
                    }

                    @Override
                    public void onFailure(String errorMessage) {
                        FlyveLog.e("changeActiveProfile: %s", errorMessage);
                    }
                });
```

* <a name="gME"></a> getMyEntities(): return all the possible entities of the current logged user

```java
glpi.getMyEntities(new GLPI.JsonObjectCallback() {
                    @Override
                    public void onResponse(JsonObject response) {
                        FlyveLog.i("getMyEntities: %s", response.toString());
                    }

                    @Override
                    public void onFailure(String errorMessage) {
                        FlyveLog.e("getMyEntities: %s", errorMessage);
                    }
                });
```
* <a name="gAE"></a> getActiveEntities(): return active entities of current logged usergetActiveEntities()

```java
glpi.getActiveEntities(new GLPI.JsonObjectCallback() {
                    @Override
                    public void onResponse(JsonObject response) {
                        FlyveLog.i("getActiveEntities: %s", response.toString());
                    }

                    @Override
                    public void onFailure(String errorMessage) {
                        FlyveLog.e("getActiveEntities: %s", errorMessage);
                    }
                });
```

* <a name="cAE"></a> changeActiveEntities(): change active entity to one of the entities obtained with 'getMyEntities'

```java
 glpi.changeActiveEntities("1", false, new GLPI.VoidCallback() {
                    @Override
                    public void onResponse(String response) {
                        FlyveLog.i("changeActiveEntities: %s", response);
                    }

                    @Override
                    public void onFailure(String errorMessage) {
                        FlyveLog.e("changeActiveEntities: %s", errorMessage);
                    }
                });
```
* <a name="gFS"></a> getFullSession(): return the current php $_SESSION

```java
glpi.getFullSession(new GLPI.JsonObjectCallback() {
                    @Override
                    public void onResponse(JsonObject response) {
                        FlyveLog.i("getFullSession: %s", response.toString());
                    }

                    @Override
                    public void onFailure(String errorMessage) {
                        FlyveLog.e("getFullSession: %s", errorMessage);
                    }
                });
```

* <a name="gGC"></a> getGlpiConfig(): return the current $CFG_GLPI

```java
glpi.getGlpiConfig(new GLPI.JsonObjectCallback() {
                    @Override
                    public void onResponse(JsonObject response) {
                        FlyveLog.i("getGlpiConfig: %s", response.toString());
                    }

                    @Override
                    public void onFailure(String errorMessage) {
                        FlyveLog.e("getGlpiConfig: %s", errorMessage);
                    }
                });
```
* <a name="gI"></a> getItem(): return the instance fields of itemtype identified by id

```java
glpi.getItem(itemType.Computer, "110", new GLPI.JsonObjectCallback() {
                    @Override
                    public void onResponse(JsonObject response) {
                        FlyveLog.i("getAnItem: %s", response.toString());
                    }

                    @Override
                    public void onFailure(String errorMessage) {
                        FlyveLog.e("getAnItem: %s", errorMessage);
                    }
                });
```

* <a name="gAI"></a> getAllItems(): return a collection of rows of the itemtype

```java
glpi.getAllItems(itemType.Computer, new GLPI.JsonArrayCallback() {
                    @Override
                    public void onResponse(JsonArray response) {
                        FlyveLog.i("getAllItems: %s", response.toString());
                    }

                    @Override
                    public void onFailure(String errorMessage) {
                        FlyveLog.e("getAllItems: %s", errorMessage);
                    }
                });
```

* <a name="gSI"></a> getSubItems(): return a collection of rows of the sub_itemtype for the identified item

```java
glpi.getSubItems(itemType.Computer, "2", itemType.ComputerType, new GLPI.JsonObjectCallback() {
                    @Override
                    public void onResponse(JsonObject response) {
                        FlyveLog.i("getSubItems", response.toString());
                    }

                    @Override
                    public void onFailure(String errorMessage) {
                        FlyveLog.e("getSubItems: %s", errorMessage);
                    }
                });
```
* <a name="aI"></a> addItems(): add an object (or multiple objects) into GLPI

```java
ArrayList<addItemExamplePayload.data> list = new ArrayList<>();
                list.add(item1);
                list.add(item2);
                list.add(item3);

                addItemExamplePayload obj = new addItemExamplePayload(list);

                glpi.addItems(itemType.Computer, obj, new GLPI.JsonArrayCallback() {
                    @Override
                    public void onResponse(JsonArray response) {
                        FlyveLog.i("addItems: %s", response.toString());
                    }

                    @Override
                    public void onFailure(String errorMessage) {
                        FlyveLog.e("addItems: %s", errorMessage);
                    }
                });
```
* <a name="uI"></a> updateItems(): update an object (or multiple objects) existing in GLPI

```java
glpi.updateItems(itemType.Computer, "10", obj, new GLPI.JsonArrayCallback() {
                    @Override
                    public void onResponse(JsonArray response) {
                        FlyveLog.i("updateItems: %s", response.toString());
                    }

                    @Override
                    public void onFailure(String errorMessage) {
                        FlyveLog.e("updateItems: %s", errorMessage);
                    }
                });
```
* <a name="dI"></a> deleteItems(): delete an object existing in GLPI

```java
ArrayList<deleteItemExamplePayload.data> deleteList = new ArrayList<>();
                deleteList.add(deleteItem1);
                deleteList.add(deleteItem2);
                deleteList.add(deleteItem3);

                deleteItemExamplePayload deleteObj = new deleteItemExamplePayload(deleteList);

                glpi.deleteItems(itemType.Computer, deleteObj, new GLPI.JsonArrayCallback() {
                    @Override
                    public void onResponse(JsonArray response) {
                        FlyveLog.i("deleteItems: %s", response.toString());
                    }

                    @Override
                    public void onFailure(String errorMessage) {
                        FlyveLog.e("deleteItems: %s", errorMessage);
                    }
                });
```
* <a name="ryP"></a> recoveryPassword(): sends a notification to the user to reset his password through email

```java
glpi.recoveryPassword("youremail@yourdomain.com", new GLPI.VoidCallback() {
                    @Override
                    public void onResponse(String response) {
                        FlyveLog.i("lostPassword: %s", response);
                    }

                    @Override
                    public void onFailure(String errorMessage) {
                        FlyveLog.e("lostPassword: %s", errorMessage);
                    }
                });
```
* <a name="rP"></a> resetPassword(): sends a notification to the user to reset his password

```java
glpi.resetPassword("youremail@yourdomain.com", "asdfasdfafsASDFd333A", "1234", new GLPI.VoidCallback() {
                    @Override
                    public void onResponse(String response) {
                        FlyveLog.i("recoveryPassword: %s", response);
                    }

                    @Override
                    public void onFailure(String errorMessage) {
                        FlyveLog.e("recoveryPassword: %s", errorMessage);
                    }
                });
```

For more information about the functions check our [Code Documentation](https://glpi-project.github.io/java-library-glpi/reports/javadoc/) section
