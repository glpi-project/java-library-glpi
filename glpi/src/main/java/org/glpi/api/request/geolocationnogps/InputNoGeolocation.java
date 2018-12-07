package org.glpi.api.request.geolocationnogps;

import com.google.gson.annotations.SerializedName;

public class InputNoGeolocation {

    @SerializedName("_datetime")
    private String datetime;

    @SerializedName("_agents_id")
    private String agentsId;

    @SerializedName("_gps")
    private String gps;

    public void setDatetime(String datetime) {
        this.datetime = datetime;
    }

    public String getDatetime() {
        return datetime;
    }

    public void setAgentsId(String agentsId) {
        this.agentsId = agentsId;
    }

    public String getAgentsId() {
        return agentsId;
    }

    public void setGps(String gps) {
        this.gps = gps;
    }

    public String getGps() {
        return gps;
    }
}