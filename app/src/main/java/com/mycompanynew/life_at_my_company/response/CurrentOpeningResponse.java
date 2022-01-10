package com.mycompanynew.life_at_my_company.response;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CurrentOpeningResponse {

    @SerializedName("current_opening")
    private List<CurrentOpeningItem> openingItemList;

    @SerializedName("message")
     private String message;

    @SerializedName("status")
    private String status;

    public List<CurrentOpeningItem> getOpeningItemList() {
        return openingItemList;
    }

    public void setOpeningItemList(List<CurrentOpeningItem> openingItemList) {
        this.openingItemList = openingItemList;
    }

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
