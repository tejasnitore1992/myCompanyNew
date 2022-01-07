package com.mycompanynew.home.response;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class OurServicesItem{

	@SerializedName("company_service_image")
	private String companyServiceImage;

	@SerializedName("service_list")
	private List<ServiceListItem> serviceList;

	@SerializedName("company_service_id")
	private String companyServiceId;

	@SerializedName("company_service_name")
	private String companyServiceName;

	public void setCompanyServiceImage(String companyServiceImage){
		this.companyServiceImage = companyServiceImage;
	}

	public String getCompanyServiceImage(){
		return companyServiceImage;
	}

	public void setServiceList(List<ServiceListItem> serviceList){
		this.serviceList = serviceList;
	}

	public List<ServiceListItem> getServiceList(){
		return serviceList;
	}

	public void setCompanyServiceId(String companyServiceId){
		this.companyServiceId = companyServiceId;
	}

	public String getCompanyServiceId(){
		return companyServiceId;
	}

	public void setCompanyServiceName(String companyServiceName){
		this.companyServiceName = companyServiceName;
	}

	public String getCompanyServiceName(){
		return companyServiceName;
	}
}