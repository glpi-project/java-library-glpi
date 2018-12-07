package org.glpi.api.request.inventory;

import com.google.gson.annotations.SerializedName;

public class InventoryBody {

    @SerializedName("input")
    private InputInventory input;

    public void setInput(InputInventory input) {
        this.input = input;
    }

    public InputInventory getInput() {
        return input;
    }
}