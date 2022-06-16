package bjm.bc.controller;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import bjm.bc.dto.ExpensePartyDto;
import bjm.bc.exception.UserRegisteredAlreadyException;
import bjm.bc.model.User;
import bjm.bc.service.ExpensePartyService;
import bjm.bc.service.UserService;

@Controller
public class ExpensePartyController {
	
private static Logger LOGGER = Logger.getLogger(ExpensePartyController.class.getName());
	
	@Autowired
	private ExpensePartyService expensePartyService;
	
	@Autowired
	private UserService userService;

	@RequestMapping(value = "/expensePartyRegister", method = RequestMethod.GET)
	public String expensePartyRegisterForm(@ModelAttribute("expensePartyDto") ExpensePartyDto expensePartyDto) {
		return "exp/expensePartyRegister";
	}

	@RequestMapping(value = "/expensePartyRegister", method = RequestMethod.POST)
	public String expensePartySubmit(@Valid @ModelAttribute("expensePartyDto") ExpensePartyDto expensePartyDto, BindingResult result, RedirectAttributes ra, HttpServletResponse response) {

		if (result.hasErrors()) {
			for (ObjectError err: result.getAllErrors()) {
				LOGGER.log(Level.SEVERE, err.getDefaultMessage());
			}
		}else {
			//check if email has already been taken
			User user = userService.getByEmail(expensePartyDto.getEmail());
			if (user != null) {
				ObjectError error = new ObjectError("email","An account already exists for this email.");
				result.addError(error);
				return "exp/expensePartyRegister";
			}
		}
		//Trnasfer all the data to the Entity being done in the Service layer 
		try{
			expensePartyDto = expensePartyService.createExpenseParty(expensePartyDto);
			ra.addFlashAttribute("expensePartyDto",expensePartyDto);
			return "exp/expensePartyConfirm";
		}catch(UserRegisteredAlreadyException ex) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Email already exists!");
		}
		
		
	}
	
	@RequestMapping(value = "/expensePartyAmend", method = RequestMethod.GET)
	public String expensePartyAmend(@ModelAttribute("expenseParty") ExpensePartyDto expensePartyDto, @RequestParam Long partyId) {
		expensePartyDto = expensePartyService.findExpensePartyById(partyId);
		return "exp/expensePartyAmend";
	}
	
	//@PostMapping("/expensePartyAmend")
	@RequestMapping(value = "/expensePartyAmend", method = RequestMethod.POST)
	public String expensePartyAmendSubmit(@Valid @ModelAttribute("expenseParty") ExpensePartyDto expensePartyDto, BindingResult result, RedirectAttributes ra) {
		if (result.hasErrors()) {
			for (ObjectError err: result.getAllErrors()) {
				LOGGER.log(Level.SEVERE, err.getDefaultMessage());
			}
			return "exp/expensePartyAmend";
		}
		expensePartyDto = expensePartyService.updateExpenseParty(expensePartyDto);
		ra.addFlashAttribute("expenseParty",expensePartyDto);
		return "expensePartyConfirm";
	}
	
	@RequestMapping(value = "/expensePartyFinish", method = RequestMethod.GET)
	public String expensePartyFinish(Model model, @RequestParam Long partyId) {
		ExpensePartyDto expensePartyDto = expensePartyService.findExpensePartyById(partyId);
		model.addAttribute("expenseParty", expensePartyDto);
		return "exp/expensePartyFinish";
	}


}
