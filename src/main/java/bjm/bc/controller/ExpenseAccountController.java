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

import bjm.bc.dto.ExpenseAccountDto;
import bjm.bc.service.ExpenseAccountService;

@Controller
public class ExpenseAccountController {
	
private static Logger LOGGER = Logger.getLogger(ExpenseAccountController.class.getName());
	
	@Autowired
	private ExpenseAccountService expenseAccountService;
	
	@RequestMapping(value = "/expensePartyAccountManage", method = RequestMethod.GET)
	public ModelAndView addToExpensePartyBalance(@RequestParam long accountId) {
		ExpenseAccountDto expenseAccountDto = expenseAccountService.getExpenseAccountToManage(accountId);
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("expenseAccountDto", expenseAccountDto);
		modelAndView.setViewName("account/expenseAccountForm");
		return modelAndView;
	}
	
	@RequestMapping(value = "/expensePartyAccountManage", method = RequestMethod.POST)
	public String submitExpensePartyBalance(@Valid @ModelAttribute("expenseAccountDto") ExpenseAccountDto expenseAccountDto,BindingResult result, ModelMap modelMap) {
		if (result.hasErrors()) {
			StringBuilder sb= new StringBuilder();
			for (ObjectError err: result.getAllErrors()) {
				LOGGER.log(Level.SEVERE, err.getDefaultMessage());
				sb.append(err.getDefaultMessage()).append(',');
			}
			modelMap.addAttribute("error", expenseAccountDto);
			modelMap.addAttribute("expenseAccountDto", expenseAccountDto);
		}else {
			boolean status = expenseAccountService.addToBalanceExpenseAccount(expenseAccountDto.getId(), expenseAccountDto.getBalanceToAdd());
			if (status) {
				expenseAccountDto.setBalance(expenseAccountDto.getBalance()+expenseAccountDto.getBalanceToAdd());
				expenseAccountDto.setBalanceToAdd(0);
				//expenseAccountDto.setInfo("Balance was successfully udated");
				modelMap.addAttribute("info","Balance was successfully udated");
				modelMap.addAttribute("expenseAccountDto", expenseAccountDto);
			}
		}
		return "account/expenseAccountForm";
	}
	
	@RequestMapping(value = "/expensePartyAccountManageWithdraw", method = RequestMethod.POST)
	public String submitWithdrawExpensePartyBalance(@Valid @ModelAttribute("expenseAccountDto") ExpenseAccountDto expenseAccountDto,BindingResult result, ModelMap modelMap) {
		if (result.hasErrors()) {
			StringBuilder sb= new StringBuilder();
			for (ObjectError err: result.getAllErrors()) {
				LOGGER.log(Level.SEVERE, err.getDefaultMessage());
				sb.append(err.getDefaultMessage()).append(',');
			}
			modelMap.addAttribute("error", expenseAccountDto);
			modelMap.addAttribute("expenseAccountDto", expenseAccountDto);
		}else {
			ExpenseAccountDto expenseAccountDtoDb=expenseAccountService.getExpenseAccountToManage(expenseAccountDto.getId());
			if (expenseAccountDtoDb.getBalance() < expenseAccountDto.getBalanceToWithdraw()) {
				modelMap.addAttribute("error", "Insufficient Balance in Account");
			}else {
				
			}
			boolean status = expenseAccountService.withdrawFromBalanceExpenseAccount(expenseAccountDtoDb.getId(),expenseAccountDto.getBalanceToWithdraw());
			if (status) {
				expenseAccountDto.setBalance(expenseAccountDto.getBalance()-expenseAccountDto.getBalanceToWithdraw());
				expenseAccountDto.setBalanceToWithdraw(0);
				modelMap.addAttribute("info","Balance was successfully udated");
				modelMap.addAttribute("expenseAccountDto", expenseAccountDto);
			}
		}
		return "account/expenseAccountForm";
	}

}
