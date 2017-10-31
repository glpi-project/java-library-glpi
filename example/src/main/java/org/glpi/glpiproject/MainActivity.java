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
import android.view.View;
import android.widget.Button;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.FormatStrategy;
import com.orhanobut.logger.Logger;
import com.orhanobut.logger.PrettyFormatStrategy;

import org.glpi.api.GLPI;
import org.glpi.api.itemType;
import org.glpi.api.response.InitSession;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FormatStrategy formatStrategy = PrettyFormatStrategy.newBuilder()
                .showThreadInfo(false)  // (Optional) Whether to show thread info or not. Default true
                .methodCount(0)         // (Optional) How many method line to show. Default 2
                .methodOffset(7)        // (Optional) Hides internal method calls up to offset. Default 5
                .tag("org.glpi.api")   // (Optional) Global tag for every log. Default PRETTY_LOGGER
                .build();

        Logger.addLogAdapter(new AndroidLogAdapter(formatStrategy));

        final GLPI glpi = new GLPI(MainActivity.this, BuildConfig.GLPI_URL);

        Button btnInit = (Button) findViewById(R.id.btnInit);
        btnInit.setOnClickListener(new View.OnClickListener() {
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
        });

        Button btn = (Button) findViewById(R.id.btnCall);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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

                addItemExamplePayload.data item1 = new addItemExamplePayload.data("My first computer","12345");
                addItemExamplePayload.data item2 = new addItemExamplePayload.data("My second computer","12345678");
                addItemExamplePayload.data item3 = new addItemExamplePayload.data("My computer","54321");

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

                glpi.deleteItems(itemType.Computer, "10", new GLPI.JsonArrayCallback() {
                    @Override
                    public void onResponse(JsonArray response) {
                        FlyveLog.i("deleteItems: %s", response.toString());
                    }

                    @Override
                    public void onFailure(String errorMessage) {
                        FlyveLog.e("deleteItems: %s", errorMessage);
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
            }
        });

        Button btnKill = (Button) findViewById(R.id.btnKill);
        btnKill.setOnClickListener(new View.OnClickListener() {
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
        });
    }
}
