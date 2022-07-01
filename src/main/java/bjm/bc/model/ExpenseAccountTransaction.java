package bjm.bc.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;

@Entity(name = "EXPENSE_ACCOUNT_TRANSACTION")
public class ExpenseAccountTransaction {
	
	@EmbeddedId
	private ExpenseAccountTransactionId expenseAccountTransactionId;
	
	
	@Column(name = "AMOUNT")
	private double amount;
	
	@Column(name = "TRANSACTION_TIME")
	private LocalDateTime transactionTime;
	
	@ManyToOne
	@MapsId("ID")
	private ExpenseAccount expenseAccount;

	public ExpenseAccountTransactionId getExpenseAccountTransactionId() {
		return expenseAccountTransactionId;
	}

	public void setExpenseAccountTransactionId(ExpenseAccountTransactionId expenseAccountTransactionId) {
		this.expenseAccountTransactionId = expenseAccountTransactionId;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public LocalDateTime getTransactionTime() {
		return transactionTime;
	}

	public void setTransactionTime(LocalDateTime transactionTime) {
		this.transactionTime = transactionTime;
	}
	
	

}
