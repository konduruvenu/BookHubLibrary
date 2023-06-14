package com.venu.library.Service;

import java.util.List;

import com.venu.library.Model.Book;

public interface BookService {
	
	List<Book> getAllBooks(String genre);
	
	Book findByGenreAndTitle(String genre,String bookName);
	
	void registerBook(Book book);
	
	void deleteBookById(long id);
	
	Book findBookById(long id);
	
	List<Book> getAllDetails();

}
