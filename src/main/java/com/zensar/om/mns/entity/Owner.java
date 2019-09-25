package com.zensar.om.mns.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Owner {

	@Id
	private String ownerData;

	public String getOwnerData() {
		return ownerData;
	}

	public void setOwnerData(String ownerData) {
		this.ownerData = ownerData;
	}
	
}
