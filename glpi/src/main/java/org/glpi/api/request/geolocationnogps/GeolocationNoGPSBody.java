package org.glpi.api.request.geolocationnogps;

import com.google.gson.annotations.SerializedName;

public class GeolocationNoGPSBody {

    @SerializedName("input")
    private InputNoGeolocation input;

    public void setInput(InputNoGeolocation input) {
        this.input = input;
    }

    public InputNoGeolocation getInput() {
        return input;
    }
}