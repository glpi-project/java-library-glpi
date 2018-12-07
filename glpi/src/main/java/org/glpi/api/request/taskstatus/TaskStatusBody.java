package org.glpi.api.request.taskstatus;

import com.google.gson.annotations.SerializedName;

public class TaskStatusBody {

    @SerializedName("input")
    private InputTaskStatus input;

    public void setInput(InputTaskStatus input) {
        this.input = input;
    }

    public InputTaskStatus getInput() {
        return input;
    }
}