package bjm.bc.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Transient;
import javax.persistence.GenerationType;

@Entity(name = "REVENUE_PARTY")
public class RevenueParty {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private long id;
	@Column(name = "NAME")
	private String name;
	@Column(name = "EMAIL")
	private String email;
	@Column(name = "ORGANISATION")
    private String organisation;
	@Column(name = "OWNER_ADHAAR_NUMBER")
    private String ownerAdhaarNumber;
	@Column(name = "MEMORABLE_DATE")
    private LocalDate memorableDate;
	@Column(name = "ACCOUNT_HASH")
	private String accountHash;
	@Column(name = "REVENUE_TYPE")
	@Enumerated(EnumType.STRING)
	private RevenueType revenueType;
	@Column(name = "PASSWORD")
    private String password;
	
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
	public RevenueType getRevenueType() {
		return revenueType;
	}
	public void setRevenueType(RevenueType revenueType) {
		this.revenueType = revenueType;
	}
	public LocalDate getMemorableDate() {
		return memorableDate;
	}
	public void setMemorableDate(LocalDate memorableDate) {
		this.memorableDate = memorableDate;
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
