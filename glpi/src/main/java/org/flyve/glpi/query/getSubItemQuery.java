package org.flyve.glpi.query;

import android.content.Context;

import org.flyve.glpi.R;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static android.R.attr.data;

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
 * @date      24/10/17
 * @copyright Copyright © 2017 Teclib. All rights reserved.
 * @license   GPLv3 https://www.gnu.org/licenses/gpl-3.0.html
 * @link      https://github.com/flyve-mdm/flyve-mdm-android
 * @link      https://flyve-mdm.com
 * ------------------------------------------------------------------------------
 */
public class getSubItemQuery {

    private Boolean expandDropdowns;
    private Boolean getHateoas;
    private Boolean onlyId;
    private String range;
    private String sort;
    private String order;
    private Context context;

    public getSubItemQuery(Context context) {
        this.context = context;
    }

    public Map<String, String> getQuery() {

        Map<String, String> map = new HashMap<>();

        if(expandDropdowns!=null) {
            map.put("expand_dropdowns", expandDropdowns.toString());
        }

        if(getHateoas!=null) {
            map.put("get_hateoas", getHateoas.toString());
        }

        if(onlyId!=null) {
            map.put("only_id", onlyId.toString());
        }

        if(range!=null) {
            map.put("range", range);
        }

        if(sort!=null) {
            map.put("sort", sort);
        }

        if(order!=null) {
            map.put("order", order);
        }

        return map;
    }

    public void setExpandDropdowns(Boolean expandDropdowns) {
        this.expandDropdowns = expandDropdowns;
    }

    public void setGetHateoas(Boolean getHateoas) {
        this.getHateoas = getHateoas;
    }

    public void setOnlyId(Boolean onlyId) {
        this.onlyId = onlyId;
    }

    public void setRange(int min, int max) {
        if(max>=min) {
            throw new RuntimeException(context.getResources().getString(R.string.error_range));
        }

        this.range = min + "-" + max;
    }

    public void setSort(int sort) {
        this.sort = String.valueOf(sort);
    }

    public void setOrder(Order order) {
        this.order = order.name();
    }

    public enum Order {
        DESC,
        ASC
    }
}
