package com.venu.library.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.venu.library.Model.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
	
	List<Book> findAllByGenre(String genre);
	
	Book findByGenreAndTitle(String genre,String bookName);

}
