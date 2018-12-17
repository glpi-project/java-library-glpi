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

package org.glpi.api.request;

import com.google.gson.annotations.SerializedName;

public class RecoveryPasswordRequest {

    @SerializedName("email")
    private String email;

    /**
     * Create a request for Lost Password
     * @param string email
     */
    public RecoveryPasswordRequest(String email) {
        this.email = email;
    }

    /**
     * get email
     * @return the email of the user to recover
     */
    public String getEmail() {
        return email;
    }

    /**
     * Set email
     * @param string email
     */
    public void setEmail(String email) {
        this.email = email;
    }
}
