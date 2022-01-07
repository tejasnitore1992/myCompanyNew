package com.mycompanynew.home.response;

import com.google.gson.annotations.SerializedName;

public class SliderItem{

	@SerializedName("company_home_slider_id")
	private String companyHomeSliderId;

	@SerializedName("company_home_slider_image")
	private String companyHomeSliderImage;

	@SerializedName("company_home_slider_description")
	private String companyHomeSliderDescription;

	@SerializedName("company_home_slider_mobile")
	private String companyHomeSliderMobile;

	@SerializedName("company_home_slider_title")
	private String companyHomeSliderTitle;

	@SerializedName("company_home_slider_url")
	private String companyHomeSliderUrl;

	public void setCompanyHomeSliderId(String companyHomeSliderId){
		this.companyHomeSliderId = companyHomeSliderId;
	}

	public String getCompanyHomeSliderId(){
		return companyHomeSliderId;
	}

	public void setCompanyHomeSliderImage(String companyHomeSliderImage){
		this.companyHomeSliderImage = companyHomeSliderImage;
	}

	public String getCompanyHomeSliderImage(){
		return companyHomeSliderImage;
	}

	public void setCompanyHomeSliderDescription(String companyHomeSliderDescription){
		this.companyHomeSliderDescription = companyHomeSliderDescription;
	}

	public String getCompanyHomeSliderDescription(){
		return companyHomeSliderDescription;
	}

	public void setCompanyHomeSliderMobile(String companyHomeSliderMobile){
		this.companyHomeSliderMobile = companyHomeSliderMobile;
	}

	public String getCompanyHomeSliderMobile(){
		return companyHomeSliderMobile;
	}

	public void setCompanyHomeSliderTitle(String companyHomeSliderTitle){
		this.companyHomeSliderTitle = companyHomeSliderTitle;
	}

	public String getCompanyHomeSliderTitle(){
		return companyHomeSliderTitle;
	}

	public void setCompanyHomeSliderUrl(String companyHomeSliderUrl){
		this.companyHomeSliderUrl = companyHomeSliderUrl;
	}

	public String getCompanyHomeSliderUrl(){
		return companyHomeSliderUrl;
	}
}