package org.flyve.glpi;

import org.flyve.glpi.response.InitSession;
import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;

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
    Call<JSONObject> killSession(@Header("Session-Token") String sessionToken);

    @Headers("Content-Type: application/json")
    @GET("getMyProfiles")
    Call<JSONObject> getMyProfiles(@Header("Session-Token") String sessionToken);

}
