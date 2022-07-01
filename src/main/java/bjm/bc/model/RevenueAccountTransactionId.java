package bjm.bc.model;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class RevenueAccountTransactionId implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Column(name = "REVENUE_ACCOUNT_ID")
	private long revenueAccountId;
	
	@Column(name = "CENTRAL_ACCOUNT_ID")
	private long centralAccountId;
	
	@Override
	public int hashCode() {
		return Objects.hash(centralAccountId, revenueAccountId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		RevenueAccountTransactionId other = (RevenueAccountTransactionId) obj;
		return centralAccountId == other.centralAccountId && revenueAccountId == other.revenueAccountId;
	}
	
	

}
