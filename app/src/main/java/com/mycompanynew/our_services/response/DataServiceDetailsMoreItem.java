package com.mycompanynew.our_services.response;

import com.google.gson.annotations.SerializedName;

public class DataServiceDetailsMoreItem {

    @SerializedName("company_service_more_details_list_id")
    private String companyServiceMoreDetailsListId;

    @SerializedName("company_service_more_detail_list_name")
    private String companyServiceMoreDetailListName;

    public String getCompanyServiceMoreDetailsListId() {
        return companyServiceMoreDetailsListId;
    }

    public void setCompanyServiceMoreDetailsListId(String companyServiceMoreDetailsListId) {
        this.companyServiceMoreDetailsListId = companyServiceMoreDetailsListId;
    }

    public String getCompanyServiceMoreDetailListName() {
        return companyServiceMoreDetailListName;
    }

    public void setCompanyServiceMoreDetailListName(String companyServiceMoreDetailListName) {
        this.companyServiceMoreDetailListName = companyServiceMoreDetailListName;
    }
}
