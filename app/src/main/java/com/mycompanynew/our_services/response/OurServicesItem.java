package com.mycompanynew.our_services.response;

import com.google.gson.annotations.SerializedName;

public class OurServicesItem {

    @SerializedName("company_service_id")
    private String companyServiceId;

    @SerializedName("company_service_image")
    private String companyServiceImage;

    @SerializedName("company_service_name")
    private String companyServiceName;

    public String getCompanyServiceId() {
        return companyServiceId;
    }

    public void setCompanyServiceId(String companyServiceId) {
        this.companyServiceId = companyServiceId;
    }

    public String getCompanyServiceImage() {
        return companyServiceImage;
    }

    public void setCompanyServiceImage(String companyServiceImage) {
        this.companyServiceImage = companyServiceImage;
    }

    public String getCompanyServiceName() {
        return companyServiceName;
    }

    public void setCompanyServiceName(String companyServiceName) {
        this.companyServiceName = companyServiceName;
    }
}
