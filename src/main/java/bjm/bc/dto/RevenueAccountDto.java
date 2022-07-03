package bjm.bc.dto;

public class RevenueAccountDto {
	
	private long id;
	
	private String name;
	
	private String revenueType;
	
	private double balance;
	
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
	
	

}
