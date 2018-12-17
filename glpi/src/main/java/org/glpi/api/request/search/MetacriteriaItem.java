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

package org.glpi.api.request.search;

import com.google.gson.annotations.SerializedName;

public class MetacriteriaItem {

    @SerializedName("itemtype")
    private String itemtype;

    @SerializedName("field")
    private int field;

    @SerializedName("searchtype")
    private String searchtype;

    @SerializedName("link")
    private String link;

    @SerializedName("value")
    private String value;

    public void setItemtype(String itemtype) {
        this.itemtype = itemtype;
    }

    public String getItemtype() {
        return itemtype;
    }

    public void setField(int field) {
        this.field = field;
    }

    public int getField() {
        return field;
    }

    public void setSearchtype(String searchtype) {
        this.searchtype = searchtype;
    }

    public String getSearchtype() {
        return searchtype;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getLink() {
        return link;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}