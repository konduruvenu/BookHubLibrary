package com.venu.library.Controller;

import java.util.List;
import java.util.Iterator;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.venu.library.Model.Issuedetails;
import com.venu.library.Model.Returndetails;
import com.venu.library.Model.User;
import com.venu.library.Service.IssueService;
import com.venu.library.Service.ReturnService;
import com.venu.library.Service.UserService;

@Controller
public class UserController {

	@Autowired
	private UserService userService;

	@Autowired
	private IssueService issueService;

	@Autowired
	private ReturnService returnService;

	@GetMapping("/homepage")
	public String homepage() {
		return "homepage";
	}

	@GetMapping("/register")
	public String showRegistrationForm(Model model) {
		model.addAttribute("user", new User());
		return "register";
	}

	@PostMapping("/saveRegister")
	public String registerUser(@ModelAttribute User user, Model model) {
		userService.registerUser(user);
		return "homepage";
	}

	@GetMapping("/login")
	public String showLoginForm() {
		return "login";
	}

	@PostMapping("/validatelogin")
	public String processLoginForm(HttpSession session, @RequestParam String email, @RequestParam String password,
			Model model) {
		User user = userService.loginUser(email, password);
		if (user != null) {
			session.setAttribute("user", user);
			if (user.getEmail().contains("@admin.com")) {

				List<Issuedetails> issuedBook = issueService.getAllDetails();
				int totalIssue = 0;
				int totalNonIssue = 0;
				for (Issuedetails i : issuedBook) {
					if (i.getApprovals().equals("Approved")) {
						totalIssue += 1;
					} else {
						totalNonIssue += 1;
					}
				}
				List<Returndetails> returnedBook = returnService.getAllDetails();
				int totalReturn = 0;
				int totalDue = 0;
				for (Returndetails r : returnedBook) {
					if (r.getStatus().equals("Returned")) {
						totalReturn += 1;
					} else {
						totalDue += 1;
					}
				}
				model.addAttribute("user", user);
				model.addAttribute("issueCount", totalIssue);
				model.addAttribute("returnCount", totalReturn);
				model.addAttribute("dueCount", totalDue);
				model.addAttribute("nonIssueCount", totalNonIssue);
				return "adminDashboard";
			} else {
				model.addAttribute("user", user);

				long id = user.getId();
				List<Issuedetails> issuedBook = issueService.getAllDetailsById(id);
				int totalIssue = 0;
				int totalNonIssue = 0;
				for (Issuedetails i : issuedBook) {
					if (i.getApprovals().equals("Approved")) {
						totalIssue += 1;
					} else {
						totalNonIssue += 1;
					}
				}
				List<Returndetails> returnedBook = returnService.getAllDetails(id);
				int totalReturn = 0;
				int totalDue = 0;
				for (Returndetails r : returnedBook) {
					if (r.getStatus().equals("Returned")) {
						totalReturn += 1;
					} else {
						totalDue += 1;
					}
				}
				model.addAttribute("user", user);
				model.addAttribute("issueCount", totalIssue);
				model.addAttribute("returnCount", totalReturn);
				model.addAttribute("dueCount", totalDue);
				model.addAttribute("nonIssueCount", totalNonIssue);
				return "userDashboard";
			}
		} else {
			model.addAttribute("error", "Invalid username or password");
			return "login";
		}
	}

	@GetMapping("/returnDashboard")
	public String returnToDashboard(HttpSession session, Model model) {
		User user = (User) session.getAttribute("user");
		long id = user.getId();
		List<Issuedetails> issuedBook = issueService.getAllDetailsById(id);
		int totalIssue = 0;
		int totalNonIssue = 0;
		for (Issuedetails i : issuedBook) {
			if (i.getApprovals().equals("Approved")) {
				totalIssue += 1;
			} else {
				totalNonIssue += 1;
			}
		}
		List<Returndetails> returnedBook = returnService.getAllDetails(id);
		int totalReturn = 0;
		int totalDue = 0;
		for (Returndetails r : returnedBook) {
			if (r.getStatus().equals("Returned")) {
				totalReturn += 1;
			} else {
				totalDue += 1;
			}
		}
		model.addAttribute("issueCount", totalIssue);
		model.addAttribute("returnCount", totalReturn);
		model.addAttribute("dueCount", totalDue);
		model.addAttribute("nonIssueCount", totalNonIssue);
		model.addAttribute("user", user);
		return "userDashboard";
	}

	@GetMapping("/returnAdminDashboard")
	public String returnToDashBoard(HttpSession session, Model model) {
		User user = (User) session.getAttribute("user");
		List<Issuedetails> issuedBook = issueService.getAllDetails();
		int totalIssue = 0;
		int totalNonIssue = 0;
		for (Issuedetails i : issuedBook) {
			if (i.getApprovals().equals("Approved")) {
				totalIssue += 1;
			} else {
				totalNonIssue += 1;
			}
		}
		List<Returndetails> returnedBook = returnService.getAllDetails();
		int totalReturn = 0;
		int totalDue = 0;
		for (Returndetails r : returnedBook) {
			if (r.getStatus().equals("Returned")) {
				totalReturn += 1;
			} else {
				totalDue += 1;
			}
		}
		model.addAttribute("issueCount", totalIssue);
		model.addAttribute("returnCount", totalReturn);
		model.addAttribute("dueCount", totalDue);
		model.addAttribute("nonIssueCount", totalNonIssue);
		model.addAttribute("user", user);
		return "adminDashboard";
	}

	@RequestMapping("/userProfile")
	public String showProfile(HttpSession session, Model model) {
		User detail = (User) session.getAttribute("user");
		model.addAttribute("user", detail);
		return "editProfile";
	}

	@RequestMapping("/viewAdminProfile")
	public String showAdminProfile(HttpSession session, Model model) {
		User detail = (User) session.getAttribute("user");
		// System.out.println(detail);
		model.addAttribute("user", detail);
		return "adminProfile";
	}

	@PostMapping("/editUser")
	public String editingViewForPassenger(@ModelAttribute User user, Model model, HttpSession session) {
		userService.registerUser(user);
		User updateUser = userService.findById(user.getId());
		if (updateUser.getEmail().contains("admin.com")) {
			model.addAttribute("user", updateUser);
			session.setAttribute("user", updateUser);
			// System.out.println(user);
			return "adminProfile";

		} else {
			model.addAttribute("user", updateUser);
			session.setAttribute("user", updateUser);
			// System.out.println(user);
			return "editProfile";
		}
	}

	@PostMapping("/deleteAccountConfirmationPage")
	public String confirmationForDeleteAccount(Model model, HttpSession session) {
		User detail = (User) session.getAttribute("user");
		// System.out.println(detail);
		model.addAttribute("user", detail);
		return "deleteAccountConfirmationPage";
	}

	@GetMapping("/deleteAccount")
	public String deleteLoggedInAccount(HttpSession httpSession) {
		User user = (User) httpSession.getAttribute("user");
		userService.removeById(user.getId());
		httpSession.removeAttribute("user");
		return "homepage";
	}

	@RequestMapping("/viewAllUsers")
	public ModelAndView showUsersInAdmin(HttpSession session, Model model) {
		User detail = (User) session.getAttribute("user");
		List<User> users = userService.getAllUsers();
		model.addAttribute("user", detail);
		Iterator<User> iterator = users.iterator();
		while (iterator.hasNext()) {
			User user = iterator.next();
			if (user.getEmail().contains("admin.com")) {
				iterator.remove();
			}
		}
		return new ModelAndView("adminviewUsers", "users", users);
	}

	@PostMapping("/addNewUser")
	public String adminAddNewUser(Model model) {
		model.addAttribute("user", new User());
		return "adminNewUser";
	}

	@PostMapping("/saveNewUser")
	public String saveNewBook(@ModelAttribute("user") User user, Model model, HttpSession session) {
		userService.registerUser(user);
		User user1 = (User) session.getAttribute("user");
		List<User> users = userService.getAllUsers();
		model.addAttribute("user", user1);
		Iterator<User> iterator = users.iterator();
		while (iterator.hasNext()) {
			User user2 = iterator.next();
			if (user2.getEmail().contains("admin.com")) {
				iterator.remove();
			}
		}
		model.addAttribute("users", users);
		return "adminviewUsers";

	}

	@GetMapping("/deleteUser/{id}")
	public String deleteBookByadmin(@PathVariable("id") long id, Model model, HttpSession session) {
		// User users=userService.findById(id);
		// System.out.println(book);
		// String genre=book.getGenre();
		// System.out.println(genre);
		userService.removeById(id);
		List<User> detail = userService.getAllUsers();
		User user = (User) session.getAttribute("user");
		Iterator<User> iterator = detail.iterator();
		while (iterator.hasNext()) {
			User users = iterator.next();
			if (users.getEmail().contains("admin.com")) {
				iterator.remove();
			}
		}
		

		model.addAttribute("user", user);
		model.addAttribute("users", detail);
		return "redirect:/viewAllUsers";

	}

}
