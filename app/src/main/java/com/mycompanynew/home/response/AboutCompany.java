package com.mycompanynew.home.response;

import com.google.gson.annotations.SerializedName;

public class AboutCompany{

	@SerializedName("company_home_title")
	private String companyHomeTitle;

	@SerializedName("company_home_description")
	private String companyHomeDescription;

	@SerializedName("company_home_id")
	private String companyHomeId;

	public void setCompanyHomeTitle(String companyHomeTitle){
		this.companyHomeTitle = companyHomeTitle;
	}

	public String getCompanyHomeTitle(){
		return companyHomeTitle;
	}

	public void setCompanyHomeDescription(String companyHomeDescription){
		this.companyHomeDescription = companyHomeDescription;
	}

	public String getCompanyHomeDescription(){
		return companyHomeDescription;
	}

	public void setCompanyHomeId(String companyHomeId){
		this.companyHomeId = companyHomeId;
	}

	public String getCompanyHomeId(){
		return companyHomeId;
	}
}