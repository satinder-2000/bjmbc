package bjm.bc.controller;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.validation.Valid;
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
import bjm.bc.dto.RevenuePartyDto;
import bjm.bc.service.RevenuePartyService;

@Controller
public class RevenuePartyController {

	private static Logger LOGGER = Logger.getLogger(RevenuePartyController.class.getName());
	
	@Autowired
	private RevenuePartyService revenuePartyService;

	@RequestMapping(value = "/revenuePartyRegister", method = RequestMethod.GET)
	public String revenuePartyRegisterForm(@ModelAttribute("revenuePartyDto") RevenuePartyDto revenuePartyDto) {
		return "rev/revenuePartyRegister";
	}

	@RequestMapping(value = "/revenuePartyRegister", method = RequestMethod.POST)
	public String revenuePartySubmit(@Valid @ModelAttribute("revenuePartyDto") RevenuePartyDto revenuePartyDto, BindingResult result, RedirectAttributes ra) {
		if (result.hasErrors()) {
			for (ObjectError err: result.getAllErrors()) {
				LOGGER.log(Level.SEVERE, err.getDefaultMessage());
			}
			return "rev/revenuePartyRegister";
		}
		//Trnasfer all the data to the Entity being done in the Service layer 
		revenuePartyDto = revenuePartyService.createRevenueParty(revenuePartyDto);
		
		ra.addFlashAttribute("revenuePartyDto",revenuePartyDto);
		return "rev/revenuePartyConfirm";
	}
	
	//@GetMapping("/revenuePartyAmend")
	@RequestMapping(value = "/revenuePartyAmend", method = RequestMethod.GET)
	public String revenuePartyAmend(@ModelAttribute("revenueParty") RevenuePartyDto revenuePartyDto, @RequestParam Long partyId) {
		revenuePartyDto = revenuePartyService.findRevenuePartyById(partyId);
		return "rev/revenuePartyAmend";
	}
	
	//@PostMapping("/revenuePartyAmend")
	@RequestMapping(value = "/revenuePartyAmend", method = RequestMethod.POST)
	public String revenuePartyAmendSubmit(@Valid @ModelAttribute("revenuePartyDto") RevenuePartyDto revenuePartyDto, BindingResult result, RedirectAttributes ra) {
		if (result.hasErrors()) {
			for (ObjectError err: result.getAllErrors()) {
				LOGGER.log(Level.SEVERE, err.getDefaultMessage());
			}
			return "rev/revenuePartyAmend";
		}
		revenuePartyDto = revenuePartyService.updateRevenueParty(revenuePartyDto);
		ra.addFlashAttribute("revenueParty",revenuePartyDto);
		return "rev/revenuePartyConfirm";
	}
	
	@RequestMapping(value = "/revenuePartyFinish", method = RequestMethod.GET)
	public String revenuePartyFinish(Model model, @RequestParam Long partyId) {
		RevenuePartyDto revenueParty = revenuePartyService.findRevenuePartyById(partyId);
		model.addAttribute("revenueParty", revenueParty);
		return "rev/revenuePartyFinish";
	}
	
}
