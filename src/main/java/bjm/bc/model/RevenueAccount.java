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

@Entity(name = "REVENUE_ACCOUNT")
public class RevenueAccount {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private long id;
	
	@Column(name = "NAME")
	private String name;
	
	@Column(name = "REVENUE_TYPE")
	@Enumerated(EnumType.STRING)
	private RevenueType revenueType;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "REVENUE_PARTY_ID", nullable = false)
	private RevenueParty revenueParty;
	
	
	
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

	public RevenueType getRevenueType() {
		return revenueType;
	}

	public void setRevenueType(RevenueType revenueType) {
		this.revenueType = revenueType;
	}

	public RevenueParty getRevenueParty() {
		return revenueParty;
	}

	public void setRevenueParty(RevenueParty revenueParty) {
		this.revenueParty = revenueParty;
	}

}
