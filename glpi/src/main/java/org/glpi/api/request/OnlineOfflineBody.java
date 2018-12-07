package org.glpi.api.request;

import com.google.gson.annotations.SerializedName;

public class OnlineOfflineBody {

    @SerializedName("is_online")
    private String isOnline;

    public void setIsOnline(String isOnline) {
        this.isOnline = isOnline;
    }

    public String getIsOnline() {
        return isOnline;
    }
}