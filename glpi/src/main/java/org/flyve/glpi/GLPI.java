package org.flyve.glpi;

import org.flyve.glpi.response.InitSession;
import org.flyve.glpi.utils.Helpers;

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

    public Routes interfaces;

    public GLPI(String glpiUrl) {
        start("https://dev.flyve.org/glpi/apirest.php/");
        interfaces = retrofit.create(Routes.class);
    }

    public void initSessionByUserToken(String userToken, final initSessionCallback callback) {
        Call<InitSession> responseCall = interfaces.initSessionByUserToken("user_token " + userToken.trim());
        responseCall.enqueue(new Callback<InitSession>() {
            @Override
            public void onResponse(Call<InitSession> call, Response<InitSession> response) {
                if(response.isSuccessful()) {
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

    public void killsession() {


    }
    public void getMyProfiles() {}
    public void getActiveProfile() {}
    public void changeActiveProfile() {}
    public void getMyEntities() {}
    public void getActiveEntities() {}
    public void changeActiveEntities() {}
    public void getFullSession() {}
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

}
