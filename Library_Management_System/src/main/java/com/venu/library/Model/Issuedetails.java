package com.venu.library.Model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Issuedetails {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String issuedate;
	private String approvals;
	private int period;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="userid",referencedColumnName="id")
	private User user;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="bookid",referencedColumnName="id")
	private Book book;

	

	public Issuedetails(long id, String issuedate, String approvals, int period, User user, Book book) {
		super();
		this.id = id;
		this.issuedate = issuedate;
		this.approvals = approvals;
		this.period = period;
		this.user = user;
		this.book = book;
	}
	

	public Issuedetails(String issuedate, String approvals, int period, User user, Book book) {
		super();
		this.issuedate = issuedate;
		this.approvals = approvals;
		this.period = period;
		this.user = user;
		this.book = book;
	}


	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getIssuedate() {
		return issuedate;
	}

	public void setIssuedate(String issuedate) {
		this.issuedate = issuedate;
	}

	public String getApprovals() {
		return approvals;
	}

	public void setApprovals(String approvals) {
		this.approvals = approvals;
	}

	public int getPeriod() {
		return period;
	}

	public void setPeriod(int period) {
		this.period = period;
	}

	/*public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getBookName() {
		return bookName;
	}

	public void setBookName(String bookName) {
		this.bookName = bookName;
	}*/

	public User getUser() {
		return user;
	}

	

	public Issuedetails() {
		super();
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}

	
}
