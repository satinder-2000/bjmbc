package bjm.bc.model;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class ExpenseAccountTransactionId implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Column(name = "EXPENSE_ACCOUNT_ID")
	private long expenseAccountId;
	
	@Column(name = "CENTRAL_ACCOUNT_ID")
	private long centralAccountId;

	@Override
	public int hashCode() {
		return Objects.hash(centralAccountId, expenseAccountId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ExpenseAccountTransactionId other = (ExpenseAccountTransactionId) obj;
		return centralAccountId == other.centralAccountId && expenseAccountId == other.expenseAccountId;
	}
}
