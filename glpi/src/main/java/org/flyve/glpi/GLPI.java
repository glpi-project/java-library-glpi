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

package org.flyve.glpi;

import android.content.Context;
import android.util.Log;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import org.flyve.glpi.query.GetAllItemQuery;
import org.flyve.glpi.query.GetAnItemQuery;
import org.flyve.glpi.query.GetSubItemQuery;
import org.flyve.glpi.request.ChangeActiveEntitiesRequest;
import org.flyve.glpi.request.ChangeActiveProfileRequest;
import org.flyve.glpi.request.LostPasswordRequest;
import org.flyve.glpi.request.RecoveryPasswordRequest;
import org.flyve.glpi.response.InitSession;
import org.flyve.glpi.utils.Helpers;
import java.util.HashMap;
import java.util.Map;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GLPI extends ServiceGenerator {

    private Routes interfaces;
    private String sessionToken = "";
    private String appToken;
    private Context context;

    /**
     * GLPI REST API Constructor this class will help you to interact with GLPI endpoints
     * @param context is the context
     * @param glpiUrl is the url glpi instance
     */
    public GLPI(Context context, String glpiUrl) {
        start(glpiUrl);
        this.context = context;

        interfaces = retrofit.create(Routes.class);
    }

    /**
     * Request a session token to uses other api endpoints.
     * @param userToken defined in User Preference (See 'Remote access key' on GLPI)
     * @param callback here your are going to get the asynchronous response
     */
    public void initSessionByUserToken(String userToken, final InitSessionCallback callback) {
        initSessionByUserToken(null, userToken, callback);
    }

    /**
     * Request a session token to uses other api endpoints.
     * @param appToken authorization string provided by the GLPI api configuration. [Optional]
     * @param userToken defined in User Preference (See 'Remote access key' on GLPI)
     * @param callback here your are going to get the asynchronous response
     */
    public void initSessionByUserToken(String appToken, String userToken, final InitSessionCallback callback) {

        this.appToken = appToken;

        Call<InitSession> responseCall = interfaces.initSessionByUserToken("user_token " + userToken.trim());
        responseCall.enqueue(new Callback<InitSession>() {
            @Override
            public void onResponse(Call<InitSession> call, Response<InitSession> response) {
                if(response.isSuccessful()) {

                    try {
                        sessionToken = response.body().getSessionToken();
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

    /**
     * Request a session token to uses other api endpoints. with a couple login & password: 2 parameters to login with user authentication
     * @param user valid user on GLPI
     * @param password valid password on GLPI
     * @param callback here your are going to get the asynchronous response
     */
    public void initSessionByCredentials(String user, String password, final InitSessionCallback callback) {
        initSessionByCredentials(null, user, password, callback);
    }

    /**
     * Request a session token to uses other api endpoints. with a couple login & password: 2 parameters to login with user authentication
     * @param appToken  authorization string provided by the GLPI api configuration. [Optional]
     * @param user valid user on GLPI
     * @param password valid password on GLPI
     * @param callback here your are going to get the asynchronous response
     */
    public void initSessionByCredentials(String appToken, String user, String password, final InitSessionCallback callback) {

        this.appToken = appToken;

        String authorization = Helpers.base64encode( user + ":" + password );

        Call<InitSession> responseCall = interfaces.initSessionByCredentials("Basic " + authorization.trim());
        responseCall.enqueue(new Callback<InitSession>() {
            @Override
            public void onResponse(Call<InitSession> call, Response<InitSession> response) {
                if(response.isSuccessful()) {
                    try {
                        sessionToken = response.body().getSessionToken();
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

    /**
     * Create a valid Array with common headers needs
     * @return Map<String, String> with all the headers
     */
    private Map<String, String> getHeader() {
        Map<String, String> map = new HashMap<>();

        map.put("Session-Token", this.sessionToken);
        if(appToken!=null) {
            map.put("App-Token", appToken);
        }

        return map;
    }

    /**
     * Return all the profiles associated to logged user.
     * @param callback here your are going to get the asynchronous response
     */
    public void getMyProfiles(final JsonObjectCallback callback) {

        Call<JsonObject> responseCall = interfaces.getMyProfiles(getHeader());
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

    /**
     * Return the current active profile.
     * @param callback here your are going to get the asynchronous response
     */
    public void getActiveProfile(final JsonObjectCallback callback) {

        Call<JsonObject> responseCall = interfaces.getActiveProfile(getHeader());
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

    /**
     * Return all the possible entities of the current logged user (and for current active profile).
     * @param callback here your are going to get the asynchronous response
     */
    public void getMyEntities(final JsonObjectCallback callback) {

        Call<JsonObject> responseCall = interfaces.getMyEntities(getHeader());
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

    /**
     * Return active entities of current logged user.
     * @param callback here your are going to get the asynchronous response
     */
    public void getActiveEntities(final JsonObjectCallback callback) {

        Call<JsonObject> responseCall = interfaces.getActiveEntities(getHeader());
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

    /**
     * Return the current php $_SESSION.
     * @param callback here your are going to get the asynchronous response
     */
    public void getFullSession(final JsonObjectCallback callback) {

        Call<JsonObject> responseCall = interfaces.getFullSession(getHeader());
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

    /**
     * Return the current $CFG_GLPI.
     * @param callback here your are going to get the asynchronous response
     */
    public void getGlpiConfig(final JsonObjectCallback callback) {

        Call<JsonObject> responseCall = interfaces.getGlpiConfig(getHeader());
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

    /**
     * Destroy a session identified by a session token.
     * @param callback here your are going to get the asynchronous response
     */
    public void killSession(final VoidCallback callback) {

        Call<Void> responseCall = interfaces.killSession(getHeader());
        responseCall.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    GLPI.this.appToken = null;
                    GLPI.this.sessionToken = "";
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

    /**
     * Return a collection of rows of the itemtype.
     * @param itemType This are the item type available on GLPI
     * @param callback here your are going to get the asynchronous response
     */
    public void getAllItems(itemType itemType, final JsonArrayCallback callback) {
        getAllItems(new GetAllItemQuery(GLPI.this.context), itemType, callback);
    }

    /**
     * Return a collection of rows of the itemtype.
     * @param options this are the parameters of this endpoint for example expand_dropdowns or get_hateoas
     * @param itemType This are the item type available on GLPI
     * @param callback here your are going to get the asynchronous response
     */
    public void getAllItems(GetAllItemQuery options, itemType itemType, final JsonArrayCallback callback) {

        Call<JsonArray> responseCall = interfaces.getAllItem(getHeader(), itemType.name(), options.getQuery());
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

    /**
     * Return the instance fields of itemtype identified by id.
     * @param itemType This are the item type available on GLPI
     * @param id unique identifier of the itemtype
     * @param callback here your are going to get the asynchronous response
     */
    public void getAnItem(itemType itemType, String id, final JsonObjectCallback callback) {
        getAnItem(new GetAnItemQuery(), itemType, id, callback);
    }

    /**
     *  Return the instance fields of itemtype identified by id.
     * @param options this are the parameters of this endpoint for example expand_dropdowns or get_hateoas
     * @param itemType This are the item type available on GLPI
     * @param id unique identifier of the itemtype
     * @param callback here your are going to get the asynchronous response
     */
    public void getAnItem(GetAnItemQuery options, itemType itemType, String id, final JsonObjectCallback callback) {

        Call<JsonObject> responseCall = interfaces.getAnItem(getHeader(), itemType.name(), id, options.getQuery());
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

    /**
     * Return a collection of rows of the sub_itemtype for the identified item.
     * @param itemType This are the item type available on GLPI
     * @param id unique identifier of the parent itemtype
     * @param subItemType This are the item type available on GLPI
     * @param callback here your are going to get the asynchronous response
     */
    public void getSubItems(itemType itemType, String id, String subItemType, final JsonObjectCallback callback) {
        getSubItems(new GetSubItemQuery(GLPI.this.context), itemType, id, subItemType, callback);
    }

    /**
     * Return a collection of rows of the sub_itemtype for the identified item.
     * @param options this are the parameters of this endpoint for example expand_dropdowns or get_hateoas
     * @param itemType This are the item type available on GLPI
     * @param id unique identifier of the parent itemtype
     * @param subItemType This are the item type available on GLPI
     * @param callback here your are going to get the asynchronous response
     */
    public void getSubItems(GetSubItemQuery options, itemType itemType, String id, String subItemType, final JsonObjectCallback callback) {

        Call<JsonObject> responseCall = interfaces.getSubItem(getHeader(), itemType.name(), id, subItemType, options.getQuery());
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

    /**
     * Change active profile to the profiles_id one. See getMyProfiles endpoint for possible profiles.
     * @param profilesId (default 'all') ID of the new active profile.
     * @param callback here your are going to get the asynchronous response
     */
    public void changeActiveProfile(String profilesId, final VoidCallback callback) {

        ChangeActiveProfileRequest requestPost = new ChangeActiveProfileRequest(profilesId);

        Call<Void> responseCall = interfaces.changeActiveProfile(getHeader(), requestPost);
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

    /**
     * Change active entity to the entities_id one. See getMyEntities endpoint for possible entities.
     * @param entitiesId (default 'all') ID of the new active entity ("all" => load all possible entities).
     * @param is_recursive (default false) Also display sub entities of the active entity.
     * @param callback here your are going to get the asynchronous response
     */
    public void changeActiveEntities(String entitiesId, Boolean is_recursive, final VoidCallback callback) {

        ChangeActiveEntitiesRequest requestPost = new ChangeActiveEntitiesRequest(entitiesId, is_recursive.toString());

        Call<Void> responseCall = interfaces.changeActiveEntities(getHeader(), requestPost);
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

    /**
     * Add an object (or multiple objects) into GLPI.
     * @param itemType This are the item type available on GLPI
     * @param payload input: an object with fields of itemtype to be inserted. You can add several items in one action by passing an array of objects.
     * @param callback here your are going to get the asynchronous response
     */
    public void addItems(itemType itemType, Object payload, final JsonArrayCallback callback) {

        Call<JsonArray> responseCall = interfaces.addItem(getHeader(), itemType.name(), payload);
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

    /**
     * Update an object (or multiple objects) existing in GLPI.
     * @param itemType This are the item type available on GLPI
     * @param id the unique identifier of the itemtype
     * @param payload Array of objects with fields of itemtype to be updated.
     * @param callback here your are going to get the asynchronous response
     */
    public void updateItems(itemType itemType, String id, Object payload, final JsonArrayCallback callback) {

        Call<JsonArray> responseCall = interfaces.updateItem(getHeader(), itemType.name(), id, payload);
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

    /**
     * Delete an object existing in GLPI.
     * @param itemType This are the item type available on GLPI
     * @param id unique identifier of the itemtype
     * @param callback here your are going to get the asynchronous response
     */
    public void deleteItems(itemType itemType, String id, final JsonArrayCallback callback) {

        Call<JsonArray> responseCall = interfaces.deleteItem(getHeader(), itemType.name(), id);
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

    /**
     * Delete multiples objects existing in GLPI.
     * @param itemType This are the item type available on GLPI
     * @param payload Array of id who need to be deleted
     * @param callback here your are going to get the asynchronous response
     */
    public void deleteItems(itemType itemType, Object payload, final JsonArrayCallback callback) {

        Call<JsonArray> responseCall = interfaces.deleteMultiplesItem(getHeader(), itemType.name(), payload);
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

    /**
     * This endpoint allows to request password reset
     * @param email email address of the user to recover
     * @param callback here your are going to get the asynchronous response
     */
    public void lostPassword(String email, final VoidCallback callback) {

        LostPasswordRequest requestPost = new LostPasswordRequest(email);

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

    /**
     * This endpoint allows to request password recovery
     * @param email email address of the user to recover
     * @param token reset token
     * @param newPassword the new password for the user
     * @param callback here your are going to get the asynchronous response
     */
    public void recoveryPassword(String email, String token, String newPassword, final VoidCallback callback) {

        RecoveryPasswordRequest requestPost = new RecoveryPasswordRequest(email, token, newPassword);

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

    /**
     * Interface definition for a callback to be invoked when a user init session.
     */
    public interface InitSessionCallback {
        void onResponse(InitSession response);
        void onFailure(String errorMessage);
    }

    /**
     * Interface definition for a callback to be invoked when an endpoint return a Json Object.
     */
    public interface JsonObjectCallback {
        void onResponse(JsonObject response);
        void onFailure(String errorMessage);
    }

    /**
     * Interface definition for a callback to be invoked when an endpoint return a Json Array.
     */
    public interface JsonArrayCallback {
        void onResponse(JsonArray response);
        void onFailure(String errorMessage);
    }

    /**
     * Interface definition for a callback to be invoked when an endpoint return void.
     */
    public interface VoidCallback {
        void onResponse(String response);
        void onFailure(String errorMessage);
    }


}
