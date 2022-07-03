package bjm.bc.controller;

import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestParam;

import bjm.bc.service.RevenueAccountService;

@Controller
public class RevenueAccountController {
	
	private static Logger LOGGER = Logger.getLogger(RevenuePartyController.class.getName());
	
	@Autowired
	private RevenueAccountService revenueAccountService;
	
	public String addToRevenuePartyBalance(@RequestParam long accountId, @RequestParam double amount) {
		boolean status = revenueAccountService.addToBalanceRevenueAccount(accountId, amount);
		if (!status) {
			LOGGER.severe("Failed to update the Account Balance");
		}
		return "home/RevenuePartyHome";
	}

}
