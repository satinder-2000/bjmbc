package bjm.bc.controller;

//import java.time.LocalDate;
//import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import bjm.bc.model.RevenueParty;
import bjm.bc.model.RevenueType;
import bjm.bc.service.RevenuePartyService;

@Controller
public class RevenuePartyController {

	//private static Logger LOGGER = Logger.getLogger(RevenuePartyController.class.getName());
	
	@Autowired
	private RevenuePartyService revenuePartyService;

	@RequestMapping(value = "/revenuePartyRegister", method = RequestMethod.GET)
	public String revenuePartyRegisterForm(Model model) {
		model.addAttribute("revenueParty", new RevenueParty());
		return "revenuePartyRegister";

	}

	@RequestMapping(value = "/revenuePartyRegister", method = RequestMethod.POST)
	public String revenuePartySubmit(@RequestParam String name, @RequestParam String email, @RequestParam String ownerAdhaarNumber,
			@RequestParam String organisation, @RequestParam String revenueType, @RequestParam String memorableDateStr,
			@RequestParam String password, @RequestParam String passwordConfirm,
			Model model) {
		RevenueParty revenueParty= new RevenueParty();
		revenueParty.setName(name);
		revenueParty.setEmail(email);
		revenueParty.setOwnerAdhaarNumber(ownerAdhaarNumber);
		revenueParty.setOrganisation(organisation);
		revenueParty.setRevenueType(RevenueType.valueOf(revenueType));
		revenueParty.setMemorableDateStr(memorableDateStr);
		revenueParty.setPassword(password);
		revenueParty.setPasswordConfirm(passwordConfirm);
		revenueParty = revenuePartyService.createRevenueParty(revenueParty);
		model.addAttribute("revenueParty", revenueParty);
		return "revenuePartyConfirm";
	}
	
	//@GetMapping("/revenuePartyAmend")
	@RequestMapping(value = "/revenuePartyAmend", method = RequestMethod.GET)
	public String revenuePartyAmend(Model model, @RequestParam Long partyId) {
		RevenueParty revenueParty = revenuePartyService.findRevenuePartyById(partyId);
		model.addAttribute("revenueParty", revenueParty);
		return "revenuePartyAmend";
	}
	
	//@PostMapping("/revenuePartyAmend")
	@RequestMapping(value = "/revenuePartyAmend", method = RequestMethod.POST)
	public String revenuePartyAmendSubmit(@RequestParam String id, @RequestParam String name, @RequestParam String email, @RequestParam String ownerAdhaarNumber,
			@RequestParam String organisation, @RequestParam String revenueType, @RequestParam String memorableDateStr,
			@RequestParam String password, @RequestParam String passwordConfirm,
			Model model) {
		RevenueParty revenuePartydB = revenuePartyService.findRevenuePartyById(Long.valueOf(id));
		revenuePartydB.setName(name);
		revenuePartydB.setEmail(email);
		revenuePartydB.setOwnerAdhaarNumber(ownerAdhaarNumber);
		revenuePartydB.setOrganisation(organisation);
		revenuePartydB.setRevenueType(RevenueType.valueOf(revenueType));
		revenuePartydB.setMemorableDateStr(memorableDateStr);
		revenuePartydB.setPassword(password);
		revenuePartydB.setPasswordConfirm(passwordConfirm);
		
		revenuePartyService.updateRevenueParty(revenuePartydB);
		model.addAttribute("revenueParty", revenuePartydB);
		return "revenuePartyConfirm";
	}
	
	@RequestMapping(value = "/revenuePartyFinish", method = RequestMethod.GET)
	public String revenuePartyFinish(Model model, @RequestParam Long partyId) {
		RevenueParty revenueParty = revenuePartyService.findRevenuePartyById(partyId);
		model.addAttribute("revenueParty", revenueParty);
		return "revenuePartyFinish";
	}

}
