package bjm.bc.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import bjm.bc.dto.RevenueAccountDto;
import bjm.bc.service.RevenuePartyService;

@Controller
public class RevenuePartyHomeController {
	
	@Autowired
	private RevenuePartyService revenuePartyService;
	
	@RequestMapping(value = "/revenuePartyAccounts", method = RequestMethod.GET)
	public ModelAndView getRevenuePartyAccounts(@RequestParam String email) {
		ModelAndView modelAndView = new ModelAndView();
		//Get Revenue Party Accounts
		List<RevenueAccountDto> revenueAccountsDtos = revenuePartyService.findRevenueAccountsOfRevenueParty(email);
		modelAndView.setViewName("home/RevenuePartyHome");
		modelAndView.addObject("revenuePartyAccounts", revenueAccountsDtos);
		return modelAndView;
		
	}

}
