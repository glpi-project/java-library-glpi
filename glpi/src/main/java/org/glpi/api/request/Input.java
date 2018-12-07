package org.glpi.api.request;

import com.google.gson.annotations.SerializedName;

public class Input {

    @SerializedName("_invitation_token")
    private String invitationToken;

    @SerializedName("_serial")
    private String serial;

    @SerializedName("firstname")
    private String firstname;

    @SerializedName("_email")
    private String email;

    @SerializedName("csr")
    private String csr;

    @SerializedName("phone")
    private String phone;

    @SerializedName("has_system_permission")
    private String hasSystemPermission;

    @SerializedName("_uuid")
    private String uuid;

    @SerializedName("type")
    private String type;

    @SerializedName("inventory")
    private String inventory;

    @SerializedName("version")
    private String version;

    @SerializedName("lastname")
    private String lastname;

    public void setInvitationToken(String invitationToken) {
        this.invitationToken = invitationToken;
    }

    public String getInvitationToken() {
        return invitationToken;
    }

    public void setSerial(String serial) {
        this.serial = serial;
    }

    public String getSerial() {
        return serial;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setCsr(String csr) {
        this.csr = csr;
    }

    public String getCsr() {
        return csr;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPhone() {
        return phone;
    }

    public void setHasSystemPermission(String hasSystemPermission) {
        this.hasSystemPermission = hasSystemPermission;
    }

    public String getHasSystemPermission() {
        return hasSystemPermission;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getUuid() {
        return uuid;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setInventory(String inventory) {
        this.inventory = inventory;
    }

    public String getInventory() {
        return inventory;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getVersion() {
        return version;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getLastname() {
        return lastname;
    }
}