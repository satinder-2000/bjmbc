package bjm.bc.controller;

import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import bjm.bc.model.User;
import bjm.bc.service.UserService;
import bjm.bc.util.PasswordUtil;

@Controller
public class LogInController {
	
	private static final Logger LOGGER = Logger.getLogger(LogInController.class.getName());
	
	@Autowired
	UserService userService;
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String loginForm(Model model) {
		model.addAttribute("user", new User());
		return "login";

	}
	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String loginFormSubmit(@ModelAttribute(name = "user") User user, Model model) {
		User userdB=userService.getByEmail(user.getEmail(), user.getPassword());
		String passwordReq=PasswordUtil.generateSecurePassword(user.getPassword(), user.getEmail());
		String passwordDb=userdB.getPassword();
		if (!passwordReq.equals(passwordDb)) {
			user = userService.increaseFailedLoginnAttempt(userdB);
			if (user.getFailedAttempts() == UserService.MAX_FAILED_ATTEMPTS ) {// no more attempts left and the account has been locked.
				model.addAttribute("error", "Login attempts(3) expired. Account is now locked");
			}else {
				int attemptsLeft =  UserService.MAX_FAILED_ATTEMPTS - user.getFailedAttempts();
				model.addAttribute("error", "Login failed. Attempts left: "+attemptsLeft);
			}
			return "login";
		}else {
			return "loginSuccess";
		}
		

	}

}