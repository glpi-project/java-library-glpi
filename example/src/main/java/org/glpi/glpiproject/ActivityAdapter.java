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
 *  @link      https://bintray.com/glpi-project/teclib-repository/java-library-glpi
 *  --------------------------------------------------------------------------------
 */

package org.glpi.glpiproject;

import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class ActivityAdapter extends RecyclerView.Adapter<ActivityAdapter.ActivityHolder> {

    private List<String> data;

    ActivityAdapter(List<String> data) {
        this.data = data;
    }

    @Override
    public ActivityHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        int layout = R.layout.list_item_activity;
        View view = LayoutInflater.from(parent.getContext()).inflate(layout, parent, false);
        return new ActivityHolder(view);
    }

    @Override
    public void onBindViewHolder(ActivityHolder holder, int position) {
        String text = data.get(position);
        if (text.contains("Success")) {
            holder.title.setTextColor(Color.parseColor("#25cf3c"));
        } else {
            holder.title.setTextColor(Color.parseColor("#ff4040"));
        }
        holder.title.setText(text);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class ActivityHolder extends RecyclerView.ViewHolder {
        TextView title;

        ActivityHolder(View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.textResult);
        }
    }
}
