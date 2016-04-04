package controller;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import bll.UserService;
import bll.model.MarketsSimUser;

@Controller
public class UserController {

	private final UserService _userService;
	
	public UserController() {
		// TODO: dependency injection
		_userService = new UserService();
	}
	@RequestMapping(value = "/user/register", method = RequestMethod.POST)
	public @ResponseBody MarketsSimUser registerNewUser(@Valid @RequestBody MarketsSimUser newUser) {
		_userService.registerNewUser(newUser);
		return newUser;
	}
	
}
