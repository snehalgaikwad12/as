package com.zensar.om.mns.entity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.regex.Pattern;

import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.stereotype.Component;

import com.zensar.om.mns.exception.DateFormatInvalidException;

@Entity
@Table(name = "ticket_info")
public class TicketInfo extends GenerateId {

	private String topsNumber;
	private String problemDesc;
	private String recvdDate;
	private String pickedDate;
	private String closedDate;
	private String Status;
	private String clasification;
	private String functionalArea;
	private String subFunction;
	private String owner;
	private String typeOfTicket;
	private String smeHelp;
	private String smeName;
	private String reasonForHelp;
	private String priority;
	private String resolution;
	private String complexity;
	private String rcCategory;
	private String rootCause;
	private String permFixApp;
	private String remarks;
	private String actualTimeTaken;

	public String getActualTimeTaken() {
		return actualTimeTaken;
	}

	public void setActualTimeTaken(String actualTimeTaken) {
		this.actualTimeTaken = actualTimeTaken;
	}

	public TicketInfo(String topsNumber, String problemDesc, String recvdDate, String pickedDate, String closedDate,
			String status, String clasification, String functionalArea, String subFunction, String owner,
			String typeOfTicket, String smeHelp, String smeName, String reasonForHelp, String priority,
			String resolution, String complexity, String rcCategory, String rootCause, String permFixApp,
			String remarks, String actualTimeTaken, long sequenceNo) {
		super();
		this.topsNumber = topsNumber;
		this.problemDesc = problemDesc;
		this.recvdDate = recvdDate;
		this.pickedDate = pickedDate;
		this.closedDate = closedDate;
		Status = status;
		this.clasification = clasification;
		this.functionalArea = functionalArea;
		this.subFunction = subFunction;
		this.owner = owner;
		this.typeOfTicket = typeOfTicket;
		this.smeHelp = smeHelp;
		this.smeName = smeName;
		this.reasonForHelp = reasonForHelp;
		this.priority = priority;
		this.resolution = resolution;
		this.complexity = complexity;
		this.rcCategory = rcCategory;
		this.rootCause = rootCause;
		this.permFixApp = permFixApp;
		this.remarks = remarks;
		this.actualTimeTaken = actualTimeTaken;
		this.sequenceNo = sequenceNo;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long sequenceNo;

	public TicketInfo() {

	}

	public TicketInfo(String topsNumber, String owner, String problemDesc, String recvdDate, String pickedDate,
			String closedDate, String status, String clasification, String functionalArea, String subFunction,
			String typeOfTicket, String smeHelp, String smeName, String reasonForHelp, String rcCategory,
			String rootCause, String permFixApp, String remarks, String priority, String resolution, String complexity,
			long sequenceNo) {
		super();
		this.topsNumber = topsNumber;
		this.owner = owner;
		this.problemDesc = problemDesc;
		this.recvdDate = recvdDate;
		this.pickedDate = pickedDate;
		this.closedDate = closedDate;
		Status = status;
		this.clasification = clasification;
		this.functionalArea = functionalArea;
		this.subFunction = subFunction;
		this.typeOfTicket = typeOfTicket;
		this.smeHelp = smeHelp;
		this.smeName = smeName;
		this.reasonForHelp = reasonForHelp;
		this.rcCategory = rcCategory;
		this.rootCause = rootCause;
		this.permFixApp = permFixApp;
		this.remarks = remarks;
		this.priority = priority;
		this.resolution = resolution;
		this.complexity = complexity;
		this.sequenceNo = sequenceNo;
	}

	public TicketInfo(long sequenceNo, String topsNumber, String problemDesc, String recvdDate, String pickedDate,
			String closedDate, String status, String clasification, String functionalArea, String subFunction,
			String owner, String typeOfTicket, String smeHelp, String smeName, String reasonForHelp, String resolution,
			String priority, String complexity, String rcCategory, String rootCause, String permFixApp,
			String remarks) {
		super();
		this.topsNumber = topsNumber;
		this.owner = owner;
		this.problemDesc = problemDesc;
		this.recvdDate = recvdDate;
		this.pickedDate = pickedDate;
		this.closedDate = closedDate;
		Status = status;
		this.clasification = clasification;
		this.functionalArea = functionalArea;
		this.subFunction = subFunction;
		this.typeOfTicket = typeOfTicket;
		this.smeHelp = smeHelp;
		this.smeName = smeName;
		this.reasonForHelp = reasonForHelp;
		this.rcCategory = rcCategory;
		this.rootCause = rootCause;
		this.permFixApp = permFixApp;
		this.remarks = remarks;
		this.priority = priority;
		this.resolution = resolution;
		this.complexity = complexity;
		this.sequenceNo = sequenceNo;
	}

	public long getSequenceNo() {
		return sequenceNo;
	}

	public String getTopsNumber() {
		return topsNumber;
	}

	public void setTopsNumber(String topsNumber) {
		this.topsNumber = topsNumber;
	}

	public String getOwner() {
		System.out.println("o "+owner);
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

	public String getProblemDesc() {
		System.out.println("PD "+problemDesc);
		return problemDesc;
	}

	public void setProblemDesc(String problemDesc) {
		this.problemDesc = problemDesc;
	}

	public String getRecvdDate() {

		return recvdDate;
	}

	public void setRecvdDate(String recvdDate) {
		try {
			String ddmmyyyy = "^(3[01]|[12][0-9]|0[1-9])-(1[0-2]|0[1-9])-[0-9]{4}$";
			Pattern ddmmyyyyPattern = Pattern.compile(ddmmyyyy);
			if (recvdDate != "") {
				if (ddmmyyyyPattern.matcher(recvdDate).matches()) {
					SimpleDateFormat sdf1 = new SimpleDateFormat("dd-MM-yyyy");
					SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
					this.recvdDate = sdf2.format(sdf1.parse(recvdDate));

				} else {
					SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
					SimpleDateFormat sdf2 = new SimpleDateFormat("dd-MM-yyyy");
					this.recvdDate = sdf2.format(sdf1.parse(recvdDate));
				}
			} else {
				this.recvdDate = "";

			}

		} catch (Exception e) {
			throw new DateFormatInvalidException("Not a valid date format ");
		}
	}

	public String getPickedDate() {
		System.out.println("PDate "+pickedDate);
		return pickedDate;
	}

	public void setPickedDate(String pickedDate)  {
		try {
			String ddmmyyyy = "^(3[01]|[12][0-9]|0[1-9])-(1[0-2]|0[1-9])-[0-9]{4}$";

			Pattern ddmmyyyyPattern = Pattern.compile(ddmmyyyy);
			if (pickedDate != "") {
				if (ddmmyyyyPattern.matcher(pickedDate).matches()) {
					SimpleDateFormat sdf1 = new SimpleDateFormat("dd-MM-yyyy");
					SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
					this.pickedDate = sdf2.format(sdf1.parse(pickedDate));

				} else {
					SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
					SimpleDateFormat sdf2 = new SimpleDateFormat("dd-MM-yyyy");
					this.pickedDate = sdf2.format(sdf1.parse(pickedDate));
				}
			} else {
				this.pickedDate = "";

			}
		} catch (Exception e) {
			throw new DateFormatInvalidException("Not a valid date format ");
		}
	}

	public String getClosedDate() {
		return closedDate;
	}

	public void setClosedDate(String closedDate)  {
		try {
			String ddmmyyyy = "^(3[01]|[12][0-9]|0[1-9])-(1[0-2]|0[1-9])-[0-9]{4}$";

			Pattern ddmmyyyyPattern = Pattern.compile(ddmmyyyy);
			if (closedDate != "") {
				if (ddmmyyyyPattern.matcher(closedDate).matches()) {
					SimpleDateFormat sdf1 = new SimpleDateFormat("dd-MM-yyyy");
					SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
					this.closedDate = sdf2.format(sdf1.parse(closedDate));

				} else {
					SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
					SimpleDateFormat sdf2 = new SimpleDateFormat("dd-MM-yyyy");
					this.closedDate = sdf2.format(sdf1.parse(closedDate));
				}
			} else {
				this.closedDate = "";

			}
		} catch (Exception e) {
			throw new DateFormatInvalidException("Not a valid date format ");
		}
	}

	public String getStatus() {
		return Status;
	}

	public void setStatus(String status) {
		Status = status;
	}

	public String getClasification() {
		return clasification;
	}

	public void setClasification(String clasification) {
		this.clasification = clasification;
	}

	public String getFunctionalArea() {
		return functionalArea;
	}

	public void setFunctionalArea(String functionalArea) {
		this.functionalArea = functionalArea;
	}

	public String getSubFunction() {
		return subFunction;
	}

	public void setSubFunction(String subFunction) {
		this.subFunction = subFunction;
	}

	public String getTypeOfTicket() {
		return typeOfTicket;
	}

	public void setTypeOfTicket(String typeOfTicket) {
		this.typeOfTicket = typeOfTicket;
	}

	public String getSmeHelp() {
		return smeHelp;
	}

	public void setSmeHelp(String smeHelp) {
		this.smeHelp = smeHelp;
	}

	public String getSmeName() {
		return smeName;
	}

	public void setSmeName(String smeName) {
		this.smeName = smeName;
	}

	public String getReasonForHelp() {
		return reasonForHelp;
	}

	public void setReasonForHelp(String reasonForHelp) {
		this.reasonForHelp = reasonForHelp;
	}

	public String getRcCategory() {
		return rcCategory;
	}

	public void setRcCategory(String rcCategory) {
		this.rcCategory = rcCategory;
	}

	public String getRootCause() {
		return rootCause;
	}

	public void setRootCause(String rootCause) {
		this.rootCause = rootCause;
	}

	public String getPermFixApp() {
		return permFixApp;
	}

	public void setPermFixApp(String permFixApp) {
		this.permFixApp = permFixApp;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getPriority() {
		return priority;
	}

	public void setPriority(String priority) {
		this.priority = priority;
	}

	public String getResolution() {
		return resolution;
	}

	public void setResolution(String resolution) {
		this.resolution = resolution;
	}

	public String getComplexity() {
		return complexity;
	}

	public void setComplexity(String complexity) {
		this.complexity = complexity;
	}

	public void setSequenceNo(long sequenceNo) {
		this.sequenceNo = sequenceNo;
	}

	@Override
	public String toString() {
		return "TicketInfo [topsNumber=" + topsNumber + ", problemDesc=" + problemDesc + ", recvdDate=" + recvdDate
				+ ", pickedDate=" + pickedDate + ", closedDate=" + closedDate + ", Status=" + Status
				+ ", clasification=" + clasification + ", functionalArea=" + functionalArea + ", subFunction="
				+ subFunction + ", owner=" + owner + ", typeOfTicket=" + typeOfTicket + ", smeHelp=" + smeHelp
				+ ", smeName=" + smeName + ", reasonForHelp=" + reasonForHelp + ", priority=" + priority
				+ ", resolution=" + resolution + ", complexity=" + complexity + ", rcCategory=" + rcCategory
				+ ", rootCause=" + rootCause + ", permFixApp=" + permFixApp + ", remarks=" + remarks
				+ ", actualTimeTaken=" + actualTimeTaken + ", sequenceNo=" + sequenceNo + "]";
	}

	
}
