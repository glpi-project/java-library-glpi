package org.flyve.glpiproject;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import org.flyve.glpi.GLPI;
import org.flyve.glpi.itemType;
import org.flyve.glpi.response.InitSession;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final GLPI glpi = new GLPI(MainActivity.this, "https://dev.flyve.org/glpi/apirest.php/");


        Button btnInit = (Button) findViewById(R.id.btnInit);
        btnInit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                glpi.initSessionByCredentials("rafaelje@gmail.com", "12345678", new GLPI.InitSessionCallback() {
                    @Override
                    public void onResponse(InitSession response) {
                        Log.d("initSession", response.getSession_token());
                    }

                    @Override
                    public void onFailure(String errorMessage) {
                        Log.e("initSession", errorMessage);
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
                        Log.d("getMyProfiles", response.toString());
                    }

                    @Override
                    public void onFailure(String errorMessage) {
                        Log.e("getMyProfiles", errorMessage);
                    }
                });

                glpi.getActiveProfile(new GLPI.JsonObjectCallback() {
                    @Override
                    public void onResponse(JsonObject response) {
                        Log.d("getActiveProfile", response.toString());
                    }

                    @Override
                    public void onFailure(String errorMessage) {
                        Log.e("getActiveProfile", errorMessage);
                    }
                });

                glpi.getMyEntities(new GLPI.JsonObjectCallback() {
                    @Override
                    public void onResponse(JsonObject response) {
                        Log.d("getMyEntities", response.toString());
                    }

                    @Override
                    public void onFailure(String errorMessage) {
                        Log.e("getMyEntities", errorMessage);
                    }
                });

                glpi.getActiveEntities(new GLPI.JsonObjectCallback() {
                    @Override
                    public void onResponse(JsonObject response) {
                        Log.d("getActiveEntities", response.toString());
                    }

                    @Override
                    public void onFailure(String errorMessage) {
                        Log.e("getActiveEntities", errorMessage);
                    }
                });

                glpi.getFullSession(new GLPI.JsonObjectCallback() {
                    @Override
                    public void onResponse(JsonObject response) {
                        Log.d("getFullSession", response.toString());
                    }

                    @Override
                    public void onFailure(String errorMessage) {
                        Log.e("getFullSession", errorMessage);
                    }
                });

                glpi.getGlpiConfig(new GLPI.JsonObjectCallback() {
                    @Override
                    public void onResponse(JsonObject response) {
                        Log.d("getGlpiConfig", response.toString());
                    }

                    @Override
                    public void onFailure(String errorMessage) {
                        Log.e("getGlpiConfig", errorMessage);
                    }
                });

                glpi.getAllItems(itemType.Computer, new GLPI.JsonArrayCallback() {
                    @Override
                    public void onResponse(JsonArray response) {
                        Log.d("getAllItems", response.toString());
                    }

                    @Override
                    public void onFailure(String errorMessage) {
                        Log.e("getAllItems", errorMessage);
                    }
                });

                glpi.getAnItem(itemType.Computer, "110", new GLPI.JsonObjectCallback() {
                    @Override
                    public void onResponse(JsonObject response) {
                        Log.d("getAnItem", response.toString());
                    }

                    @Override
                    public void onFailure(String errorMessage) {
                        Log.e("getAnItem", errorMessage);
                    }
                });

                glpi.getSubItems(itemType.User, "2", "Log", new GLPI.JsonObjectCallback() {
                    @Override
                    public void onResponse(JsonObject response) {
                        Log.d("getSubItems", response.toString());
                    }

                    @Override
                    public void onFailure(String errorMessage) {
                        Log.e("getSubItems", errorMessage);
                    }
                });

            }
        });

        Button btnKill = (Button) findViewById(R.id.btnKill);
        btnKill.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                glpi.killSession(new GLPI.KillSessionCallback() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("killSession", response.toString());
                    }

                    @Override
                    public void onFailure(String errorMessage) {
                        Log.e("killSession", errorMessage);
                    }
                });

            }
        });



    }
}
