package com.zensar.om.mns.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class SmeName {

	@Id
	private String smeNameData;

	public String getSmeNameData() {
		return smeNameData;
	}

	public void setSmeNameData(String smeNameData) {
		this.smeNameData = smeNameData;
	}
	
}
