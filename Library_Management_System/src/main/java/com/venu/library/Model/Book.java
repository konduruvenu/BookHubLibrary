package com.venu.library.Model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Book {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
	private String title;
	private String author;
	private String genre;
	private String publisher;
	private String publisherdate;
	private int copies;
   
    
    @OneToMany(fetch=FetchType.LAZY,mappedBy="book")
	private List<Issuedetails> issuedBooks;
	
    public Book() {
		super();
	}

	public Book(long id,String title, String author, String genre, String publisher, String publisherdate, int copies
			) {
		super();
		this.id = id;
		this.title = title;
		this.author = author;
		this.genre = genre;
		this.publisher = publisher;
		this.publisherdate = publisherdate;
		this.copies = copies;
	
	}

	public long getId() {
		return id;
	}

	
	public void setId(long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public String getPublisher() {
		return publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	

	

	public String getPublisherdate() {
		return publisherdate;
	}

	public void setPublisherdate(String publisherdate) {
		this.publisherdate = publisherdate;
	}

	public int getCopies() {
		return copies;
	}

	public void setCopies(int copies) {
		this.copies = copies;
	}

	
	
    
}
