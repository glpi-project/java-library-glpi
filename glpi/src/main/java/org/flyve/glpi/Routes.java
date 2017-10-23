package org.flyve.glpi;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import org.flyve.glpi.request.changeActiveEntitiesRequest;
import org.flyve.glpi.request.changeActiveProfileRequest;
import org.flyve.glpi.response.InitSession;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.HTTP;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

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
public interface Routes {

    @Headers("Content-Type: application/json")
    @GET("initSession")
    Call<InitSession> initSessionByUserToken(@Header("Authorization") String authorization);

    @Headers("Content-Type: application/json")
    @GET("initSession")
    Call<InitSession> initSessionByCredentials(@Header("Authorization") String authorization);

    @Headers("Content-Type: application/json")
    @GET("killSession")
    Call<Void> killSession(@Header("Session-Token") String sessionToken);

    @Headers("Content-Type: application/json")
    @GET("getMyProfiles")
    Call<JsonObject> getMyProfiles(@Header("Session-Token") String sessionToken);

    @Headers("Content-Type: application/json")
    @GET("getActiveProfile")
    Call<JsonObject> getActiveProfile(@Header("Session-Token") String sessionToken);

    @Headers("Content-Type: application/json")
    @GET("getMyEntities")
    Call<JsonObject> getMyEntities(@Header("Session-Token") String sessionToken);

    @Headers("Content-Type: application/json")
    @GET("getActiveEntities")
    Call<JsonObject> getActiveEntities(@Header("Session-Token") String sessionToken);

    @Headers("Content-Type: application/json")
    @GET("getFullSession")
    Call<JsonObject> getFullSession(@Header("Session-Token") String sessionToken);

    @Headers("Content-Type: application/json")
    @GET("getGlpiConfig")
    Call<JsonObject> getGlpiConfig(@Header("Session-Token") String sessionToken);

    @Headers("Content-Type: application/json")
    @GET("{itemType}")
    Call<JsonArray> getAllItem(@Header("Session-Token") String sessionToken, @Path("itemType") String itemType);

    @Headers("Content-Type: application/json")
    @GET("{itemType}/{id}")
    Call<JsonObject> getAnItem(@Header("Session-Token") String sessionToken, @Path("itemType") String itemType, @Path("id") String id);

    @Headers("Content-Type: application/json")
    @GET("{itemType}/{id}/{subItemType}")
    Call<JsonObject> getSubItem(@Header("Session-Token") String sessionToken, @Path("itemType") String itemType, @Path("id") String id, @Path("subItemType") String subItemType);


    @Headers("Content-Type: application/json")
    @POST("changeActiveProfile")
    Call<Void> changeActiveProfile(@Header("Session-Token") String sessionToken,  @Body changeActiveProfileRequest requestPost);

    @Headers("Content-Type: application/json")
    @POST("changeActiveEntities")
    Call<Void> changeActiveEntities(@Header("Session-Token") String sessionToken,  @Body changeActiveEntitiesRequest requestPost);

    @Headers("Content-Type: application/json")
    @POST("{itemType}")
    Call<JsonArray> addItem(@Header("Session-Token") String sessionToken, @Path("itemType") String itemType, @Body Object requestPost);

    @Headers("Content-Type: application/json")
    @PUT("{itemType}/{id}")
    Call<JsonArray> updateItem(@Header("Session-Token") String sessionToken, @Path("itemType") String itemType, @Path("id") String id, @Body Object requestPost);

    @Headers("Content-Type: application/json")
    @DELETE("{itemType}/{id}")
    Call<JsonArray> deleteItem(@Header("Session-Token") String sessionToken, @Path("itemType") String itemType, @Path("id") String id);

    @Headers("Content-Type: application/json")
    @HTTP(method = "DELETE", path = "{itemType}", hasBody = true)
    Call<JsonArray> deleteMultiplesItem(@Header("Session-Token") String sessionToken, @Path("itemType") String itemType, @Body Object requestPost);

}
