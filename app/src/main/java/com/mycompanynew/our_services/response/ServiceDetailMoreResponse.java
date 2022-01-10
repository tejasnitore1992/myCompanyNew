package com.mycompanynew.our_services.response;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ServiceDetailMoreResponse {

    @SerializedName("company_service_more_details_id")
    private String companyServiceMoreDetailsId;

    @SerializedName("company_service_more_details_name")
    private String companyServiceMoreDetailsName;

    @SerializedName("company_service_more_details_title")
    private String companyServiceMoreDetailsTitle;

    @SerializedName("company_service_more_details_description")
    private String companyServiceMoreDetailsDescription;

    @SerializedName("company_service_more_details_image")
    private String companyServiceMoreDetailsImage;

    @SerializedName("data_list")
    private List<DataServiceDetailsMoreItem> dataDetailsItemList;

    @SerializedName("message")
    private String message;

    @SerializedName("status")
    private String status;

    public String getCompanyServiceMoreDetailsId() {
        return companyServiceMoreDetailsId;
    }

    public void setCompanyServiceMoreDetailsId(String companyServiceMoreDetailsId) {
        this.companyServiceMoreDetailsId = companyServiceMoreDetailsId;
    }

    public String getCompanyServiceMoreDetailsName() {
        return companyServiceMoreDetailsName;
    }

    public void setCompanyServiceMoreDetailsName(String companyServiceMoreDetailsName) {
        this.companyServiceMoreDetailsName = companyServiceMoreDetailsName;
    }

    public String getCompanyServiceMoreDetailsTitle() {
        return companyServiceMoreDetailsTitle;
    }

    public void setCompanyServiceMoreDetailsTitle(String companyServiceMoreDetailsTitle) {
        this.companyServiceMoreDetailsTitle = companyServiceMoreDetailsTitle;
    }

    public String getCompanyServiceMoreDetailsDescription() {
        return companyServiceMoreDetailsDescription;
    }

    public void setCompanyServiceMoreDetailsDescription(String companyServiceMoreDetailsDescription) {
        this.companyServiceMoreDetailsDescription = companyServiceMoreDetailsDescription;
    }

    public String getCompanyServiceMoreDetailsImage() {
        return companyServiceMoreDetailsImage;
    }

    public void setCompanyServiceMoreDetailsImage(String companyServiceMoreDetailsImage) {
        this.companyServiceMoreDetailsImage = companyServiceMoreDetailsImage;
    }

    public List<DataServiceDetailsMoreItem> getDataDetailsItemList() {
        return dataDetailsItemList;
    }

    public void setDataDetailsItemList(List<DataServiceDetailsMoreItem> dataDetailsItemList) {
        this.dataDetailsItemList = dataDetailsItemList;
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
