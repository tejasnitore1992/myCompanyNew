package com.mycompanynew.our_services.response;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class OurServicesResponse {

    @SerializedName("our_services")
    private List<OurServicesItem> servicesItemList;

    @SerializedName("message")
    private String message;

    @SerializedName("status")
    private String status;

    public List<OurServicesItem> getServicesItemList() {
        return servicesItemList;
    }

    public void setServicesItemList(List<OurServicesItem> servicesItemList) {
        this.servicesItemList = servicesItemList;
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
