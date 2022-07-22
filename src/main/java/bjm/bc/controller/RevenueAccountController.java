package bjm.bc.controller;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import bjm.bc.dto.RevenueAccountDto;
import bjm.bc.service.RevenueAccountService;

@Controller
public class RevenueAccountController {
	
	private static Logger LOGGER = Logger.getLogger(RevenueAccountController.class.getName());
	
	@Autowired
	private RevenueAccountService revenueAccountService;
	
	
	@RequestMapping(value = "/revenuePartyAccountManage", method = RequestMethod.GET)
	public ModelAndView addToRevenuePartyBalance(@RequestParam long accountId) {
		RevenueAccountDto revenueAccountDto = revenueAccountService.getRevenueAccountToManage(accountId);
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("revenueAccountDto", revenueAccountDto);
		modelAndView.setViewName("account/revenueAccountForm");
		return modelAndView;
	}
	
	@RequestMapping(value = "/revenuePartyAccountManage", method = RequestMethod.POST)
	public String submitRevenuePartyBalance(@Valid @ModelAttribute("revenueAccountDto") RevenueAccountDto revenueAccountDto,BindingResult result, ModelMap modelMap) {
		if (result.hasErrors()) {
			StringBuilder sb= new StringBuilder();
			for (ObjectError err: result.getAllErrors()) {
				LOGGER.log(Level.SEVERE, err.getDefaultMessage());
				sb.append(err.getDefaultMessage()).append(',');
			}
			modelMap.addAttribute("error", revenueAccountDto);
			modelMap.addAttribute("revenueAccountDto", revenueAccountDto);
		}else {
			boolean status = revenueAccountService.addToBalanceRevenueAccount(revenueAccountDto.getId(), revenueAccountDto.getBalanceToAdd());
			if (status) {
				revenueAccountDto.setBalance(revenueAccountDto.getBalance()+revenueAccountDto.getBalanceToAdd());
				revenueAccountDto.setBalanceToAdd(0);
				//revenueAccountDto.setInfo("Balance was successfully udated");
				modelMap.addAttribute("info","Balance was successfully udated");
				modelMap.addAttribute("revenueAccountDto", revenueAccountDto);
			}
		}
		return "account/revenueAccountForm";
	}
}
