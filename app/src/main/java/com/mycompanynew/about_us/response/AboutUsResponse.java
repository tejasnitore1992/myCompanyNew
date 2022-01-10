package com.mycompanynew.about_us.response;

import com.google.gson.annotations.SerializedName;

public class AboutUsResponse {

    @SerializedName("company_about_us_id")
    private String companyAboutUsId;

    @SerializedName("company_about_us_top_image")
    private String companyAboutUsTopImage;

    @SerializedName("company_about_us_top_description")
    private String companyAboutUsTopDescription;

    @SerializedName("company_about_us_image_one")
    private String companyAboutUsImageOne;

    @SerializedName("company_about_us_image_two")
    private String companyAboutUsImageTwo;

    @SerializedName("company_about_us_bottom_title")
    private String companyAboutUsBottomTitle;

    @SerializedName("company_about_us_bottom_description")
    private String companyAboutUsBottomDescription;

    @SerializedName("message")
    private String message;

    @SerializedName("status")
    private String status;

    public String getCompanyAboutUsId() {
        return companyAboutUsId;
    }

    public void setCompanyAboutUsId(String companyAboutUsId) {
        this.companyAboutUsId = companyAboutUsId;
    }

    public String getCompanyAboutUsTopImage() {
        return companyAboutUsTopImage;
    }

    public void setCompanyAboutUsTopImage(String companyAboutUsTopImage) {
        this.companyAboutUsTopImage = companyAboutUsTopImage;
    }

    public String getCompanyAboutUsTopDescription() {
        return companyAboutUsTopDescription;
    }

    public void setCompanyAboutUsTopDescription(String companyAboutUsTopDescription) {
        this.companyAboutUsTopDescription = companyAboutUsTopDescription;
    }

    public String getCompanyAboutUsImageOne() {
        return companyAboutUsImageOne;
    }

    public void setCompanyAboutUsImageOne(String companyAboutUsImageOne) {
        this.companyAboutUsImageOne = companyAboutUsImageOne;
    }

    public String getCompanyAboutUsImageTwo() {
        return companyAboutUsImageTwo;
    }

    public void setCompanyAboutUsImageTwo(String companyAboutUsImageTwo) {
        this.companyAboutUsImageTwo = companyAboutUsImageTwo;
    }

    public String getCompanyAboutUsBottomTitle() {
        return companyAboutUsBottomTitle;
    }

    public void setCompanyAboutUsBottomTitle(String companyAboutUsBottomTitle) {
        this.companyAboutUsBottomTitle = companyAboutUsBottomTitle;
    }

    public String getCompanyAboutUsBottomDescription() {
        return companyAboutUsBottomDescription;
    }

    public void setCompanyAboutUsBottomDescription(String companyAboutUsBottomDescription) {
        this.companyAboutUsBottomDescription = companyAboutUsBottomDescription;
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
