/* ---------------------------------------------------------------------
*
*  LICENSE
*
*  This file is part of the GLPI API Client Library for Java,
*  a subproject of GLPI. GLPI is a free IT Asset Management.
*
*  GLPI is free software: you can redistribute it and/or
*  modify it under the terms of the GNU General Public License
*  as published by the Free Software Foundation; either version 3
*  of the License, or (at your option) any later version.
*
*  GLPI is distributed in the hope that it will be useful,
*  but WITHOUT ANY WARRANTY; without even the implied warranty of
*  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
*  GNU General Public License for more details.
*  --------------------------------------------------------------------
*  @author    Rafael Hernandez - <rhernandez@teclib.com>
*  @copyright (C) 2017 Teclib' and contributors.
*  @license   GPLv3 https://www.gnu.org/licenses/gpl-3.0.html
*  @link      https://github.com/glpi-project/java-library-glpi
*  @link      http://www.glpi-project.org/
*  --------------------------------------------------------------------
*/

package org.glpi.glpiproject;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Spinner;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.FormatStrategy;
import com.orhanobut.logger.Logger;
import com.orhanobut.logger.PrettyFormatStrategy;

import org.glpi.api.GLPI;
import org.glpi.api.itemType;
import org.glpi.api.response.FullSessionModel;
import org.glpi.api.response.InitSession;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<String> resultList = new ArrayList<>();
    private GLPI glpi;
    private ActivityAdapter activityAdapter;
    private Spinner spinnerTest;
    private ExampleData data;
    private ProgressBar progressBar;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        data = new ExampleData();
        data.setEmail("greatsupport@example.com");
        data.setEmail("5e9d551afc181984042a985fd6552ea8400c190c4f1f61d7838cf8a1b88668f3");
        data.setName("Great support");
        data.setPhone("+33 123456789");
        data.setUrl("https://dev.flyve.org/glpi/apirest.php/");
        data.setUserToken("ufC5Ct9MXVfQdEOdTp0B0Rb05t8PXNRZKcGmVIWV");
        data.setWebsite("https://mygreatsupport.com");

        FormatStrategy formatStrategy = PrettyFormatStrategy.newBuilder()
                .showThreadInfo(false)  // (Optional) Whether to show thread info or not. Default true
                .methodCount(0)         // (Optional) How many method line to show. Default 2
                .methodOffset(7)        // (Optional) Hides internal method calls up to offset. Default 5
                .tag("org.glpi.api")   // (Optional) Global tag for every log. Default PRETTY_LOGGER
                .build();

        Logger.addLogAdapter(new AndroidLogAdapter(formatStrategy));

        glpi = new GLPI(MainActivity.this, data.getUrl());

        progressBar = findViewById(R.id.progressBar);

        recyclerView = findViewById(R.id.recyclerViewApi);
        resultList.add("Ivans");
        activityAdapter = new ActivityAdapter(resultList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(activityAdapter);

        spinnerTest = findViewById(R.id.spinnerTest);
        ArrayList<String> list = new ArrayList<>();
        list.add("Init Session");
        list.add("Kill session");
        list.add("Call Request");
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, R.layout.spinner_item, list);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerTest.setAdapter(adapter);

        Button btn = findViewById(R.id.btnCall);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (spinnerTest.getSelectedItem().toString()) {
                    case "Init Session":
                        btnInit();
                        break;
                    case "Kill session":
                        btnKill();
                        break;
                    case "Call Request":
                        btnCall();
                        break;
                }
            }
        });
    }

    private void btnKill() {
        progressBar.setVisibility(View.VISIBLE);
        resultList.clear();
        glpi.killSession(new GLPI.ResponseHandle<String, String>() {
            @Override
            public void onResponse(String response) {
                FlyveLog.i("killSession: %s", response.toString());
                updateAdapter("Success: Kill Session");
                progressBar.setVisibility(View.GONE);
                recyclerView.setVisibility(View.VISIBLE);
            }

            @Override
            public void onFailure(String errorMessage) {
                FlyveLog.e("killSession: %s", errorMessage);
                updateAdapter("Error: Kill Session");
                progressBar.setVisibility(View.GONE);
                recyclerView.setVisibility(View.VISIBLE);
            }
        });
    }

    private void btnInit() {
        progressBar.setVisibility(View.VISIBLE);
        resultList.clear();
        glpi.initSessionByUserToken(data.getUserToken(), new GLPI.ResponseHandle<InitSession, String>() {
            @Override
            public void onResponse(InitSession response) {
                updateAdapter("Success: Init Session User Token");
            }

            @Override
            public void onFailure(String errorMessage) {
                updateAdapter("Error: Init Session User Token" + errorMessage);
            }
        });
        String token = glpi.initSessionByCredentialsSync(BuildConfig.GLPI_USER, BuildConfig.GLPI_PASSWORD);
        FlyveLog.i("initSession: %s", token);
        updateAdapter("Success: Init Session Credentials");
        glpi.fullSession(new GLPI.ResponseHandle<FullSessionModel, String>() {
            @Override
            public void onResponse(FullSessionModel response) {
                FlyveLog.i("Full Session: %s", response.toString());
                updateAdapter("Success: Full Session");
            }

            @Override
            public void onFailure(String errorMessage) {
                FlyveLog.i("Full Session: %s", errorMessage);
                updateAdapter("Error: Full Session");
            }
        });
        GLPI.ResponseHandle<InitSession, String> handle = new GLPI.ResponseHandle<InitSession, String>() {
            @Override
            public void onResponse(InitSession response) {
                FlyveLog.i("initSession: %s", response.getSessionToken());
                updateAdapter("Success: Init Session Credentials");
                progressBar.setVisibility(View.GONE);
                recyclerView.setVisibility(View.VISIBLE);
            }

            @Override
            public void onFailure(String errorMessage) {
                updateAdapter("Error: Init Session Credentials" + errorMessage);
                progressBar.setVisibility(View.GONE);
                recyclerView.setVisibility(View.VISIBLE);
            }
        };
        glpi.initSessionByCredentials(BuildConfig.GLPI_USER, BuildConfig.GLPI_PASSWORD, handle);
    }

    private void btnCall() {
        progressBar.setVisibility(View.VISIBLE);
        resultList.clear();
        glpi.getMyProfiles(new GLPI.ResponseHandle<JsonObject, String>() {
            @Override
            public void onResponse(JsonObject response) {
                FlyveLog.i("getMyProfiles: %s", response.toString());
                updateAdapter("Success: my profiles");
            }

            @Override
            public void onFailure(String errorMessage) {
                FlyveLog.e("getMyProfiles: %s", errorMessage);
                updateAdapter("Error: my profiles");
            }
        });

        glpi.getActiveProfile(new GLPI.ResponseHandle<JsonObject, String>()  {
            @Override
            public void onResponse(JsonObject response) {
                FlyveLog.i("getActiveProfile: %s", response.toString());
                updateAdapter("Success: activate profile");
            }

            @Override
            public void onFailure(String errorMessage) {
                FlyveLog.e("getActiveProfile: %s", errorMessage);
                updateAdapter("Error: activate profile");
            }
        });

        glpi.getMyEntities(new GLPI.ResponseHandle<JsonObject, String>()  {
            @Override
            public void onResponse(JsonObject response) {
                FlyveLog.i("getMyEntities: %s", response.toString());
                updateAdapter("Success: my entities");
            }

            @Override
            public void onFailure(String errorMessage) {
                FlyveLog.e("getMyEntities: %s", errorMessage);
                updateAdapter("Error: my entities");
            }
        });

        glpi.getActiveEntities(new GLPI.ResponseHandle<JsonObject, String>()  {
            @Override
            public void onResponse(JsonObject response) {
                FlyveLog.i("getActiveEntities: %s", response.toString());
                updateAdapter("Success: active entities");
            }

            @Override
            public void onFailure(String errorMessage) {
                FlyveLog.e("getActiveEntities: %s", errorMessage);
                updateAdapter("Error: active entities");
            }
        });

        glpi.getFullSession(new GLPI.ResponseHandle<JsonObject, String>()  {
            @Override
            public void onResponse(JsonObject response) {
                FlyveLog.i("getFullSession: %s", response.toString());
                updateAdapter("Success: Full Session");
            }

            @Override
            public void onFailure(String errorMessage) {
                FlyveLog.e("getFullSession: %s", errorMessage);
                updateAdapter("Error: Full Session");
            }
        });

        glpi.getGlpiConfig(new GLPI.ResponseHandle<JsonObject, String>()  {
            @Override
            public void onResponse(JsonObject response) {
                FlyveLog.i("getGlpiConfig: %s", response.toString());
                updateAdapter("Success: glpi config");
            }

            @Override
            public void onFailure(String errorMessage) {
                FlyveLog.e("getGlpiConfig: %s", errorMessage);
                updateAdapter("Error: glpi config");
            }
        });

        glpi.getAllItems(itemType.Computer, new GLPI.ResponseHandle<JsonArray, String>() {
            @Override
            public void onResponse(JsonArray response) {
                FlyveLog.i("getAllItems: %s", response.toString());
                updateAdapter("Success: all items");
            }

            @Override
            public void onFailure(String errorMessage) {
                FlyveLog.e("getAllItems: %s", errorMessage);
                updateAdapter("Error: all items");
            }
        });

        glpi.getItem(itemType.Computer, "110", new GLPI.ResponseHandle<JsonObject, String>()  {
            @Override
            public void onResponse(JsonObject response) {
                FlyveLog.i("getAnItem: %s", response.toString());
                updateAdapter("Success: item");
            }

            @Override
            public void onFailure(String errorMessage) {
                FlyveLog.e("getAnItem: %s", errorMessage);
                updateAdapter("Error: item");
            }
        });

        GLPI.ResponseHandle<JsonObject, String> responseHandle = new GLPI.ResponseHandle<JsonObject, String>() {
            @Override
            public void onResponse(JsonObject response) {
                FlyveLog.i("getSubItems", response.toString());
                updateAdapter("Success: sub items");
            }

            @Override
            public void onFailure(String errorMessage) {
                FlyveLog.e("getSubItems: %s", errorMessage);
                updateAdapter("Error: sub items");
            }
        };
        glpi.getSubItems(itemType.Computer, "2", itemType.ComputerType, responseHandle);

        glpi.changeActiveProfile("9", new GLPI.ResponseHandle<String, String>() {
            @Override
            public void onResponse(String response) {
                FlyveLog.i("changeActiveProfile: %s", response);
                updateAdapter("Success: change active profile");
            }

            @Override
            public void onFailure(String errorMessage) {
                FlyveLog.e("changeActiveProfile: %s", errorMessage);
                updateAdapter("Error: change active profile");
            }
        });

        glpi.changeActiveEntities("1", false, new GLPI.ResponseHandle<String, String>() {
            @Override
            public void onResponse(String response) {
                FlyveLog.i("changeActiveEntities: %s", response);
                updateAdapter("Success: change active entities");
            }

            @Override
            public void onFailure(String errorMessage) {
                FlyveLog.e("changeActiveEntities: %s", errorMessage);
                updateAdapter("Error: change active entities");
            }
        });

        addItemExamplePayload.data item1 = new addItemExamplePayload.data("My first computer", "12345");
        addItemExamplePayload.data item2 = new addItemExamplePayload.data("My second computer", "12345678");
        addItemExamplePayload.data item3 = new addItemExamplePayload.data("My computer", "54321");

        ArrayList<addItemExamplePayload.data> list = new ArrayList<>();
        list.add(item1);
        list.add(item2);
        list.add(item3);

        addItemExamplePayload obj = new addItemExamplePayload(list);

        glpi.addItems(itemType.Computer, obj, new GLPI.ResponseHandle<JsonArray, String>() {
            @Override
            public void onResponse(JsonArray response) {

            }

            @Override
            public void onFailure(String errorMessage) {

            }
        });
        glpi.updateItems(itemType.Computer, "10", obj, new GLPI.ResponseHandle<JsonArray, String>() {
            @Override
            public void onResponse(JsonArray response) {
                FlyveLog.i("updateItems: %s", response.toString());
                updateAdapter("Success: update items");
            }

            @Override
            public void onFailure(String errorMessage) {
                FlyveLog.e("updateItems: %s", errorMessage);
                updateAdapter("Error: update items");
            }
        });

        glpi.deleteItems(itemType.Computer, "10", new GLPI.ResponseHandle<JsonArray, String>() {
            @Override
            public void onResponse(JsonArray response) {
                FlyveLog.i("deleteItems: %s", response.toString());
                updateAdapter("Success: delete items");
            }

            @Override
            public void onFailure(String errorMessage) {
                FlyveLog.e("deleteItems: %s", errorMessage);
                updateAdapter("Error: delete items");
            }
        });

        deleteItemExamplePayload.data deleteItem1 = new deleteItemExamplePayload.data("16");
        deleteItemExamplePayload.data deleteItem2 = new deleteItemExamplePayload.data("17");
        deleteItemExamplePayload.data deleteItem3 = new deleteItemExamplePayload.data("18");

        ArrayList<deleteItemExamplePayload.data> deleteList = new ArrayList<>();
        deleteList.add(deleteItem1);
        deleteList.add(deleteItem2);
        deleteList.add(deleteItem3);

        deleteItemExamplePayload deleteObj = new deleteItemExamplePayload(deleteList);

        glpi.deleteItems(itemType.Computer, deleteObj, new GLPI.ResponseHandle<JsonArray, String>() {
            @Override
            public void onResponse(JsonArray response) {
                FlyveLog.i("deleteItems: %s", response.toString());
                updateAdapter("Success: delete items");
            }

            @Override
            public void onFailure(String errorMessage) {
                FlyveLog.e("deleteItems: %s", errorMessage);
                updateAdapter("Error: delete items");
            }
        });

        glpi.recoveryPassword("youremail@yourdomain.com", new GLPI.ResponseHandle<String, String>() {
            @Override
            public void onResponse(String response) {
                FlyveLog.i("lostPassword: %s", response);
                updateAdapter("Success: Recovery Password");
            }

            @Override
            public void onFailure(String errorMessage) {
                FlyveLog.e("lostPassword: %s", errorMessage);
                updateAdapter("Error: Recovery Password");
            }
        });

        GLPI.ResponseHandle<String, String> callback = new GLPI.ResponseHandle<String, String>() {
            @Override
            public void onResponse(String response) {
                FlyveLog.i("recoveryPassword: %s", response);
                updateAdapter("Success: Reset Password");
                progressBar.setVisibility(View.GONE);
                recyclerView.setVisibility(View.VISIBLE);
            }

            @Override
            public void onFailure(String errorMessage) {
                FlyveLog.e("recoveryPassword: %s", errorMessage);
                updateAdapter("Error: Reset Password");
                progressBar.setVisibility(View.GONE);
                recyclerView.setVisibility(View.VISIBLE);
            }
        };
        glpi.resetPassword("youremail@yourdomain.com", "asdfasdfafsASDFd333A", "1234", callback);
    }

    private void updateAdapter(String message) {
        resultList.add(message);
        activityAdapter.notifyDataSetChanged();
    }
}
