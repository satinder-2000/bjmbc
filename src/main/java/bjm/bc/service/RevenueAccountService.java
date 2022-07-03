package bjm.bc.service;

import java.util.Optional;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bjm.bc.model.RevenueAccount;
import bjm.bc.repository.RevenueAccountRepository;

@Service
public class RevenueAccountService {
	
	private static final Logger LOGGER = Logger.getLogger(RevenueAccountService.class.getName());

	@Autowired
	private RevenueAccountRepository revenueAccountRepository;
	
	public boolean addToBalanceRevenueAccount(long acctId, double amount) {
		
		Optional<RevenueAccount> revenueAccountOp = revenueAccountRepository.findById(acctId);
		if (revenueAccountOp.isPresent()) {
			RevenueAccount revenueAccount = revenueAccountOp.get();
			revenueAccount.setBalance(revenueAccount.getBalance()+amount);
			LOGGER.info(String.format("Revenue Account Balance updated Acct %1$s Balance %2$s}", revenueAccount.getId(), revenueAccount.getBalance()));
			revenueAccountRepository.save(revenueAccount);
			return true;
		}else {
			LOGGER.severe(String.format("No Revenue Account found with ID: %s", acctId));
			return false;
		}
		
	}

}
