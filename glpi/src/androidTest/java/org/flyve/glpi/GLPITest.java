package org.flyve.glpi;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import org.flyve.glpi.response.InitSession;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class GLPITest {
    // Context of the app under test.
    Context appContext = InstrumentationRegistry.getTargetContext();
    String sessionToken = "";

    @Test
    public void initSessionTest() throws Exception {
        GLPI glpi = new GLPI(appContext, "https://dev.flyve.org/glpi/apirest.php/");
        glpi.initSessionByCredentials("rafaelje@gmail.com", "12345678", new GLPI.InitSessionCallback() {
            @Override
            public void onResponse(InitSession response) {
                sessionToken = response.getSessionToken();
                assertNotEquals("", sessionToken);
            }

            @Override
            public void onFailure(String errorMessage) {
                Assert.assertTrue(false);
            }
        });
    }



}
