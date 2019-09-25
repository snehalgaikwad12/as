package com.zensar.om.mns.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class FunctionalArea {

	@Id
	private String functionalAreaData;

	public String getFunctionalAreaData() {
		return functionalAreaData;
	}

	public void setFunctionalAreaData(String functionalAreaData) {
		this.functionalAreaData = functionalAreaData;
	}
	
	
}
