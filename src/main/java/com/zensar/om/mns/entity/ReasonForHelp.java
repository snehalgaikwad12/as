package com.zensar.om.mns.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class ReasonForHelp {

	@Id
	private String reasonForHelpData;

	public String getReasonForHelpData() {
		return reasonForHelpData;
	}

	public void setReasonForHelpData(String reasonForHelpData) {
		this.reasonForHelpData = reasonForHelpData;
	}
	
	
}
