package bjm.bc.dto;

public class ExpenseAccountDto {
	
	private long id;
	
	private String name;
	
	private String expenseType;
	
	private double balance;
	private double balanceToAdd;
	private double balanceToWithdraw;
	
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

	public String getExpenseType() {
		return expenseType;
	}

	public void setExpenseType(String expenseType) {
		this.expenseType = expenseType;
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

	public double getBalanceToWithdraw() {
		return balanceToWithdraw;
	}

	public void setBalanceToWithdraw(double balanceToWithdraw) {
		this.balanceToWithdraw = balanceToWithdraw;
	}

	
	
	
	

}
