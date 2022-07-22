package bjm.bc.dto;

public class RevenueAccountDto {
	
	private long id;
	
	private long partyId;
	
	private String revenueType;
	
	private double balance;
	
	private double balanceToAdd;
	
	private String info;
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	

	public long getPartyId() {
		return partyId;
	}

	public void setPartyId(long partyId) {
		this.partyId = partyId;
	}

	public String getRevenueType() {
		return revenueType;
	}

	public void setRevenueType(String revenueType) {
		this.revenueType = revenueType;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public double getBalanceToAdd() {
		return balanceToAdd;
	}

	public void setBalanceToAdd(double balanceToAdd) {
		this.balanceToAdd = balanceToAdd;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}
	
	
	
	

}
