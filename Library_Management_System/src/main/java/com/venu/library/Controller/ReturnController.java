package com.venu.library.Controller;
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
import com.venu.library.Model.Issuedetails;
import com.venu.library.Model.Returndetails;
import com.venu.library.Model.User;
import com.venu.library.Service.BookService;
import com.venu.library.Service.IssueService;
import com.venu.library.Service.ReturnService;

@Controller
public class ReturnController {

	@Autowired
	private ReturnService returnService;

	@Autowired
	private IssueService issueService;
	
	@Autowired
	private BookService bookService;

	@GetMapping("/viewReturnBook")
	public String getReturnList(HttpSession session, Model model) {
		User user = (User) session.getAttribute("user");
		System.out.println(user);
		List<Returndetails> allDetails=returnService.getAllDetails(user.getId());
		model.addAttribute("user", user);
		model.addAttribute("returndetails", allDetails);
		return "viewReturnBook";
	}

	@RequestMapping("/adminReturnDetails")
	public ModelAndView getAdminReturnList(HttpSession session, Model model) {
		User user = (User) session.getAttribute("user");
		List<Returndetails> allDetails = returnService.getAllDetails();
		model.addAttribute("user", user);
		return new ModelAndView("adminReturnBooks", "returndetails", allDetails);

	}
	
	@GetMapping("/adminEditReturns/{id}")
	public String viewEditReturnRecord(@PathVariable("id") long id,Model model) {
		Issuedetails detail=issueService.findById(id);
		Returndetails details=returnService.findByIssueDetails(detail);
		//System.out.println(details);
		int copies=detail.getBook().getCopies();
		copies+=1;
		Book book = bookService.findBookById(detail.getBook().getId());
		book.setCopies(copies);
		bookService.registerBook(book);
		model.addAttribute("returndetails", details);
		return "adminEditReturnRecords";
	}
	
	@PostMapping("/adminEditReturns")
	public ModelAndView viewReturnRecords(@ModelAttribute("returndetails") Returndetails returndetails,HttpSession session,Model model) {
		User user = (User) session.getAttribute("user");
		returnService.saveReturnDetails(returndetails);
		model.addAttribute("user", user);
		List<Returndetails> allDetails = returnService.getAllDetails();
		return new ModelAndView("adminReturnBooks","returndetails",allDetails);
	}
	
	

}
