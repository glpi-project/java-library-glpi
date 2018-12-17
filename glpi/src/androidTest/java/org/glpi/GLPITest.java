/**
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
 *  --------------------------------------------------------------------------------
 *  @copyright Copyright © 2018 Teclib. All rights reserved.
 *  @license   GPLv3 https://www.gnu.org/licenses/gpl-3.0.html
 *  @link      https://github.com/glpi-project/java-library-glpi
 *  @link      http://www.glpi-project.org/
 *  @link      https://glpi-project.github.io/java-library-glpi/
 *  @link      https://bintray.com/glpi-project/teclib-repository/java-library-glpi
 *  --------------------------------------------------------------------------------
 */

package org.glpi;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import org.glpi.api.BuildConfig;
import org.glpi.api.GLPI;
import org.glpi.api.response.InitSession;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertNotEquals;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class GLPITest {
    // Context of the app under test.
    Context appContext = InstrumentationRegistry.getTargetContext();

    @Test
    public void initSessionTest() throws Exception {
        if (!BuildConfig.GLPI_URL.equals("")) {
            GLPI glpi = new GLPI(appContext, BuildConfig.GLPI_URL);
            glpi.initSessionByCredentials(BuildConfig.GLPI_USER, BuildConfig.GLPI_PASSWORD, new GLPI.ResponseHandle<InitSession, String>() {
                @Override
                public void onResponse(InitSession response) {
                    String sessionToken = response.getSessionToken();
                    assertNotEquals("", sessionToken);
                }

                @Override
                public void onFailure(String errorMessage) {
                    Assert.assertTrue(false);
                }
            });
        } else {
            Assert.assertTrue(true);
        }
    }
}
