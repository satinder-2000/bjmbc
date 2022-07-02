package bjm.bc.service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import bjm.bc.dto.RevenueAccountDto;
import bjm.bc.dto.RevenuePartyDto;
import bjm.bc.model.RevenueAccount;
import bjm.bc.model.RevenueParty;
import bjm.bc.model.RevenueType;
import bjm.bc.model.User;
import bjm.bc.model.UserType;
import bjm.bc.repository.RevenuePartyRepository;
import bjm.bc.util.HashGenerator;
import bjm.bc.util.PasswordUtil;


@Service
public class RevenuePartyService {
	
	private static Logger LOGGER = Logger.getLogger(RevenuePartyService.class.getName());
	
	@Autowired
	private RevenuePartyRepository revenuePartyRepository;
	
	@Autowired
	private UserService userService; 
	
	@Autowired
	private JavaMailSender javaMailSender;
	
	public RevenuePartyDto createRevenueParty(@Valid RevenuePartyDto revenuePartyDto) {
		RevenueParty revenueParty = new RevenueParty();
		revenueParty.setName(revenuePartyDto.getName());
		revenueParty.setEmail(revenuePartyDto.getEmail());
		revenueParty.setOrganisation(revenuePartyDto.getOrganisation());
		revenueParty.setOwnerAdhaarNumber(revenuePartyDto.getOwnerAdhaarNumber());
		LocalDate ld = LocalDate.parse(revenuePartyDto.getMemorableDateStr(), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
		String securePassword = PasswordUtil.generateSecurePassword(revenuePartyDto.getPassword(), revenuePartyDto.getEmail());
		revenueParty.setMemorableDate(ld);
		String partyHash=HashGenerator.generateHash(revenueParty.getName().concat(revenueParty.getEmail()).concat(revenueParty.getOwnerAdhaarNumber()));
		revenueParty.setPassword(securePassword);
		revenueParty.setPartyHash(partyHash);
		//Set Revenue Accounts now
		RevenueType[] revenueTypes = revenuePartyDto.getRevenueTypes();
		for (RevenueType rt : revenueTypes) {
			RevenueAccount ra = new RevenueAccount();
			ra.setRevenueType(rt);
			revenueParty.getRevenueAccounts().add(ra);
			ra.setRevenueParty(revenueParty);
		}
		revenueParty = revenuePartyRepository.save(revenueParty);
		revenuePartyDto.setId(revenueParty.getId());
		LOGGER.info("Revenue Party created with ID: "+revenueParty.getId());
		
		User user = new User();
		user.setAccountLocked(false);
		user.setEmail(revenueParty.getEmail());
		user.setFailedAttempts(0);
		user.setPassword(securePassword);
		user.setUserType(UserType.REVENUE_PARTY);
		user =userService.createUser(user);
		LOGGER.info("User created with ID: "+user.getId());
		
		SimpleMailMessage message =new SimpleMailMessage();
		message.setFrom("admin@bjmpc.in");
		message.setTo(revenueParty.getEmail());
		message.setSubject("Successful Registeration");
		message.setText("Congratulations, your registeration was successful!!");
		javaMailSender.send(message);
		return revenuePartyDto;
	}

	public RevenuePartyDto findRevenuePartyById(Long partyId) {
		Optional<RevenueParty> rpO = revenuePartyRepository.findById(partyId);
		if (rpO.isPresent()) {
			RevenueParty revenueParty = rpO.get();
			RevenuePartyDto revenuePartyDto = new RevenuePartyDto();
			revenuePartyDto.setId(revenueParty.getId());
			revenuePartyDto.setName(revenueParty.getName());
			revenuePartyDto.setEmail(revenueParty.getEmail());
			revenuePartyDto.setOrganisation(revenueParty.getOrganisation());
			revenuePartyDto.setOwnerAdhaarNumber(revenueParty.getOwnerAdhaarNumber());
			revenuePartyDto.setMemorableDateStr(revenueParty.getMemorableDate().toString());
			revenuePartyDto.setPartyHash(revenueParty.getPartyHash());
			RevenueType[] revenueTypes = new RevenueType[revenueParty.getRevenueAccounts().size()];
			int i=0;
			for (RevenueAccount ra: revenueParty.getRevenueAccounts()) {
				revenueTypes[i] =  ra.getRevenueType();
				i++;
			}
			revenuePartyDto.setRevenueTypes(revenueTypes);
			return revenuePartyDto;
		}else {
			return null; //Though this should never happen.
		}
	}
	
	public RevenuePartyDto updateRevenueParty(@Valid RevenuePartyDto revenuePartyDto) {
		LocalDate ld = LocalDate.parse(revenuePartyDto.getMemorableDateStr(), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
		String securePassword = PasswordUtil.generateSecurePassword(revenuePartyDto.getPassword(), revenuePartyDto.getEmail());
		String partyHash=HashGenerator.generateHash(revenuePartyDto.getName().concat(revenuePartyDto.getEmail()).concat(revenuePartyDto.getOwnerAdhaarNumber()));
		Optional<RevenueParty> rpO = revenuePartyRepository.findById(revenuePartyDto.getId());
		if (rpO.isPresent()) {
			RevenueParty revenueParty=rpO.get();
			revenueParty.setName(revenuePartyDto.getName());
			revenueParty.setEmail(revenuePartyDto.getEmail());
			revenueParty.setOrganisation(revenuePartyDto.getOrganisation());
			revenueParty.setOwnerAdhaarNumber(revenuePartyDto.getOwnerAdhaarNumber());
			revenueParty.setMemorableDate(ld);
			revenueParty.setPassword(securePassword);
			revenueParty.setPartyHash(partyHash);
			revenueParty = revenuePartyRepository.save(revenueParty);
			LOGGER.info("Revenue Party updated with ID: "+revenueParty.getId());
			User user = userService.getByEmail(revenueParty.getEmail());
			user.setPassword(securePassword);
			user = userService.updateUser(user);
			LOGGER.info("User updated with ID: "+user.getId());
		}
		return revenuePartyDto;
		
	}
	
	public List<RevenueAccountDto> findRevenueAccountsOfRevenueParty(Long partyId) {
		List<RevenueAccountDto> revAcctsDto = new ArrayList<>();
		Optional<RevenueParty> rpO = revenuePartyRepository.findById(partyId);
		if (rpO.isPresent()) {
			RevenueParty revenueParty = rpO.get();
			for (RevenueAccount ra: revenueParty.getRevenueAccounts()) {
				RevenueAccountDto aDto= new RevenueAccountDto();
				aDto.setId(ra.getId());
				aDto.setName(ra.getName());
				aDto.setRevenueType(ra.getRevenueType().toString());
				revAcctsDto.add(aDto);
				
			}
			return revAcctsDto;
		}else {
			return null; //Though this should never happen.
		}
	}
}
