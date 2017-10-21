package org.flyve.glpiproject;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import org.flyve.glpi.GLPI;
import org.flyve.glpi.response.InitSession;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        GLPI glpi = new GLPI(MainActivity.this, "https://dev.flyve.org/glpi/apirest.php");

        glpi.initSessionByCredentials("rafaelje@gmail.com", "12345678", new GLPI.initSessionCallback() {
            @Override
            public void onResponse(InitSession response) {
                Log.d("initSession", response.getSession_token());
            }

            @Override
            public void onFailure(String errorMessage) {
                Log.e("initSession", errorMessage);
            }
        });

        glpi.getMyProfiles(new GLPI.jsonObjectCallback() {
            @Override
            public void onResponse(JSONObject response) {
                Log.d("getMyProfiles", response.toString());
            }

            @Override
            public void onFailure(String errorMessage) {
                Log.d("getMyProfiles", errorMessage);
            }
        });

    }
}
