package org.glpi.api.request.taskstatus;

import com.google.gson.annotations.SerializedName;

public class InputTaskStatus {

    @SerializedName("status")
    private String status;

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }
}