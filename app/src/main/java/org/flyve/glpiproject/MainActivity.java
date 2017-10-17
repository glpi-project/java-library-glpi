package org.flyve.glpiproject;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import org.flyve.glpi.GLPI;
import org.flyve.glpi.response.InitSession;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        GLPI glpi = new GLPI("https://dev.flyve.org/glpi/apirest.php");

        glpi.initSessionByUserToken("kbd2wdhj8hqgc6ggj20acdkdfb6gyxt4rc2hem9n", new GLPI.initSessionCallback() {
            @Override
            public void onResponse(InitSession response) {
                Log.d("MainActivity", response.getSession_token());
            }

            @Override
            public void onFailure(String mensajeError) {
                Log.e("MainActivity", mensajeError);
            }
        });

    }
}
