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
import java.util.ArrayList;

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
                glpi.initSessionByCredentials("gianfrancomanganiello1997@gmail.com", "12345678", new GLPI.InitSessionCallback() {
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

                glpi.changeActiveProfile("9", new GLPI.VoidCallback() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("changeActiveProfile", response);
                    }

                    @Override
                    public void onFailure(String errorMessage) {
                        Log.e("changeActiveProfile", errorMessage);
                    }
                });

                glpi.changeActiveEntities("1", false, new GLPI.VoidCallback() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("changeActiveEntities", response);
                    }

                    @Override
                    public void onFailure(String errorMessage) {
                        Log.e("changeActiveEntities", errorMessage);
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
                        Log.d("addItems", response.toString());
                    }

                    @Override
                    public void onFailure(String errorMessage) {
                        Log.e("addItems", errorMessage);
                    }
                });

                glpi.updateItems(itemType.Computer, "10", obj, new GLPI.JsonArrayCallback() {
                    @Override
                    public void onResponse(JsonArray response) {
                        Log.d("updateItems", response.toString());
                    }

                    @Override
                    public void onFailure(String errorMessage) {
                        Log.e("updateItems", errorMessage);
                    }
                });

                glpi.deleteItems(itemType.Computer, "10", new GLPI.JsonArrayCallback() {
                    @Override
                    public void onResponse(JsonArray response) {
                        Log.d("deleteItems", response.toString());
                    }

                    @Override
                    public void onFailure(String errorMessage) {
                        Log.e("deleteItems", errorMessage);
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
                        Log.d("deleteItems", response.toString());
                    }

                    @Override
                    public void onFailure(String errorMessage) {
                        Log.e("deleteItems", errorMessage);
                    }
                });

                glpi.lostPassword("youremail@yourdomain.com", new GLPI.VoidCallback() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("lostPassword", response);
                    }

                    @Override
                    public void onFailure(String errorMessage) {
                        Log.e("lostPassword", errorMessage);
                    }
                });

                glpi.recoveryPassword("youremail@yourdomain.com", "asdfasdfafsASDFd333A", "1234", new GLPI.VoidCallback() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("recoveryPassword", response);
                    }

                    @Override
                    public void onFailure(String errorMessage) {
                        Log.e("recoveryPassword", errorMessage);
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
