package com.mycompanynew.home.response;

import com.google.gson.annotations.SerializedName;

public class ServiceListItem{

	@SerializedName("company_service_detail_list_name")
	private String companyServiceDetailListName;

	@SerializedName("company_service_detail_list_id")
	private String companyServiceDetailListId;

	public void setCompanyServiceDetailListName(String companyServiceDetailListName){
		this.companyServiceDetailListName = companyServiceDetailListName;
	}

	public String getCompanyServiceDetailListName(){
		return companyServiceDetailListName;
	}

	public void setCompanyServiceDetailListId(String companyServiceDetailListId){
		this.companyServiceDetailListId = companyServiceDetailListId;
	}

	public String getCompanyServiceDetailListId(){
		return companyServiceDetailListId;
	}
}