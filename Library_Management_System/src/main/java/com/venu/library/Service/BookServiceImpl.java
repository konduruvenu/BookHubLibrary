package com.venu.library.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.venu.library.Model.Book;
import com.venu.library.Repository.BookRepository;

@Service
public class BookServiceImpl implements BookService {

   @Autowired
	private BookRepository bookRepository;
	
   public BookServiceImpl(BookRepository bookRepository) {
		super();
		this.bookRepository = bookRepository;
	}
	

	public List<Book> getAllBooks(String genre) {
		
		List<Book> bookDetails=bookRepository.findAllByGenre(genre);
		
		return bookDetails;
	}


	public Book findByGenreAndTitle(String genre, String bookName) {
		Book detail=bookRepository.findByGenreAndTitle(genre,bookName);
		return detail;
	}


	public void registerBook(Book book) {
		bookRepository.save(book);
		
	}


	
	public void deleteBookById(long id) {
		bookRepository.deleteById(id);
		
	}


	@Override
	public Book findBookById(long id) {
		
		Optional<Book> book=bookRepository.findById(id);
		return book.get();
	}


	@Override
	public List<Book> getAllDetails() {
		List<Book> books=bookRepository.findAll();
		return books;
	}


	

}
