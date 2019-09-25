
package com.zensar.om.mns.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class SubFunction {

	@Id
	private String subFunctionData;
	private String functionalArea;

	public String getFunctionalArea() {
		return functionalArea;
	}

	public void setFunctionalArea(String functionalArea) {
		this.functionalArea = functionalArea;
	}

	public String getSubFunctionData() {
		return subFunctionData;
	}

	public void setSubFunctionData(String subFunctionData) {
		this.subFunctionData = subFunctionData;
	}

	@Override
	public String toString() {
		return "SubFunction [subFunctionData=" + subFunctionData + ", functionalArea=" + functionalArea + "]";
	}
	
	
}
