package bjm.bc.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.PrimaryKeyJoinColumns;

@Entity(name = "EXPENSE_ACCOUNT")
public class ExpenseAccount {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private long id;
	
	@Column(name = "NAME")
	private String name;
	
	@Column(name = "EXPENSE_TYPE")
	@Enumerated(EnumType.STRING)
	private ExpenseType expenseType;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "EXPENSE_PARTY_ID", nullable = false)
	private ExpenseParty expenseParty;
	
	@Column(name = "BALANCE")
	private double balance;
	
	@OneToMany(mappedBy = "expenseAccount", cascade =CascadeType.ALL)
	@PrimaryKeyJoinColumns({
		@PrimaryKeyJoinColumn(name = "EXPENSE_ACCOUNT_ID", referencedColumnName = "ID"),
		@PrimaryKeyJoinColumn(name = "CENTRAL_ACCOUNT_ID", referencedColumnName = "ID")
	})
	private Set<ExpenseAccountTransaction> expenseAccountTransactions;

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

	public ExpenseType getExpenseType() {
		return expenseType;
	}

	public void setExpenseType(ExpenseType expenseType) {
		this.expenseType = expenseType;
	}

	public ExpenseParty getExpenseParty() {
		return expenseParty;
	}

	public void setExpenseParty(ExpenseParty expenseParty) {
		this.expenseParty = expenseParty;
	}
	
	

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public Set<ExpenseAccountTransaction> getExpenseAccountTransactions() {
		return expenseAccountTransactions;
	}

	public void setExpenseAccountTransactions(Set<ExpenseAccountTransaction> expenseAccountTransactions) {
		this.expenseAccountTransactions = expenseAccountTransactions;
	}
	
	

}
