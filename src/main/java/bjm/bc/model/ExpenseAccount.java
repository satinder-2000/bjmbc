package bjm.bc.model;

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
	
	

}
