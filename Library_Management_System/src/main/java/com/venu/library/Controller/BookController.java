package com.venu.library.Controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.venu.library.Model.Book;
import com.venu.library.Model.User;
import com.venu.library.Service.BookService;

@Controller
class BookController {
	
	@Autowired
	private BookService bookService;
	
	@RequestMapping("/viewAllCategory")
	public String showAllCategory(HttpSession session,Model model) {
		User user=(User)session.getAttribute("user");
		if (user.getEmail().contains("@admin.com")) {
			model.addAttribute("user", user);
			return "admincategoryList";
		} else {
			model.addAttribute("user", user);
			return "categoryList";
		}
	}
	
	@GetMapping("/allActionList")
	public ModelAndView showAllActionBooks(HttpSession session,Model model) {
		User user =(User)session.getAttribute("user");
		model.addAttribute("user", user);
		List<Book> detail=bookService.getAllBooks("Adventure");
		if(user.getEmail().contains("admin.com")) {
			return new ModelAndView("adminviewcategoryList","bookdetails",detail);
		}
		else {
			return new ModelAndView("viewCategoryList","bookdetails",detail);
		}
	}
	
	@GetMapping("/allComicsList")
	public ModelAndView showAllComicsBooks(HttpSession session,Model model) {
		User user =(User)session.getAttribute("user");
		model.addAttribute("user", user);
		List<Book> detail=bookService.getAllBooks("Comics");
		if(user.getEmail().contains("admin.com")) {
			return new ModelAndView("adminviewcategoryList","bookdetails",detail);
		}
		else {
			return new ModelAndView("viewCategoryList","bookdetails",detail);
		}
	}
	@GetMapping("/allHistoryList")
	public ModelAndView showAllHistoryBooks(HttpSession session,Model model) {
		User user =(User)session.getAttribute("user");
		model.addAttribute("user", user);
		List<Book> detail=bookService.getAllBooks("History");
		if(user.getEmail().contains("admin.com")) {
			return new ModelAndView("adminviewcategoryList","bookdetails",detail);
		}
		else {
			return new ModelAndView("viewCategoryList","bookdetails",detail);
		}
	}
	@GetMapping("/allJournalsList")
	public ModelAndView showAllJournalsBooks(HttpSession session,Model model) {
		User user =(User)session.getAttribute("user");
		model.addAttribute("user", user);
		List<Book> detail=bookService.getAllBooks("Technology");
		if(user.getEmail().contains("admin.com")) {
			return new ModelAndView("adminviewcategoryList","bookdetails",detail);
		}
		else {
			return new ModelAndView("viewCategoryList","bookdetails",detail);
		}
	}
	@GetMapping("/allFantasyList")
	public ModelAndView showAllFantasyBooks(HttpSession session,Model model) {
		User user =(User)session.getAttribute("user");
		model.addAttribute("user", user);
		List<Book> detail=bookService.getAllBooks("Fantasy & Science Fiction");
		if(user.getEmail().contains("admin.com")) {
			return new ModelAndView("adminviewcategoryList","bookdetails",detail);
		}
		else {
			return new ModelAndView("viewCategoryList","bookdetails",detail);
		}
	}
	@GetMapping("/allNonFictionList")
	public ModelAndView showAllNonFictionBooks(HttpSession session,Model model) {
		User user =(User)session.getAttribute("user");
		model.addAttribute("user", user);
		List<Book> detail=bookService.getAllBooks("Non-Fiction");
		if(user.getEmail().contains("admin.com")) {
			return new ModelAndView("adminviewcategoryList","bookdetails",detail);
		}
		else {
			return new ModelAndView("viewCategoryList","bookdetails",detail);
		}
	}
	@GetMapping("/allLiteratureList")
	public ModelAndView showAllLiteratureBooks(HttpSession session,Model model) {
		User user =(User)session.getAttribute("user");
		model.addAttribute("user", user);
		List<Book> detail=bookService.getAllBooks("Literature & Fiction");
		if(user.getEmail().contains("admin.com")) {
			return new ModelAndView("adminviewcategoryList","bookdetails",detail);
		}
		else {
			return new ModelAndView("viewCategoryList","bookdetails",detail);
		}
	}
	@GetMapping("/allBusinessList")
	public ModelAndView showAllBusinessBooks(HttpSession session,Model model) {
		User user =(User)session.getAttribute("user");
		model.addAttribute("user", user);
		List<Book> detail=bookService.getAllBooks("Business & Economics");
		if(user.getEmail().contains("admin.com")) {
			return new ModelAndView("adminviewcategoryList","bookdetails",detail);
		}
		else {
			return new ModelAndView("viewCategoryList","bookdetails",detail);
		}
	}
	@GetMapping("/allMysteryList")
	public ModelAndView showAllMysteryBooks(HttpSession session,Model model) {
		User user =(User)session.getAttribute("user");
		model.addAttribute("user", user);
		List<Book> detail=bookService.getAllBooks("Crime,Thriller & Mystery");
		if(user.getEmail().contains("admin.com")) {
			return new ModelAndView("adminviewcategoryList","bookdetails",detail);
		}
		else {
			return new ModelAndView("viewCategoryList","bookdetails",detail);
		}
	}
	@GetMapping("/allSportsList")
	public ModelAndView showAllSportsBooks(HttpSession session,Model model) {
		User user =(User)session.getAttribute("user");
		model.addAttribute("user", user);
		List<Book> detail=bookService.getAllBooks("Sports");
		if(user.getEmail().contains("admin.com")) {
			return new ModelAndView("adminviewcategoryList","bookdetails",detail);
		}
		else {
			return new ModelAndView("viewCategoryList","bookdetails",detail);
		}
	}
	
	@GetMapping("/addNewBook")
	public String adminAddNewBook(Model model) {
		model.addAttribute("book", new Book());
		return "adminNewBook";
	}
    
	@PostMapping("/saveNewBook")
	public String saveNewBook(@ModelAttribute("book") Book book,Model model,HttpSession session) {
		bookService.registerBook(book);
		User user=(User)session.getAttribute("user");
		if (user.getEmail().contains("@admin.com")) {
			model.addAttribute("user", user);
			return "admincategoryList";
		} else {
			model.addAttribute("user", user);
			return "categoryList";
		}
	}
	
	@GetMapping("/deleteBook/{id}")
	public ModelAndView deleteBookByadmin(@PathVariable("id") long id,Model model,HttpSession session) {
		Book book=bookService.findBookById(id);
		//System.out.println(book);
		//String genre=book.getGenre();
		//System.out.println(genre);
		bookService.deleteBookById(id);
		model.addAttribute("book", book);
		//return "adminDeleteBookMessage";
	
		//List<Book> detail=bookService.getAllBooks(genre);
		User user=(User)session.getAttribute("user");
		//model.addAttribute("user", user);
			//model.addAttribute("bookdetails", detail);
		return new ModelAndView("redirect:/viewAllCategory","user",user);
		
	}
	
	@GetMapping("/editChanges/{id}")
	public String editBookByadmin(@PathVariable("id") long id,Model model,HttpSession session) {
		//bookService.registerBook(book);
		Book book=bookService.findBookById(id);
		model.addAttribute("book", book);
		return "adminEditBook";
	}
	@PostMapping("/saveEditBook")
	public String saveEditBook(@ModelAttribute("book") Book book,Model model,HttpSession session) {
		bookService.registerBook(book);
		String genre=book.getGenre();
		List<Book> detail=bookService.getAllBooks(genre);
		User user=(User)session.getAttribute("user");
		if (user.getEmail().contains("@admin.com")) {
			model.addAttribute("user", user);
			model.addAttribute("bookdetails", detail);
			return "adminviewcategoryList";
		} else {
			model.addAttribute("user", user);
			return "categoryList";
		}
	}
	
	@GetMapping("/viewReserveBook")
	public String showReserveBook(HttpSession session,Model model) {
		User user=(User)session.getAttribute("user");
		List<Book> book=bookService.getAllDetails();
		List<Book> nonAvailable=new ArrayList<>();
		for(Book i:book) {
			if(i.getCopies()==0) {
				nonAvailable.add(i);
			}
		}
		model.addAttribute("user", user);
		model.addAttribute("bookdetails", nonAvailable);
		return "viewReservedBook";
	}
	
	
	
}
