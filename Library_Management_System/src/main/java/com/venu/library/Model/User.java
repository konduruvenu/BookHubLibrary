package com.venu.library.Model;

import java.util.List;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String name;
	private String email;
	private String password;
	private String contact;
	private String address;

	//@OneToMany(fetch=FetchType.LAZY,mappedBy="user")
	//private List<Book> books;
	
	@OneToMany(fetch=FetchType.LAZY,mappedBy="user")
	private List<Issuedetails> issuedBooks;

	public User() {
		super();
	}

	public User(long id, String name, String email, String password, String contact, String address) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.password = password;
		this.contact = contact;
		this.address = address;
	}

	

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	/*public List<Book> getBooks() {
		return books;
	}

	public void setBooks(List<Book> books) {
		this.books = books;
	}*/

	public List<Issuedetails> getIssuedBooks() {
		return issuedBooks;
	}

	public void setIssuedBooks(List<Issuedetails> issuedBooks) {
		this.issuedBooks = issuedBooks;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", email=" + email + ", password=" + password + ", contact="
				+ contact + ", address=" + address + "]";
	}

}
