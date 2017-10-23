package org.flyve.glpi;

import android.content.Context;
import android.util.Log;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import org.flyve.glpi.request.changeActiveEntitiesRequest;
import org.flyve.glpi.request.changeActiveProfileRequest;
import org.flyve.glpi.request.lostPasswordRequest;
import org.flyve.glpi.request.recoveryPasswordRequest;
import org.flyve.glpi.response.InitSession;
import org.flyve.glpi.utils.Helpers;

import java.util.HashMap;
import java.util.Map;

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
        start(glpiUrl);
        this.context = context;

        interfaces = retrofit.create(Routes.class);
    }

    public void initSessionByUserToken(String userToken, final InitSessionCallback callback) {
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
                    String errorMessage;
                    try {
                        errorMessage = response.errorBody().string();
                    } catch (Exception ex) {
                        errorMessage = context.getResources().getString(R.string.error_generic);
                    }
                    callback.onFailure( errorMessage );
                }
            }

            @Override
            public void onFailure(Call<InitSession> call, Throwable t) {
                callback.onFailure(t.getMessage());
            }
        });
    }

    public void initSessionByCredentials(String user, String password, final InitSessionCallback callback) {

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
                    String errorMessage;
                    try {
                        errorMessage = response.errorBody().string();
                    } catch (Exception ex) {
                        errorMessage = context.getResources().getString(R.string.error_generic);
                    }
                    callback.onFailure( errorMessage );
                }
            }

            @Override
            public void onFailure(Call<InitSession> call, Throwable t) {
                callback.onFailure(t.getMessage());
            }
        });
    }

    public void getMyProfiles(final JsonObjectCallback callback) {
        getMyProfiles(null, callback);
    }

    public void getMyProfiles(String appToken, final JsonObjectCallback callback) {
        // validate if session token is empty
        if(sessionToken.equals("")) {
            callback.onFailure( context.getResources().getString(R.string.error_session_token_empty) );
        }
        Map<String, String> map = new HashMap<>();

        String _appToken = appToken;

        map.put("Session-Token", this.sessionToken);
        if(_appToken!=null) {
            map.put("App-Token", _appToken);
        }

        Call<JsonObject> responseCall = interfaces.getMyProfiles(map);
        responseCall.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                if(response.isSuccessful()) {
                    callback.onResponse( response.body() );
                } else {
                    String errorMessage;
                    try {
                        errorMessage = response.errorBody().string();
                    } catch (Exception ex) {
                        errorMessage = context.getResources().getString(R.string.error_generic);
                    }
                    callback.onFailure( errorMessage );
                }
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                callback.onFailure(t.getMessage());
            }
        });
    }

    public void getActiveProfile(final JsonObjectCallback callback) {
        getActiveProfile(null, callback);
    }

    public void getActiveProfile(String appToken, final JsonObjectCallback callback) {
        // validate if session token is empty
        if(sessionToken.equals("")) {
            callback.onFailure( context.getResources().getString(R.string.error_session_token_empty) );
        }

        Map<String, String> map = new HashMap<>();

        String _appToken = appToken;

        map.put("Session-Token", this.sessionToken);
        if(_appToken!=null) {
            map.put("App-Token", _appToken);
        }

        Call<JsonObject> responseCall = interfaces.getActiveProfile(map);
        responseCall.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                if(response.isSuccessful()) {
                    callback.onResponse( response.body() );
                } else {
                    String errorMessage;
                    try {
                        errorMessage = response.errorBody().string();
                    } catch (Exception ex) {
                        errorMessage = context.getResources().getString(R.string.error_generic);
                    }
                    callback.onFailure( errorMessage );
                }
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                callback.onFailure(t.getMessage());
            }
        });
    }

    public void getMyEntities(final JsonObjectCallback callback) {
        getMyEntities(null, callback);
    }

    public void getMyEntities(String appToken, final JsonObjectCallback callback) {
        // validate if session token is empty
        if(sessionToken.equals("")) {
            callback.onFailure( context.getResources().getString(R.string.error_session_token_empty) );
        }

        Map<String, String> map = new HashMap<>();

        String _appToken = appToken;

        map.put("Session-Token", this.sessionToken);
        if(_appToken!=null) {
            map.put("App-Token", _appToken);
        }

        Call<JsonObject> responseCall = interfaces.getMyEntities(map);
        responseCall.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                if(response.isSuccessful()) {
                    callback.onResponse( response.body() );
                } else {
                    String errorMessage;
                    try {
                        errorMessage = response.errorBody().string();
                    } catch (Exception ex) {
                        errorMessage = context.getResources().getString(R.string.error_generic);
                    }
                    callback.onFailure( errorMessage );
                }
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                callback.onFailure(t.getMessage());
            }
        });
    }

    public void getActiveEntities(final JsonObjectCallback callback) {
        getActiveEntities(null, callback);
    }

    public void getActiveEntities(String appToken, final JsonObjectCallback callback) {
        // validate if session token is empty
        if (sessionToken.equals("")) {
            callback.onFailure(context.getResources().getString(R.string.error_session_token_empty));
        }

        Map<String, String> map = new HashMap<>();

        String _appToken = appToken;

        map.put("Session-Token", this.sessionToken);
        if(_appToken!=null) {
            map.put("App-Token", _appToken);
        }

        Call<JsonObject> responseCall = interfaces.getActiveEntities(map);
        responseCall.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                if (response.isSuccessful()) {
                    callback.onResponse(response.body());
                } else {
                    String errorMessage;
                    try {
                        errorMessage = response.errorBody().string();
                    } catch (Exception ex) {
                        errorMessage = context.getResources().getString(R.string.error_generic);
                    }
                    callback.onFailure( errorMessage );
                }
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                callback.onFailure(t.getMessage());
            }
        });
    }

    public void getFullSession(final JsonObjectCallback callback) {
        // validate if session token is empty
        if (sessionToken.equals("")) {
            callback.onFailure(context.getResources().getString(R.string.error_session_token_empty));
        }

        Call<JsonObject> responseCall = interfaces.getFullSession(this.sessionToken);
        responseCall.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                if (response.isSuccessful()) {
                    callback.onResponse(response.body());
                } else {
                    String errorMessage;
                    try {
                        errorMessage = response.errorBody().string();
                    } catch (Exception ex) {
                        errorMessage = context.getResources().getString(R.string.error_generic);
                    }
                    callback.onFailure( errorMessage );
                }
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                callback.onFailure(t.getMessage());
            }
        });
    }

    public void getGlpiConfig(final JsonObjectCallback callback) {
        // validate if session token is empty
        if (sessionToken.equals("")) {
            callback.onFailure(context.getResources().getString(R.string.error_session_token_empty));
        }

        Call<JsonObject> responseCall = interfaces.getGlpiConfig(this.sessionToken);
        responseCall.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                if (response.isSuccessful()) {
                    callback.onResponse(response.body());
                } else {
                    String errorMessage;
                    try {
                        errorMessage = response.errorBody().string();
                    } catch (Exception ex) {
                        errorMessage = context.getResources().getString(R.string.error_generic);
                    }
                    callback.onFailure( errorMessage );
                }
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                callback.onFailure(t.getMessage());
            }
        });
    }

    public void killSession(final VoidCallback callback) {
        killSession(null, callback);
    }

    public void killSession(String appToken, final VoidCallback callback) {
        // validate if session token is empty
        if (sessionToken.equals("")) {
            callback.onFailure(context.getResources().getString(R.string.error_session_token_empty));
        }
        Map<String, String> map = new HashMap<>();

        String _appToken = appToken;

        map.put("Session-Token", this.sessionToken);
        if(_appToken!=null) {
            map.put("App-Token", _appToken);
        }

        Call<Void> responseCall = interfaces.killSession(map);
        responseCall.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    callback.onResponse(context.getResources().getString(R.string.kill_session_success));
                } else {
                    String errorMessage;
                    try {
                        errorMessage = response.errorBody().string();
                    } catch (Exception ex) {
                        errorMessage = context.getResources().getString(R.string.error_generic);
                    }
                    callback.onFailure( errorMessage );
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                callback.onFailure(t.getMessage());
            }
        });
    }

    public void getAllItems(itemType itemType, final JsonArrayCallback callback) {
        // validate if session token is empty
        if (sessionToken.equals("")) {
            callback.onFailure(context.getResources().getString(R.string.error_session_token_empty));
        }

        Call<JsonArray> responseCall = interfaces.getAllItem(this.sessionToken, itemType.name());
        responseCall.enqueue(new Callback<JsonArray>() {
            @Override
            public void onResponse(Call<JsonArray> call, Response<JsonArray> response) {
                if (response.isSuccessful()) {
                    callback.onResponse(response.body());
                } else {
                    String errorMessage;
                    try {
                        errorMessage = response.errorBody().string();
                    } catch (Exception ex) {
                        errorMessage = context.getResources().getString(R.string.error_generic);
                    }
                    callback.onFailure( errorMessage );
                }
            }

            @Override
            public void onFailure(Call<JsonArray> call, Throwable t) {
                callback.onFailure(t.getMessage());
            }
        });
    }

    public void getAnItem(itemType itemType, String id, final JsonObjectCallback callback) {
        // validate if session token is empty
        if (sessionToken.equals("")) {
            callback.onFailure(context.getResources().getString(R.string.error_session_token_empty));
        }

        Call<JsonObject> responseCall = interfaces.getAnItem(this.sessionToken, itemType.name(), id);
        responseCall.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                if (response.isSuccessful()) {
                    callback.onResponse(response.body());
                } else {
                    String errorMessage;
                    try {
                        errorMessage = response.errorBody().string();
                    } catch (Exception ex) {
                        errorMessage = context.getResources().getString(R.string.error_generic);
                    }
                    callback.onFailure(errorMessage);
                }
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                callback.onFailure(t.getMessage());
            }
        });
    }

    public void getSubItems(itemType itemType, String id, String subItemType, final JsonObjectCallback callback) {
        // validate if session token is empty
        if (sessionToken.equals("")) {
            callback.onFailure(context.getResources().getString(R.string.error_session_token_empty));
        }

        Call<JsonObject> responseCall = interfaces.getSubItem(this.sessionToken, itemType.name(), id, subItemType);
        responseCall.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                if (response.isSuccessful()) {
                    callback.onResponse(response.body());
                } else {
                    String errorMessage;
                    try {
                        errorMessage = response.errorBody().string();
                    } catch (Exception ex) {
                        errorMessage = context.getResources().getString(R.string.error_generic);
                    }
                    callback.onFailure(errorMessage);
                }
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                callback.onFailure(t.getMessage());
            }
        });
    }

    public void changeActiveProfile(String profilesId, final VoidCallback callback) {
        // validate if session token is empty
        if (sessionToken.equals("")) {
            callback.onFailure(context.getResources().getString(R.string.error_session_token_empty));
        }

        changeActiveProfileRequest requestPost = new changeActiveProfileRequest(profilesId);

        Call<Void> responseCall = interfaces.changeActiveProfile(this.sessionToken, requestPost);
        responseCall.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    callback.onResponse(context.getResources().getString(R.string.change_active_profile_success));
                } else {
                    String errorMessage;
                    try {
                        errorMessage = response.errorBody().string();
                    } catch (Exception ex) {
                        errorMessage = context.getResources().getString(R.string.error_generic);
                    }
                    callback.onFailure(errorMessage);
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                callback.onFailure(t.getMessage());
            }
        });
    }

    public void changeActiveEntities(String entitiesId, Boolean is_recursive, final VoidCallback callback) {
        // validate if session token is empty
        if (sessionToken.equals("")) {
            callback.onFailure(context.getResources().getString(R.string.error_session_token_empty));
        }

        changeActiveEntitiesRequest requestPost = new changeActiveEntitiesRequest(entitiesId, is_recursive.toString());

        Call<Void> responseCall = interfaces.changeActiveEntities(this.sessionToken, requestPost);
        responseCall.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    callback.onResponse(context.getResources().getString(R.string.change_active_entities_success));
                } else {
                    String errorMessage;
                    try {
                        errorMessage = response.errorBody().string();
                    } catch (Exception ex) {
                        errorMessage = context.getResources().getString(R.string.error_generic);
                    }
                    callback.onFailure(errorMessage);
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                callback.onFailure(t.getMessage());
            }
        });
    }

    public void addItems(itemType itemType, Object payload, final JsonArrayCallback callback) {
        // validate if session token is empty
        if (sessionToken.equals("")) {
            callback.onFailure(context.getResources().getString(R.string.error_session_token_empty));
        }

        Call<JsonArray> responseCall = interfaces.addItem(this.sessionToken, itemType.name(), payload);
        responseCall.enqueue(new Callback<JsonArray>() {
            @Override
            public void onResponse(Call<JsonArray> call, Response<JsonArray> response) {
                if (response.isSuccessful()) {
                    callback.onResponse(response.body());
                } else {
                    String errorMessage;
                    try {
                        errorMessage = response.errorBody().string();
                    } catch (Exception ex) {
                        errorMessage = context.getResources().getString(R.string.error_generic);
                    }
                    callback.onFailure(errorMessage);
                }
            }

            @Override
            public void onFailure(Call<JsonArray> call, Throwable t) {
                callback.onFailure(t.getMessage());
            }
        });
    }

    public void updateItems(itemType itemType, String id, Object payload, final JsonArrayCallback callback) {
        // validate if session token is empty
        if (sessionToken.equals("")) {
            callback.onFailure(context.getResources().getString(R.string.error_session_token_empty));
        }

        Call<JsonArray> responseCall = interfaces.updateItem(this.sessionToken, itemType.name(), id, payload);
        responseCall.enqueue(new Callback<JsonArray>() {
            @Override
            public void onResponse(Call<JsonArray> call, Response<JsonArray> response) {
                if (response.isSuccessful()) {
                    callback.onResponse(response.body());
                } else {
                    String errorMessage;
                    try {
                        errorMessage = response.errorBody().string();
                    } catch (Exception ex) {
                        errorMessage = context.getResources().getString(R.string.error_generic);
                    }
                    callback.onFailure(errorMessage);
                }
            }

            @Override
            public void onFailure(Call<JsonArray> call, Throwable t) {
                callback.onFailure(t.getMessage());
            }
        });
    }

    public void deleteItems(itemType itemType, String id, final JsonArrayCallback callback) {
        // validate if session token is empty
        if (sessionToken.equals("")) {
            callback.onFailure(context.getResources().getString(R.string.error_session_token_empty));
        }

        Call<JsonArray> responseCall = interfaces.deleteItem(this.sessionToken, itemType.name(), id);
        responseCall.enqueue(new Callback<JsonArray>() {
            @Override
            public void onResponse(Call<JsonArray> call, Response<JsonArray> response) {
                if (response.isSuccessful()) {
                    callback.onResponse(response.body());
                } else {
                    String errorMessage;
                    try {
                        errorMessage = response.errorBody().string();
                    } catch (Exception ex) {
                        errorMessage = context.getResources().getString(R.string.error_generic);
                    }
                    callback.onFailure(errorMessage);
                }
            }

            @Override
            public void onFailure(Call<JsonArray> call, Throwable t) {
                callback.onFailure(t.getMessage());
            }
        });
    }

    public void deleteItems(itemType itemType, Object payload, final JsonArrayCallback callback) {
        // validate if session token is empty
        if (sessionToken.equals("")) {
            callback.onFailure(context.getResources().getString(R.string.error_session_token_empty));
        }

        Call<JsonArray> responseCall = interfaces.deleteMultiplesItem(this.sessionToken, itemType.name(), payload);
        responseCall.enqueue(new Callback<JsonArray>() {
            @Override
            public void onResponse(Call<JsonArray> call, Response<JsonArray> response) {
                if (response.isSuccessful()) {
                    callback.onResponse(response.body());
                } else {
                    String errorMessage;
                    try {
                        errorMessage = response.errorBody().string();
                    } catch (Exception ex) {
                        errorMessage = context.getResources().getString(R.string.error_generic);
                    }
                    callback.onFailure(errorMessage);
                }
            }

            @Override
            public void onFailure(Call<JsonArray> call, Throwable t) {
                callback.onFailure(t.getMessage());
            }
        });
    }

    public void lostPassword(String email, final VoidCallback callback) {
        // validate if session token is empty
        if (sessionToken.equals("")) {
            callback.onFailure(context.getResources().getString(R.string.error_session_token_empty));
        }

        lostPasswordRequest requestPost = new lostPasswordRequest(email);

        Call<Void> responseCall = interfaces.lostPassword(requestPost);
        responseCall.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    callback.onResponse(context.getResources().getString(R.string.lost_password_success));
                } else {
                    String errorMessage;
                    try {
                        errorMessage = response.errorBody().string();
                    } catch (Exception ex) {
                        errorMessage = context.getResources().getString(R.string.error_generic);
                    }
                    callback.onFailure(errorMessage);
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                callback.onFailure(t.getMessage());
            }
        });
    }

    public void recoveryPassword(String email, String token, String newPassword, final VoidCallback callback) {
        // validate if session token is empty
        if (sessionToken.equals("")) {
            callback.onFailure(context.getResources().getString(R.string.error_session_token_empty));
        }

        recoveryPasswordRequest requestPost = new recoveryPasswordRequest(email, token, newPassword);

        Call<Void> responseCall = interfaces.recoveryPassword(requestPost);
        responseCall.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    callback.onResponse(context.getResources().getString(R.string.recovery_password_success));
                } else {
                    String errorMessage;
                    try {
                        errorMessage = response.errorBody().string();
                    } catch (Exception ex) {
                        errorMessage = context.getResources().getString(R.string.error_generic);
                    }
                    callback.onFailure(errorMessage);
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                callback.onFailure(t.getMessage());
            }
        });
    }

    public void getMultipleItems() {}
    public void listSearchOptions() {}
    public void searchItems() {}

    public interface InitSessionCallback {
        void onResponse(InitSession response);
        void onFailure(String errorMessage);
    }

    public interface JsonObjectCallback {
        void onResponse(JsonObject response);
        void onFailure(String errorMessage);
    }

    public interface JsonArrayCallback {
        void onResponse(JsonArray response);
        void onFailure(String errorMessage);
    }

    public interface VoidCallback {
        void onResponse(String response);
        void onFailure(String errorMessage);
    }


}
