package bjm.bc.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import bjm.bc.annotation.PasswordValueMatch;
import bjm.bc.model.RevenueType;


@PasswordValueMatch.List({
    @PasswordValueMatch(
            field = "password",
            fieldMatch = "passwordConfirm",
            message = "Passwords do not match!"
    )
})
public class RevenuePartyDto {
	
	private long id;
	
	@NotEmpty
	@Size(min = 2, max = 90, message = "Name size must be between 2 and 90")
	private String name;
	@Email
	//@Pattern(regexp = "^(.+)@(.+)$", message = "Invalid Email")
	private String email;
	private String organisation;
	private String ownerAdhaarNumber;
	private String accountHash;
	private String accountName;
	private RevenueType[] revenueTypes;
	@NotEmpty
	@Pattern(regexp = "^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[^A-Za-z0-9]).{8,}$", message="Password must be min 8 chars long and include at least one numeric digit and one special character.")
	private String password;
	@NotEmpty
	private String memorableDateStr;
	@NotEmpty
	@Pattern(regexp = "^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[^A-Za-z0-9]).{8,}$", message="Password must be min 8 chars long and include at least one numeric digit and one special character.")
	private String passwordConfirm;
	
	private String partyHash;
	
	
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getOrganisation() {
		return organisation;
	}
	public void setOrganisation(String organisation) {
		this.organisation = organisation;
	}
	public String getOwnerAdhaarNumber() {
		return ownerAdhaarNumber;
	}
	public void setOwnerAdhaarNumber(String ownerAdhaarNumber) {
		this.ownerAdhaarNumber = ownerAdhaarNumber;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getAccountHash() {
		return accountHash;
	}
	public void setAccountHash(String accountHash) {
		this.accountHash = accountHash;
	}
		
	public String getAccountName() {
		return accountName;
	}
	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}
		
	public RevenueType[] getRevenueTypes() {
		return revenueTypes;
	}
	public void setRevenueTypes(RevenueType[] revenueTypes) {
		this.revenueTypes = revenueTypes;
	}
	public String getMemorableDateStr() {
		return memorableDateStr;
	}
	public void setMemorableDateStr(String memorableDateStr) {
		this.memorableDateStr = memorableDateStr;
	}
	public String getPasswordConfirm() {
		return passwordConfirm;
	}
	public void setPasswordConfirm(String passwordConfirm) {
		this.passwordConfirm = passwordConfirm;
	}
	public String getPartyHash() {
		return partyHash;
	}
	public void setPartyHash(String partyHash) {
		this.partyHash = partyHash;
	}
	
	
	
	
	
	
}
