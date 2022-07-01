package bjm.bc.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;

@Entity(name = "REVENUE_ACCOUNT_TRANSACTION")
public class RevenueAccountTransaction {
	
	@EmbeddedId
	private RevenueAccountTransactionId revenueAccountTransactionId;
		
	@Column(name = "AMOUNT")
	private double amount;
	
	@Column(name = "TRANSACTION_TIME")
	private LocalDateTime transactionTime;
	
	@ManyToOne
	@MapsId("ID")
	private RevenueAccount revenueAccount;

	public RevenueAccountTransactionId getRevenueAccountTransactionId() {
		return revenueAccountTransactionId;
	}

	public void setRevenueAccountTransactionId(RevenueAccountTransactionId revenueAccountTransactionId) {
		this.revenueAccountTransactionId = revenueAccountTransactionId;
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
