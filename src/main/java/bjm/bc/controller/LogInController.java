package bjm.bc.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import bjm.bc.dto.ExpenseAccountDto;
import bjm.bc.dto.RevenueAccountDto;
import bjm.bc.model.User;
import bjm.bc.model.UserType;
import bjm.bc.service.ExpensePartyService;
import bjm.bc.service.RevenuePartyService;
import bjm.bc.service.UserService;
import bjm.bc.util.PasswordUtil;

@Controller
public class LogInController {
	
	@Autowired
	UserService userService;
	
	@Autowired
	private RevenuePartyService revenuePartyService;
	
	@Autowired
	private ExpensePartyService expensePartyService;
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String loginForm(Model model) {
		model.addAttribute("user", new User());
		return "login";

	}
	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ModelAndView loginFormSubmit(@ModelAttribute(name = "user") User user) {
		ModelAndView modelAndView = new ModelAndView();
		User userdB=userService.getByEmail(user.getEmail());
		String passwordReq=PasswordUtil.generateSecurePassword(user.getPassword(), user.getEmail());
		String passwordDb=userdB.getPassword();
		if (!passwordReq.equals(passwordDb)) {
			modelAndView.setViewName("login");
			user = userService.increaseFailedLoginnAttempt(userdB);
			if (user.getFailedAttempts() == UserService.MAX_FAILED_ATTEMPTS ) {// no more attempts left and the account has been locked.
				modelAndView.addObject("error","Login attempts(3) expired. Account is now locked" );
			}else {
				int attemptsLeft =  UserService.MAX_FAILED_ATTEMPTS - user.getFailedAttempts();
				modelAndView.addObject("error","Login failed. Attempts left: "+attemptsLeft);
			}
			return modelAndView;
			
		}else {
			UserType userType =userdB.getUserType();
			switch(userType) {
			case REVENUE_PARTY:
				userdB.setFailedAttempts(0);
				userdB.setAccountLocked(false);
				userService.updateUser(userdB);
				//Get Revenue Party Accounts
				List<RevenueAccountDto> revenueAccountsDtos = revenuePartyService.findRevenueAccountsOfRevenueParty(userdB.getEmail());
				modelAndView.setViewName("home/RevenuePartyHome");
				modelAndView.addObject("revenuePartyAccounts", revenueAccountsDtos);
				modelAndView.addObject("revenuePartyEmail", userdB.getEmail());
				break;
			case EXPENSE_PARTY:
				userdB.setFailedAttempts(0);
				userdB.setAccountLocked(false);
				userService.updateUser(userdB);
				//Get Revenue Party Accounts
				List<ExpenseAccountDto> expenseAccountDtos = expensePartyService.findExpenseAccountsOfExpenseParty(userdB.getEmail());
				modelAndView.setViewName("home/ExpensePartyHome");
				modelAndView.addObject("expensePartyAccounts", expenseAccountDtos);
				modelAndView.addObject("expensePartyEmail", userdB.getEmail());
				break;
			}
			return modelAndView;
		}
		

	}
	
	
	

}
