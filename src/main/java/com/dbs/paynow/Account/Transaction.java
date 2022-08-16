package com.dbs.paynow.Account;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "transaction")
public class Transaction {
    @Id
     int UTR_NUMBER ;
    String DOT;
    boolean STATUS;
    String SENDER_ACNUM;
    String BENEFICIARY_ACNUM;
    String amount;
    String code;
	public Transaction() {
	    // TODO Auto-generated constructor stub
	}
	public Transaction(int uTR_NUMBER, String dOT, boolean sTATUS, String sENDER_ACNUM, String bENEFICIARY_ACNUM, String amount, String code) {
	    super();
	    UTR_NUMBER = uTR_NUMBER;
	    DOT = dOT;
	    STATUS = sTATUS;
	    SENDER_ACNUM = sENDER_ACNUM;
	    BENEFICIARY_ACNUM = bENEFICIARY_ACNUM;
	    this.code = code;
	    this.amount = amount;
	}
	public int getUTR_NUMBER() {
	    return UTR_NUMBER;
	}
	public void setUTR_NUMBER(int uTR_NUMBER) {
	    UTR_NUMBER = uTR_NUMBER;
	}
	public String getDOT() {
	    return DOT;
	}
	public void setDOT(String dOT) {
	    DOT = dOT;
	}
	public boolean isSTATUS() {
	    return STATUS;
	}
	public void setSTATUS(boolean sTATUS) {
	    STATUS = sTATUS;
	}
	public String getSENDER_ACNUM() {
	    return SENDER_ACNUM;
	}
	public void setSENDER_ACNUM(String sENDER_ACNUM) {
	    SENDER_ACNUM = sENDER_ACNUM;
	}
	public String getBENEFICIARY_ACNUM() {
	    return BENEFICIARY_ACNUM;
	}
	public void setBENEFICIARY_ACNUM(String bENEFICIARY_ACNUM) {
	    BENEFICIARY_ACNUM = bENEFICIARY_ACNUM;
	}
	public String getAmount() {
	    return amount;
	}
	public void setAmount(String amount) {
    this.amount = amount;
}
	
	public String getCode() {
		return code;
	}
	
	public void setCode(String code) {
		this.code = code;
	}

}