package bjm.bc.model;

public enum ExpenseType {
	
	INTEREST_PAYMENT("Interest Payment"),
	DEFENSE("Defense"),
	FOOD_SUBSIDY("Food Subsidy"),
	HEALTHCARE("Healthcare"),
	EDUCATION("Education"),
	PENSIONS_AND_SALARIES("Pensions and Salaries"),
	AGRICULTURE("Agriculture"),
	INFRASTRUCTURE("Infrastructure");
	
	public final String value;

    private ExpenseType(String value) {
        this.value = value;
    }
}
