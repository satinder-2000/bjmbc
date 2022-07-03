package bjm.bc.service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.logging.Logger;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import bjm.bc.dto.ExpenseAccountDto;
import bjm.bc.dto.ExpensePartyDto;
import bjm.bc.exception.UserRegisteredAlreadyException;
import bjm.bc.model.ExpenseAccount;
import bjm.bc.model.ExpenseParty;
import bjm.bc.model.ExpenseType;
import bjm.bc.model.User;
import bjm.bc.model.UserType;
import bjm.bc.repository.ExpensePartyRepository;
import bjm.bc.util.HashGenerator;
import bjm.bc.util.PasswordUtil;

@Service
public class ExpensePartyService {
	
	private static final Logger LOGGER = Logger.getLogger(ExpensePartyService.class.getName());
	
	@Autowired
	private ExpensePartyRepository expensePartyRepository;
	
	@Autowired
	private UserService userService; 
	
	@Autowired
	private JavaMailSender javaMailSender;
	
	public ExpensePartyDto createExpenseParty(@Valid ExpensePartyDto expensePartyDto) throws UserRegisteredAlreadyException {
		ExpenseParty expenseParty = new ExpenseParty();
		expenseParty.setName(expensePartyDto.getName());
		expenseParty.setEmail(expensePartyDto.getEmail());
		expenseParty.setOrganisation(expensePartyDto.getOrganisation());
		expenseParty.setOwnerAdhaarNumber(expensePartyDto.getOwnerAdhaarNumber());
		LocalDate ld = LocalDate.parse(expensePartyDto.getMemorableDateStr(), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
		String securePassword = PasswordUtil.generateSecurePassword(expensePartyDto.getPassword(), expensePartyDto.getEmail());
		expenseParty.setMemorableDate(ld);
		String partyHash=HashGenerator.generateHash(expenseParty.getName().concat(expenseParty.getEmail()).concat(expenseParty.getOwnerAdhaarNumber()));
		expenseParty.setPassword(securePassword);
		expenseParty.setPartyHash(partyHash);
		//Set Expense Accounts now
		ExpenseType[] expenseTypes = expensePartyDto.getExpenseTypes();
		for (ExpenseType et : expenseTypes) {
			ExpenseAccount ea = new ExpenseAccount();
			ea.setExpenseType(et);
			expenseParty.getExpenseAccounts().add(ea);
			ea.setExpenseParty(expenseParty);
		}
		expenseParty = expensePartyRepository.save(expenseParty);
		expensePartyDto.setId(expenseParty.getId());
		LOGGER.info("Expense Party created with ID: "+expenseParty.getId());
		
		User user = new User();
		user.setAccountLocked(false);
		user.setEmail(expenseParty.getEmail());
		user.setFailedAttempts(0);
		user.setPassword(securePassword);
		user.setUserType(UserType.EXPENSE_PARTY);
		user =userService.createUser(user);
		LOGGER.info("User created with ID: "+user.getId());
		
		SimpleMailMessage message =new SimpleMailMessage();
		message.setFrom("admin@bjmpc.in");
		message.setTo(expenseParty.getEmail());
		message.setSubject("Successful Registeration");
		message.setText("Congratulations, your registeration was successful!!");
		javaMailSender.send(message);
		return expensePartyDto;
	}
	
	public ExpensePartyDto findExpensePartyById(Long partyId) {
		Optional<ExpenseParty> epO = expensePartyRepository.findById(partyId);
		if (epO.isPresent()) {
			ExpenseParty expenseParty=epO.get();
			ExpensePartyDto expensePartyDto= new ExpensePartyDto();
			expensePartyDto.setId(expenseParty.getId());
			expensePartyDto.setName(expenseParty.getName());
			expensePartyDto.setEmail(expenseParty.getEmail());
			expensePartyDto.setOrganisation(expenseParty.getOrganisation());
			expensePartyDto.setOwnerAdhaarNumber(expenseParty.getOwnerAdhaarNumber());
			expensePartyDto.setPartyHash(expenseParty.getPartyHash());
			return expensePartyDto;
		}else {
			return null; //Though this should never happen.
		}
	}

	public ExpensePartyDto updateExpenseParty(@Valid ExpensePartyDto expensePartyDto) {
		LocalDate ld = LocalDate.parse(expensePartyDto.getMemorableDateStr(), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
		String securePassword = PasswordUtil.generateSecurePassword(expensePartyDto.getPassword(), expensePartyDto.getEmail());
		String partyHash=HashGenerator.generateHash(expensePartyDto.getName().concat(expensePartyDto.getEmail()).concat(expensePartyDto.getOwnerAdhaarNumber()));
		Optional<ExpenseParty> epO = expensePartyRepository.findById(expensePartyDto.getId());
		if (epO.isPresent()) {
			ExpenseParty expenseParty=epO.get();
			expenseParty.setName(expensePartyDto.getName());
			expenseParty.setEmail(expensePartyDto.getEmail());
			expenseParty.setOrganisation(expensePartyDto.getOrganisation());
			expenseParty.setMemorableDate(ld);
			expenseParty.setPassword(securePassword);
			expenseParty.setPartyHash(partyHash);
			//Set Expense Accounts now
			ExpenseType[] expenseTypes = expensePartyDto.getExpenseTypes();
			for (ExpenseType et : expenseTypes) {
				ExpenseAccount ea = new ExpenseAccount();
				ea.setExpenseType(et);
				expenseParty.getExpenseAccounts().add(ea);
				ea.setExpenseParty(expenseParty);
			}
			expenseParty = expensePartyRepository.save(expenseParty);
			LOGGER.info("Expense Party updated with ID: "+expenseParty.getId());
			User user = userService.getByEmail(expenseParty.getEmail());
			user.setPassword(securePassword);
			user = userService.updateUser(user);
			LOGGER.info("User updated with ID: "+user.getId());
			
		}
		return expensePartyDto;
		
	}
	
	public List<ExpenseAccountDto> findExpenseAccountsOfExpenseParty(String email) {
		List<ExpenseAccountDto> expAcctsDto = new ArrayList<>();
		ExpenseParty ep= expensePartyRepository.findByEmail(email);
		Set<ExpenseAccount> expenseAccounts = ep.getExpenseAccounts();
		for (ExpenseAccount ea: expenseAccounts) {
			ExpenseAccountDto eaDto =new ExpenseAccountDto();
			eaDto.setId(ea.getId());
			eaDto.setExpenseType(ea.getExpenseType().toString());
			eaDto.setBalance(ea.getBalance());
			expAcctsDto.add(eaDto);
		}
		return expAcctsDto;
	}
}
