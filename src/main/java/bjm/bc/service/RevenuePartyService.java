package bjm.bc.service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import bjm.bc.model.RevenueParty;
import bjm.bc.repository.RevenuePartyRepository;
import bjm.bc.util.HashGenerator;
import bjm.bc.util.PasswordUtil;

@Service
public class RevenuePartyService {
	
	private static Logger LOGGER = Logger.getLogger(RevenuePartyService.class.getName());
	
	@Autowired
	private RevenuePartyRepository revenuePartyRepository;
	
	public RevenueParty createRevenueParty(RevenueParty revenueParty) {
		String accountHash = HashGenerator.generateHash(revenueParty.getName().concat(revenueParty.getEmail()).concat(revenueParty.getOwnerAdhaarNumber()));
		revenueParty.setAccountHash(accountHash);
		LocalDate ld = LocalDate.parse(revenueParty.getMemorableDateStr(), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
		revenueParty.setMemorableDate(ld);
		String securePassword = PasswordUtil.generateSecurePassword(revenueParty.getPassword(), revenueParty.getEmail());
		revenueParty.setPassword(securePassword);
		revenueParty = revenuePartyRepository.save(revenueParty);
		LOGGER.info("Revenue Party created with ID: "+revenueParty.getId());
		return revenueParty;
	}

	public RevenueParty findRevenuePartyById(Long partyId) {
		Optional<RevenueParty> rpO = revenuePartyRepository.findById(partyId);
		if (rpO.isPresent()) {
			return rpO.get();
		}else {
			return null; //Though this should never happen.
		}
	}

	public RevenueParty updateRevenueParty(RevenueParty revenueParty) {
		String accountHash = HashGenerator.generateHash(revenueParty.getName().concat(revenueParty.getEmail()).concat(revenueParty.getOwnerAdhaarNumber()));
		revenueParty.setAccountHash(accountHash);
		LocalDate ld = LocalDate.parse(revenueParty.getMemorableDateStr(), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
		revenueParty.setMemorableDate(ld);
		String securePassword = PasswordUtil.generateSecurePassword(revenueParty.getPassword(), revenueParty.getEmail());
		revenueParty.setPassword(securePassword);
		revenueParty = revenuePartyRepository.save(revenueParty);
		LOGGER.info("Revenue Party updated with ID: "+revenueParty.getId());
		return revenueParty;
		
	}

}
