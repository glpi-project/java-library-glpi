package org.glpi.api.request;

import com.google.gson.annotations.SerializedName;

public class PingBody {

    @SerializedName("_pong")
    private String pong;

    public void setPong(String pong) {
        this.pong = pong;
    }

    public String getPong() {
        return pong;
    }
}