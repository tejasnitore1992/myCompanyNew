package com.mycompanynew.life_at_my_company.response;

import com.google.gson.annotations.SerializedName;

public class ApplyOpeningResponse {

    @SerializedName("message")
    private String message;

    @SerializedName("status")
    public String status;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
