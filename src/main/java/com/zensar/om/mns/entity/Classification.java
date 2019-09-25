package com.zensar.om.mns.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Classification {
	@Id
	private String classificationData;

	public String getClassificationData() {
		return classificationData;
	}

	public void setClassificationData(String classificationData) {
		this.classificationData = classificationData;
	}

	@Override
	public String toString() {
		return "Classification [classificationData=" + classificationData + "]";
	}
	
	
	

}
