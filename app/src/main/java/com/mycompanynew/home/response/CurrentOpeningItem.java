package com.mycompanynew.home.response;

import com.google.gson.annotations.SerializedName;

public class CurrentOpeningItem{

	@SerializedName("company_current_opening_address")
	private String companyCurrentOpeningAddress;

	@SerializedName("company_current_opening_title")
	private String companyCurrentOpeningTitle;

	@SerializedName("company_current_opening_id")
	private String companyCurrentOpeningId;

	@SerializedName("company_current_opening_position")
	private String companyCurrentOpeningPosition;

	@SerializedName("company_current_opening_timing")
	private String companyCurrentOpeningTiming;

	public void setCompanyCurrentOpeningAddress(String companyCurrentOpeningAddress){
		this.companyCurrentOpeningAddress = companyCurrentOpeningAddress;
	}

	public String getCompanyCurrentOpeningAddress(){
		return companyCurrentOpeningAddress;
	}

	public void setCompanyCurrentOpeningTitle(String companyCurrentOpeningTitle){
		this.companyCurrentOpeningTitle = companyCurrentOpeningTitle;
	}

	public String getCompanyCurrentOpeningTitle(){
		return companyCurrentOpeningTitle;
	}

	public void setCompanyCurrentOpeningId(String companyCurrentOpeningId){
		this.companyCurrentOpeningId = companyCurrentOpeningId;
	}

	public String getCompanyCurrentOpeningId(){
		return companyCurrentOpeningId;
	}

	public void setCompanyCurrentOpeningPosition(String companyCurrentOpeningPosition){
		this.companyCurrentOpeningPosition = companyCurrentOpeningPosition;
	}

	public String getCompanyCurrentOpeningPosition(){
		return companyCurrentOpeningPosition;
	}

	public void setCompanyCurrentOpeningTiming(String companyCurrentOpeningTiming){
		this.companyCurrentOpeningTiming = companyCurrentOpeningTiming;
	}

	public String getCompanyCurrentOpeningTiming(){
		return companyCurrentOpeningTiming;
	}
}