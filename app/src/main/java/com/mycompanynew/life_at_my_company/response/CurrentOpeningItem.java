package com.mycompanynew.life_at_my_company.response;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class CurrentOpeningItem implements Serializable {

    @SerializedName("company_current_opening_id")
    private String companyCurrentOpeningId;
    @SerializedName("company_current_opening_title")
    private String companyCurrentOpeningTitle;
    @SerializedName("company_current_opening_position")
    private String companyCurrentOpeningPosition;
    @SerializedName("company_current_opening_timing")
    private String companyCurrentOpeningTiming;
    @SerializedName("company_current_opening_address")
    private String companyCurrentOpeningAddress;

    public String getCompanyCurrentOpeningId() {
        return companyCurrentOpeningId;
    }

    public void setCompanyCurrentOpeningId(String companyCurrentOpeningId) {
        this.companyCurrentOpeningId = companyCurrentOpeningId;
    }

    public String getCompanyCurrentOpeningTitle() {
        return companyCurrentOpeningTitle;
    }

    public void setCompanyCurrentOpeningTitle(String companyCurrentOpeningTitle) {
        this.companyCurrentOpeningTitle = companyCurrentOpeningTitle;
    }

    public String getCompanyCurrentOpeningPosition() {
        return companyCurrentOpeningPosition;
    }

    public void setCompanyCurrentOpeningPosition(String companyCurrentOpeningPosition) {
        this.companyCurrentOpeningPosition = companyCurrentOpeningPosition;
    }

    public String getCompanyCurrentOpeningTiming() {
        return companyCurrentOpeningTiming;
    }

    public void setCompanyCurrentOpeningTiming(String companyCurrentOpeningTiming) {
        this.companyCurrentOpeningTiming = companyCurrentOpeningTiming;
    }

    public String getCompanyCurrentOpeningAddress() {
        return companyCurrentOpeningAddress;
    }

    public void setCompanyCurrentOpeningAddress(String companyCurrentOpeningAddress) {
        this.companyCurrentOpeningAddress = companyCurrentOpeningAddress;
    }

    @Override
    public String toString() {
         return companyCurrentOpeningTitle ;
    }
}
