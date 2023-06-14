package com.venu.library.ServiceTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.venu.library.Model.Book;
import com.venu.library.Repository.BookRepository;
import com.venu.library.Service.BookServiceImpl;

@ExtendWith(MockitoExtension.class)
public class BookServiceTest {

	@Mock
	private BookRepository mockBookRepo;

	private BookServiceImpl bookService;

	@BeforeEach
	void setUp() throws Exception {
		bookService = new BookServiceImpl(mockBookRepo);
	}

	@Test
	void saveBook_test() {
		Book book = new Book();
		bookService.registerBook(book);
		verify(mockBookRepo, times(1)).save(book);
	}

	@Test
	void findByGenreAndTitle_test() {
		Book book = new Book();
		String testGenre = "Adventure";
		String testTitle = "Indiana Jones";
		when(mockBookRepo.findByGenreAndTitle(testGenre, testTitle)).thenReturn(book);
		Book testBook = bookService.findByGenreAndTitle(testGenre, testTitle);
		verify(mockBookRepo).findByGenreAndTitle(testGenre, testTitle);
		assertEquals(book, testBook);
	}

	@Test
	void deleteBookById_test() {
		long id = 1L;
		bookService.deleteBookById(id);
		verify(mockBookRepo).deleteById(id);
	}

	@Test
	void findBookById_test() {
		Book book = new Book();
		when(mockBookRepo.findById(1L)).thenReturn(Optional.of(book));
		Book testBook = bookService.findBookById(1L);
		verify(mockBookRepo, times(1)).findById((1L));
		assertEquals(book, testBook);
	}

}
