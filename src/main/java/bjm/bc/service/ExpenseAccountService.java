package bjm.bc.service;

import java.util.Optional;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bjm.bc.dto.ExpenseAccountDto;
import bjm.bc.dto.ExpenseAccountDto;
import bjm.bc.model.ExpenseAccount;
import bjm.bc.model.ExpenseAccount;
import bjm.bc.repository.ExpenseAccountRepository;

@Service
public class ExpenseAccountService {
	
	private static final Logger LOGGER = Logger.getLogger(ExpenseAccountService.class.getName());

	@Autowired
	private ExpenseAccountRepository expenseAccountRepository;
	
	public boolean addToBalanceExpenseAccount(long acctId, double amount) {
		
		Optional<ExpenseAccount> expenseAccountOp = expenseAccountRepository.findById(acctId);
		if (expenseAccountOp.isPresent()) {
			ExpenseAccount expenseAccount = expenseAccountOp.get();
			expenseAccount.setBalance(expenseAccount.getBalance()+amount);
			LOGGER.info(String.format("Expense Account Balance updated Acct %1$s Balance %2$s}", expenseAccount.getId(), expenseAccount.getBalance()));
			expenseAccountRepository.save(expenseAccount);
			return true;
		}else {
			LOGGER.severe(String.format("No Expense Account found with ID: %s", acctId));
			return false;
		}
		
	}
	
	public boolean withdrawFromBalanceExpenseAccount(long acctId, double amount) {
		
		Optional<ExpenseAccount> expenseAccountOp = expenseAccountRepository.findById(acctId);
		if (expenseAccountOp.isPresent()) {
			ExpenseAccount expenseAccount = expenseAccountOp.get();
			//Balance in account should be > amount
			if (expenseAccount.getBalance()<amount) {//cannot withdraw the amount
				LOGGER.severe(String.format("Expense Account lacks funds ID: %s", acctId));
				return false;
			}
			expenseAccount.setBalance(expenseAccount.getBalance()-amount);
			LOGGER.info(String.format("Expense Account Balance updated Acct %1$s Balance %2$s}", expenseAccount.getId(), expenseAccount.getBalance()));
			expenseAccountRepository.save(expenseAccount);
			return true;
		}else {
			LOGGER.severe(String.format("No Expense Account found with ID: %s", acctId));
			return false;
		}
		
	}

	public ExpenseAccountDto getExpenseAccountToManage(long accountId) {
		Optional<ExpenseAccount> expenseAccountOp = expenseAccountRepository.findById(accountId);
		//ModelAndView modelAndView = null;
		if (expenseAccountOp.isPresent()) {
			ExpenseAccount expenseAccount = expenseAccountOp.get();
			ExpenseAccountDto expenseAccountDto = new ExpenseAccountDto();
			//modelAndView = new ModelAndView();
			expenseAccountDto.setId(accountId);
			expenseAccountDto.setExpenseType(expenseAccount.getExpenseType().value);
			expenseAccountDto.setBalance(expenseAccount.getBalance());
			//modelAndView.addObject("expenseAccount",expenseAccountDto);
			//modelAndView.setViewName("ExpensePartyAccountManage");
			return expenseAccountDto;
		}else {
			return null;
		}
	}
	
	

}
