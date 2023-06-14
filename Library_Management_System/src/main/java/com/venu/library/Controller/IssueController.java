package com.venu.library.Controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.venu.library.Model.Book;
import com.venu.library.Model.Issuedetails;
import com.venu.library.Model.Returndetails;
import com.venu.library.Model.User;
import com.venu.library.Service.BookService;
import com.venu.library.Service.IssueService;
import com.venu.library.Service.ReturnService;
import com.venu.library.Service.UserService;

@Controller
public class IssueController {

	@Autowired
	private IssueService issueService;

	@Autowired
	private BookService bookService;

	@Autowired
	private UserService userService;

	@Autowired
	private ReturnService returnService;

	@GetMapping("/viewIssueBook")
	public ModelAndView issueDashboard(HttpSession session, Model model) {

		User user = (User) session.getAttribute("user");
		System.out.println(user);
		List<Issuedetails> allDetails = issueService.getAllDetailsById(user.getId());
		System.out.println(allDetails);
		// System.out.println(allDetails1);
		model.addAttribute("user", user);
		return new ModelAndView("viewIssueBook", "issuedetails", allDetails);
	}

	@PostMapping("/issueBook")
	public String submitBookDetails() {
		return "issueNewBook";
	}

	@PostMapping("/save")
	public String getBooks(@RequestParam String genre, @RequestParam String bookName, @RequestParam String issueDate,
			@RequestParam int period, @RequestParam String userName) {
		// In your actual implementation, you would fetch the books from the database
		// based on the selected genre and return the list of books.
		Book detail = bookService.findByGenreAndTitle(genre, bookName);
		User userDetail = userService.findByName(userName);
		Issuedetails issue = new Issuedetails(issueDate, "Pending", period, userDetail, detail);
		issueService.saveIssue(issue);
		// List<Book> books = (List<Book>) bookService.getAllBooks(genre);
		// List<Issuedetails> allDetails=issueService.getAllDetails(userDetail.getId());
		return "redirect:/viewIssueBook";
	}

	@GetMapping("/adminIssueDetails")
	public ModelAndView showIssuedBooks(HttpSession session, Model model) {
		User user = (User) session.getAttribute("user");
		List<Issuedetails> allDetails = issueService.getAllDetails();
		model.addAttribute("user", user);
		return new ModelAndView("adminIssueBooks", "issuedetails", allDetails);
	}

	@GetMapping("/issueMessage/{id}")
	public String adminIssueApproval(@PathVariable("id") long id, Model model, HttpSession session) {
		Issuedetails detail = issueService.findById(id);
		detail.setApprovals("Approved");
		issueService.saveIssue(detail);
		// List<Issuedetails> allDetails1 = issueService.getAllDetailsById(id);
		// System.out.println(allDetails1);
		// for (Issuedetails a : allDetails1) {
		if (detail.getApprovals().equals("Approved")) {
			String issuedDate = detail.getIssuedate();
			int period = detail.getPeriod();
			SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
			Calendar cal = Calendar.getInstance();
			try {
				cal.setTime(sdf.parse(issuedDate));
			} catch (ParseException e) {
				e.printStackTrace();
			}
			cal.add(Calendar.DAY_OF_MONTH, period);
			String dueDate = sdf.format(cal.getTime());
			Returndetails returndetail = new Returndetails("-", dueDate, "Pending", 0, detail);
			// System.out.println(returndetail);
			returnService.saveReturnDetails(returndetail);

			int copies = detail.getBook().getCopies();
			copies = copies - 1;
			Book book = bookService.findBookById(detail.getBook().getId());
			book.setCopies(copies);
			bookService.registerBook(book);

		}

		model.addAttribute("issuedetails", detail);
		return "redirect:/adminIssueDetails";
	}

}
