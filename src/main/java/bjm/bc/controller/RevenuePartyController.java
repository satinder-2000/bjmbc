package bjm.bc.controller;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.validation.Valid;

//import java.time.LocalDate;
//import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import bjm.bc.model.RevenueParty;
import bjm.bc.model.RevenueType;
import bjm.bc.service.RevenuePartyService;
import bjm.bc.vo.RevenuePartyData;

@Controller
public class RevenuePartyController {

	private static Logger LOGGER = Logger.getLogger(RevenuePartyController.class.getName());
	
	@Autowired
	private RevenuePartyService revenuePartyService;

	@RequestMapping(value = "/revenuePartyRegister", method = RequestMethod.GET)
	public String revenuePartyRegisterForm(@ModelAttribute("revenuePartyData") RevenuePartyData revenuePartyData) {
		return "revenuePartyRegister";
	}

	@RequestMapping(value = "/revenuePartyRegister", method = RequestMethod.POST)
	public String revenuePartySubmit(@Valid @ModelAttribute("revenuePartyData") RevenuePartyData revenuePartyData, BindingResult result, RedirectAttributes ra) {
		if (result.hasErrors()) {
			for (ObjectError err: result.getAllErrors()) {
				LOGGER.log(Level.SEVERE, err.getDefaultMessage());
			}
			return "revenuePartyRegister";
		}
		RevenueParty revenueParty= new RevenueParty();
		revenueParty.setName(revenuePartyData.getName());
		revenueParty.setEmail(revenuePartyData.getEmail());
		revenueParty.setMemorableDateStr(revenuePartyData.getMemorableDateStr());
		revenueParty.setOrganisation(revenuePartyData.getOrganisation());
		revenueParty.setOwnerAdhaarNumber(revenuePartyData.getOwnerAdhaarNumber());
		revenueParty.setPassword(revenuePartyData.getPassword());
		revenueParty.setRevenueType(RevenueType.valueOf(revenuePartyData.getRevenueType()));
		revenueParty = revenuePartyService.createRevenueParty(revenueParty);
		revenuePartyData.setId(revenueParty.getId());
		ra.addFlashAttribute("revenuePartyData",revenuePartyData);
		return "revenuePartyConfirm";
	}
	
	//@GetMapping("/revenuePartyAmend")
	@RequestMapping(value = "/revenuePartyAmend", method = RequestMethod.GET)
	public String revenuePartyAmend(Model model, @RequestParam Long partyId) {
		RevenueParty revenueParty = revenuePartyService.findRevenuePartyById(partyId);
		RevenuePartyData revenuePartyData= new RevenuePartyData();
		revenuePartyData.setId(revenueParty.getId());
		revenuePartyData.setEmail(revenueParty.getEmail());
		revenuePartyData.setName(revenueParty.getName());
		revenuePartyData.setMemorableDateStr(revenueParty.getMemorableDate().toString());
		revenuePartyData.setOrganisation(revenueParty.getOrganisation());
		revenuePartyData.setOwnerAdhaarNumber(revenueParty.getOwnerAdhaarNumber());
		revenuePartyData.setRevenueType(revenueParty.getRevenueType().toString());
		model.addAttribute("revenuePartyData", revenuePartyData);
		return "revenuePartyAmend";
	}
	
	//@PostMapping("/revenuePartyAmend")
	@RequestMapping(value = "/revenuePartyAmend", method = RequestMethod.POST)
	public String revenuePartyAmendSubmit(@Valid @ModelAttribute("revenuePartyData") RevenuePartyData revenuePartyData, BindingResult result, RedirectAttributes ra) {
		RevenueParty revenuePartydB = revenuePartyService.findRevenuePartyById(revenuePartyData.getId());
		revenuePartydB.setName(revenuePartyData.getName());
		revenuePartydB.setEmail(revenuePartyData.getEmail());
		revenuePartydB.setOwnerAdhaarNumber(revenuePartyData.getOwnerAdhaarNumber());
		revenuePartydB.setOrganisation(revenuePartyData.getOrganisation());
		revenuePartydB.setRevenueType(RevenueType.valueOf(revenuePartyData.getRevenueType()));
		revenuePartydB.setMemorableDateStr(revenuePartyData.getMemorableDateStr());
		revenuePartydB.setPassword(revenuePartyData.getPassword());
		revenuePartyService.updateRevenueParty(revenuePartydB);
		ra.addFlashAttribute("revenuePartyData",revenuePartyData);
		return "revenuePartyConfirm";
	}
	
	@RequestMapping(value = "/revenuePartyFinish", method = RequestMethod.GET)
	public String revenuePartyFinish(Model model, @RequestParam Long partyId) {
		RevenueParty revenueParty = revenuePartyService.findRevenuePartyById(partyId);
		model.addAttribute("revenueParty", revenueParty);
		return "revenuePartyFinish";
	}

}
