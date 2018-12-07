package org.glpi.api.request.geolocation;

import com.google.gson.annotations.SerializedName;

public class GeolocationBody {

    @SerializedName("input")
    private InputGeolocation input;

    public void setInput(InputGeolocation input) {
        this.input = input;
    }

    public InputGeolocation getInput() {
        return input;
    }
}