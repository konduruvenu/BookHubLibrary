package com.venu.library.Model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
public class Returndetails {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	private String returndate;
	private String duedate;
	private String status;
	private int fine;
	
	@OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="issueid", referencedColumnName="id")
	private Issuedetails issuedetails;
	
    public Returndetails() {
		super();
	}

	public Returndetails(String returndate, String duedate, String status, int fine,
			Issuedetails issuedetails) {
		super();
		//this.id = id;
		this.returndate = returndate;
		this.duedate = duedate;
		this.status = status;
		this.fine = fine;
		this.issuedetails = issuedetails;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getReturndate() {
		return returndate;
	}

	public void setReturndate(String returndate) {
		this.returndate = returndate;
	}

	public String getDuedate() {
		return duedate;
	}

	public void setDuedate(String duedate) {
		this.duedate = duedate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public int getFine() {
		return fine;
	}

	public void setFine(int fine) {
		this.fine = fine;
	}

	public Issuedetails getIssuedetails() {
		return issuedetails;
	}

	public void setIssuedetails(Issuedetails issuedetails) {
		this.issuedetails = issuedetails;
	}

	@Override
	public String toString() {
		return "Returndetails [returndate=" + returndate + ", duedate=" + duedate + ", status=" + status + ", fine="
				+ fine + ", issuedetails=" + issuedetails + "]";
	}
	
	
	
	

}
