package com.dbs.paynow.Account;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "receiver")
public class Receiver {

	@Id
	private String BIC; 
	private String bank; 
	private String code;
	
	
	public String getBIC() {
		return BIC;
	}
	public void setBIC(String bIC) {
		BIC = bIC;
	}
	public String getBank() {
		return bank;
	}
	public void setBank(String bank) {
		this.bank = bank;
	}
	public String getCode() {
		return code;
	} 
	
	
}
