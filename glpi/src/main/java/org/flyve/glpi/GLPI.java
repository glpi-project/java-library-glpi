package org.flyve.glpi;

import android.content.Context;
import android.util.Log;

import org.flyve.glpi.response.InitSession;
import org.flyve.glpi.utils.Helpers;
import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/*
 *   Copyright © 2017 Teclib. All rights reserved.
 *
 *   This file is part of flyve-mdm-android
 *
 * flyve-mdm-android is a subproject of Flyve MDM. Flyve MDM is a mobile
 * device management software.
 *
 * Flyve MDM is free software: you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 3
 * of the License, or (at your option) any later version.
 *
 * Flyve MDM is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * ------------------------------------------------------------------------------
 * @author    Rafael Hernandez
 * @date      17/10/17
 * @copyright Copyright © 2017 Teclib. All rights reserved.
 * @license   GPLv3 https://www.gnu.org/licenses/gpl-3.0.html
 * @link      https://github.com/flyve-mdm/flyve-mdm-android
 * @link      https://flyve-mdm.com
 * ------------------------------------------------------------------------------
 */
public class GLPI extends ServiceGenerator {

    private Routes interfaces;
    private String sessionToken = "";
    private Context context;

    public GLPI(Context context, String glpiUrl) {
        //start("https://dev.flyve.org/glpi/apirest.php/");
        start(glpiUrl);
        this.context = context;

        interfaces = retrofit.create(Routes.class);
    }

    public void initSessionByUserToken(String userToken, final initSessionCallback callback) {
        Call<InitSession> responseCall = interfaces.initSessionByUserToken("user_token " + userToken.trim());
        responseCall.enqueue(new Callback<InitSession>() {
            @Override
            public void onResponse(Call<InitSession> call, Response<InitSession> response) {
                if(response.isSuccessful()) {

                    try {
                        sessionToken = response.body().getSession_token();
                    } catch (NullPointerException ex) {
                        Log.d("initSession", ex.getMessage());
                    }

                    callback.onResponse( response.body() );
                } else {
                    callback.onFailure( response.errorBody().toString() );
                }
            }

            @Override
            public void onFailure(Call<InitSession> call, Throwable t) {
                callback.onFailure(t.getMessage());
            }
        });
    }

    public void initSessionByCredentials(String user, String password, final initSessionCallback callback) {

        String authorization = Helpers.base64encode( user + ":" + password );

        Call<InitSession> responseCall = interfaces.initSessionByCredentials("Basic " + authorization.trim());
        responseCall.enqueue(new Callback<InitSession>() {
            @Override
            public void onResponse(Call<InitSession> call, Response<InitSession> response) {
                if(response.isSuccessful()) {
                    try {
                        sessionToken = response.body().getSession_token();
                    } catch (NullPointerException ex) {
                        Log.d("initSession", ex.getMessage());
                    }

                    callback.onResponse( response.body() );
                } else {
                    callback.onFailure( response.errorBody().toString() );
                }
            }

            @Override
            public void onFailure(Call<InitSession> call, Throwable t) {
                callback.onFailure(t.getMessage());
            }
        });
    }

    public void getMyProfiles(final jsonObjectCallback callback) {
        // validate if session token is empty
        if(sessionToken.equals("")) {
            callback.onFailure( context.getResources().getString(R.string.error_session_token_empty) );
        }

        Call<JSONObject> responseCall = interfaces.getMyProfiles(this.sessionToken);
        responseCall.enqueue(new Callback<JSONObject>() {
            @Override
            public void onResponse(Call<JSONObject> call, Response<JSONObject> response) {
                if(response.isSuccessful()) {
                    callback.onResponse( response.body() );
                } else {
                    callback.onFailure( response.errorBody().toString() );
                }
            }

            @Override
            public void onFailure(Call<JSONObject> call, Throwable t) {
                callback.onFailure(t.getMessage());
            }
        });
    }

    public void getActiveProfile(final jsonObjectCallback callback) {
        // validate if session token is empty
        if(sessionToken.equals("")) {
            callback.onFailure( context.getResources().getString(R.string.error_session_token_empty) );
        }

        Call<JSONObject> responseCall = interfaces.getActiveProfile(this.sessionToken);
        responseCall.enqueue(new Callback<JSONObject>() {
            @Override
            public void onResponse(Call<JSONObject> call, Response<JSONObject> response) {
                if(response.isSuccessful()) {
                    callback.onResponse( response.body() );
                } else {
                    callback.onFailure( response.errorBody().toString() );
                }
            }

            @Override
            public void onFailure(Call<JSONObject> call, Throwable t) {
                callback.onFailure(t.getMessage());
            }
        });
    }

    public void getMyEntities(final jsonObjectCallback callback) {
        // validate if session token is empty
        if(sessionToken.equals("")) {
            callback.onFailure( context.getResources().getString(R.string.error_session_token_empty) );
        }

        Call<JSONObject> responseCall = interfaces.getMyEntities(this.sessionToken);
        responseCall.enqueue(new Callback<JSONObject>() {
            @Override
            public void onResponse(Call<JSONObject> call, Response<JSONObject> response) {
                if(response.isSuccessful()) {
                    callback.onResponse( response.body() );
                } else {
                    callback.onFailure( response.errorBody().toString() );
                }
            }

            @Override
            public void onFailure(Call<JSONObject> call, Throwable t) {
                callback.onFailure(t.getMessage());
            }
        });
    }

    public void getActiveEntities(final jsonObjectCallback callback) {
        // validate if session token is empty
        if (sessionToken.equals("")) {
            callback.onFailure(context.getResources().getString(R.string.error_session_token_empty));
        }

        Call<JSONObject> responseCall = interfaces.getActiveEntities(this.sessionToken);
        responseCall.enqueue(new Callback<JSONObject>() {
            @Override
            public void onResponse(Call<JSONObject> call, Response<JSONObject> response) {
                if (response.isSuccessful()) {
                    callback.onResponse(response.body());
                } else {
                    callback.onFailure(response.errorBody().toString());
                }
            }

            @Override
            public void onFailure(Call<JSONObject> call, Throwable t) {
                callback.onFailure(t.getMessage());
            }
        });
    }

    public void getFullSession(final jsonObjectCallback callback) {
        // validate if session token is empty
        if (sessionToken.equals("")) {
            callback.onFailure(context.getResources().getString(R.string.error_session_token_empty));
        }

        Call<JSONObject> responseCall = interfaces.getFullSession(this.sessionToken);
        responseCall.enqueue(new Callback<JSONObject>() {
            @Override
            public void onResponse(Call<JSONObject> call, Response<JSONObject> response) {
                if (response.isSuccessful()) {
                    callback.onResponse(response.body());
                } else {
                    callback.onFailure(response.errorBody().toString());
                }
            }

            @Override
            public void onFailure(Call<JSONObject> call, Throwable t) {
                callback.onFailure(t.getMessage());
            }
        });
    }

    public void killsession() {}

    public void changeActiveProfile() {}
    public void changeActiveEntities() {}

    public void getAnTtem() {}
    public void getAllUtems() {}
    public void getSubItems() {}
    public void getMultipleItems() {}
    public void listSearchOptions() {}
    public void searchItems() {}
    public void addItems() {}
    public void updateItems() {}
    public void deleteItems() {}


    public interface initSessionCallback {
        void onResponse(InitSession response);
        void onFailure(String mensajeError);
    }

    public interface jsonObjectCallback {
        void onResponse(JSONObject response);
        void onFailure(String mensajeError);
    }

}
