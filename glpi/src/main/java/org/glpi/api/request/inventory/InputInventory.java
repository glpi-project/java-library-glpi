package org.glpi.api.request.inventory;

import com.google.gson.annotations.SerializedName;

public class InputInventory {

    @SerializedName("_inventory")
    private String inventory;

    public void setInventory(String inventory) {
        this.inventory = inventory;
    }

    public String getInventory() {
        return inventory;
    }
}