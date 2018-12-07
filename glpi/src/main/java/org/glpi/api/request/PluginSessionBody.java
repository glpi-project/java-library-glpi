package org.glpi.api.request;

import com.google.gson.annotations.SerializedName;

public class PluginSessionBody {

    @SerializedName("input")
    private Input input;

    public void setInput(Input input) {
        this.input = input;
    }

    public Input getInput() {
        return input;
    }
}