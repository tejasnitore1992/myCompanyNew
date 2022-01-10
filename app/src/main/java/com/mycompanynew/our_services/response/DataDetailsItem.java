package com.mycompanynew.our_services.response;

import com.google.gson.annotations.SerializedName;

public class DataDetailsItem {

    @SerializedName("company_service_more_details_id")
    private String companyServiceMoreDetailsId;

    @SerializedName("company_service_more_details_name")
    private String companyServiceMoreDetailsName;

    @SerializedName("company_service_more_details_image")
    private String companyServiceMoreDetailsImage;

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

    public String getCompanyServiceMoreDetailsImage() {
        return companyServiceMoreDetailsImage;
    }

    public void setCompanyServiceMoreDetailsImage(String companyServiceMoreDetailsImage) {
        this.companyServiceMoreDetailsImage = companyServiceMoreDetailsImage;
    }
}
