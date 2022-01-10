package com.mycompanynew.our_services.response;

import com.google.gson.annotations.SerializedName;

public class ServiceDetailsItem {

    @SerializedName("company_service_detail_list_id")
    private String companyServiceDetailListId;

    @SerializedName("company_service_detail_list_name")
    private String companyServiceDetailListName;

    public String getCompanyServiceDetailListId() {
        return companyServiceDetailListId;
    }

    public void setCompanyServiceDetailListId(String companyServiceDetailListId) {
        this.companyServiceDetailListId = companyServiceDetailListId;
    }

    public String getCompanyServiceDetailListName() {
        return companyServiceDetailListName;
    }

    public void setCompanyServiceDetailListName(String companyServiceDetailListName) {
        this.companyServiceDetailListName = companyServiceDetailListName;
    }
}
