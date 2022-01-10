package com.mycompanynew.our_services.response;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ServicesDetailsResponse {

    @SerializedName("company_service_details_id")
    private String companyServiceDetailsId;

    @SerializedName("company_service_id")
    private String companyServiceId;

    @SerializedName("society_id")
    private String societyId;

    @SerializedName("company_service_details_title")
    private String companyServiceDetailsTitle;

    @SerializedName("company_service_details_description")
    private String companyServiceDetailsDescription;

    @SerializedName("service_list")
    private List<ServiceDetailsItem> serviceDetailsItemList;

    @SerializedName("data_list")
    private List<DataDetailsItem> dataDetailsItemList;

    @SerializedName("message")
    private String message;

    @SerializedName("status")
    private String status;

    public String getCompanyServiceDetailsId() {
        return companyServiceDetailsId;
    }

    public void setCompanyServiceDetailsId(String companyServiceDetailsId) {
        this.companyServiceDetailsId = companyServiceDetailsId;
    }

    public String getCompanyServiceId() {
        return companyServiceId;
    }

    public void setCompanyServiceId(String companyServiceId) {
        this.companyServiceId = companyServiceId;
    }

    public String getSocietyId() {
        return societyId;
    }

    public void setSocietyId(String societyId) {
        this.societyId = societyId;
    }

    public String getCompanyServiceDetailsTitle() {
        return companyServiceDetailsTitle;
    }

    public void setCompanyServiceDetailsTitle(String companyServiceDetailsTitle) {
        this.companyServiceDetailsTitle = companyServiceDetailsTitle;
    }

    public String getCompanyServiceDetailsDescription() {
        return companyServiceDetailsDescription;
    }

    public void setCompanyServiceDetailsDescription(String companyServiceDetailsDescription) {
        this.companyServiceDetailsDescription = companyServiceDetailsDescription;
    }

    public List<ServiceDetailsItem> getServiceDetailsItemList() {
        return serviceDetailsItemList;
    }

    public void setServiceDetailsItemList(List<ServiceDetailsItem> serviceDetailsItemList) {
        this.serviceDetailsItemList = serviceDetailsItemList;
    }

    public List<DataDetailsItem> getDataDetailsItemList() {
        return dataDetailsItemList;
    }

    public void setDataDetailsItemList(List<DataDetailsItem> dataDetailsItemList) {
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
