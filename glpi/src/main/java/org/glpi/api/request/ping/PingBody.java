package org.glpi.api.request.ping;

import com.google.gson.annotations.SerializedName;

public class PingBody {

    @SerializedName("input")
    private InputPing input;

    public void setInput(InputPing input) {
        this.input = input;
    }

    public InputPing getInput() {
        return input;
    }
}