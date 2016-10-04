package co.grandcircus.movies.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import co.grandcircus.movies.dao.UserDao;
import co.grandcircus.movies.model.Movie;
import co.grandcircus.movies.model.User;

@Controller
public class UserController {

	private static final Logger logger = LoggerFactory.getLogger(UserController.class);
	
	@Autowired
	private UserDao userDao;
	
	@RequestMapping(value = "/users", method = RequestMethod.GET)
    public String sortUsers(Model model, @RequestParam(value = "sort", required = false) String sortOrder){
        List<User> users;
        if(sortOrder != null){
			users = userDao.getAllUsersSortedBy(sortOrder);
		}else{
		users = userDao.getAllUsers();
		}
        model.addAttribute("users", users);
        logger.info("/users -> user-list.jsp");
        return "user-list";
    }
	
	
	
	@RequestMapping(value = "/users/{id}", method = RequestMethod.GET)
	public String displayUser(@PathVariable int id, Model model) {
		model.addAttribute("id", id);
		model.addAttribute("user", userDao.getUser(id));

		logger.info("GET /movies/" + id + " -> user.jsp");
		return "user";
	}
	
	@RequestMapping(value = "/users/{id}", method = RequestMethod.POST)
	public String saveUser(@PathVariable int id, User user, Model model) {
		userDao.updateUser(id, user);
		model.addAttribute("id", id);
		model.addAttribute("usesr", user);

		logger.info("POST /users/" + id + " -> user.jsp");
		return "user";
	}
	
	@RequestMapping(value = "/users/create", method = RequestMethod.GET)
	public String displayUserForm(Model model) {
		model.addAttribute("user", new User());
		logger.info("/users/create -> user-create.jsp");
		return "user-create";
	}
	
	@RequestMapping(value = "/users/create", method = RequestMethod.POST)
	public String createUser(User user, Model model) {
		userDao.addUser(user);
		model.asMap().clear();

		logger.info("POST /users/create -> redirect to /users");
		return "redirect:/users";
	}
	
	
	
}
