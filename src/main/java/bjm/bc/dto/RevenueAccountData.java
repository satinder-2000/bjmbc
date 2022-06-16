package bjm.bc.dto;

public class RevenueAccountData {
	
	private long id;
	
	private String name;
	
	private String revenueType;
	
	private String accountHash;

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

	public String getAccountHash() {
		return accountHash;
	}

	public void setAccountHash(String accountHash) {
		this.accountHash = accountHash;
	}
	
	

}
