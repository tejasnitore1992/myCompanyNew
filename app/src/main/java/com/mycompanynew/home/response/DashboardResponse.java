package com.mycompanynew.home.response;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class DashboardResponse{

	@SerializedName("slider")
	private List<SliderItem> slider;

	@SerializedName("about_company")
	private AboutCompany aboutCompany;

	@SerializedName("our_services")
	private List<OurServicesItem> ourServices;

	@SerializedName("message")
	private String message;

	@SerializedName("current_opening")
	private List<CurrentOpeningItem> currentOpening;

	@SerializedName("status")
	private String status;

	public void setSlider(List<SliderItem> slider){
		this.slider = slider;
	}

	public List<SliderItem> getSlider(){
		return slider;
	}

	public void setAboutCompany(AboutCompany aboutCompany){
		this.aboutCompany = aboutCompany;
	}

	public AboutCompany getAboutCompany(){
		return aboutCompany;
	}

	public void setOurServices(List<OurServicesItem> ourServices){
		this.ourServices = ourServices;
	}

	public List<OurServicesItem> getOurServices(){
		return ourServices;
	}

	public void setMessage(String message){
		this.message = message;
	}

	public String getMessage(){
		return message;
	}

	public void setCurrentOpening(List<CurrentOpeningItem> currentOpening){
		this.currentOpening = currentOpening;
	}

	public List<CurrentOpeningItem> getCurrentOpening(){
		return currentOpening;
	}

	public void setStatus(String status){
		this.status = status;
	}

	public String getStatus(){
		return status;
	}
}