package com.zensar.om.mns.entity;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

import org.springframework.stereotype.Component;


@Entity
@Table(name = "history_table")
public class HistoryTable {
	
	String username;
	@Lob
	String ticketInfoJson;
	
	String remark;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	int id;
	
	public HistoryTable() {
		super();
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getTicketInfoJson() {
		return ticketInfoJson;
	}

	public void setTicketInfoJson(String ticketInfoJson) {
		this.ticketInfoJson = ticketInfoJson;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public HistoryTable(String username, String ticketInfoJson, String remark, int id) {
		super();
		this.username = username;
		this.ticketInfoJson = ticketInfoJson;
		this.remark = remark;
		this.id = id;
	}

	@Override
	public String toString() {
		return "HistoryTable [username=" + username + ", ticketInfoJson=" + ticketInfoJson + ", remark=" + remark
				+ ", id=" + id + "]";
	}
	
	
	
	

}
