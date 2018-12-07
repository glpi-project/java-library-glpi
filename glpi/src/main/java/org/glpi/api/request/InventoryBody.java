package org.glpi.api.request;

import com.google.gson.annotations.SerializedName;

public class InventoryBody {

    @SerializedName("_inventory")
    private String inventory;

    public void setInventory(String inventory) {
        this.inventory = inventory;
    }

    public String getInventory() {
        return inventory;
    }
}