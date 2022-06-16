package bjm.bc.model;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Transient;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Entity(name = "EXPENSE_PARTY")
public class ExpenseParty {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private long id;
	
	@NotEmpty
	@Column(name = "NAME")
	@Size(min = 2, max = 90, message = "Name size must be between 2 and 90")
	private String name;
	@Email
	@Column(name = "EMAIL")
	private String email;
	@Column(name = "ORGANISATION")
    private String organisation;
	@Column(name = "OWNER_ADHAAR_NUMBER")
    private String ownerAdhaarNumber;
	@Column(name = "MEMORABLE_DATE")
    private LocalDate memorableDate;
	@NotEmpty
	@Pattern(regexp = "^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[^A-Za-z0-9]).{8,}$", message="Password must be min 8 chars long and include at least one numeric digit and one special character.")
	@Column(name = "PASSWORD")
    private String password;
	
	@Column(name = "PARTY_HASH")
	private String partyHash;
	
	@OneToMany(mappedBy = "expenseParty", cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<ExpenseAccount> expenseAccounts  = new HashSet<>();
	
	@Transient
	private String memorableDateStr;
	@Transient
	private String passwordConfirm;
	
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
	public LocalDate getMemorableDate() {
		return memorableDate;
	}
	public void setMemorableDate(LocalDate memorableDate) {
		this.memorableDate = memorableDate;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	
	public String getPartyHash() {
		return partyHash;
	}
	public void setPartyHash(String partyHash) {
		this.partyHash = partyHash;
	}
	public Set<ExpenseAccount> getExpenseAccounts() {
		return expenseAccounts;
	}
	public void setExpenseAccounts(Set<ExpenseAccount> expenseAccounts) {
		this.expenseAccounts = expenseAccounts;
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
	
	


}
