package org.glpi.api.request.status;

import com.google.gson.annotations.SerializedName;

public class OnlineOfflineBody {

    @SerializedName("input")
    private InputIsOnline input;

    public void setInput(InputIsOnline input) {
        this.input = input;
    }

    public InputIsOnline getInput() {
        return input;
    }
}