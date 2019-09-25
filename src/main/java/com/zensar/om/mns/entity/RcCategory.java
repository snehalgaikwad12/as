package com.zensar.om.mns.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class RcCategory {

	@Id
	private String rcCategoryData;

	public String getRcCategoryData() {
		return rcCategoryData;
	}

	public void setRcCategoryData(String rcCategoryData) {
		this.rcCategoryData = rcCategoryData;
	}
	
	
}
