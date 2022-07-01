package bjm.bc.model;

import java.util.Set;

import javax.persistence.CascadeType;
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
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.PrimaryKeyJoinColumns;

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
	
	
	@OneToMany(mappedBy = "revenueAccount", cascade =CascadeType.ALL)
	@PrimaryKeyJoinColumns({
			@PrimaryKeyJoinColumn(name = "REVENUE_ACCOUNT_ID", referencedColumnName = "ID"),
			@PrimaryKeyJoinColumn(name = "CENTRAL_ACCOUNT_ID", referencedColumnName = "ID")
	})
	
	private Set<RevenueAccountTransaction> revenueAccountTransactions;
	
	
	
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

	public Set<RevenueAccountTransaction> getRevenueAccountTransactions() {
		return revenueAccountTransactions;
	}

	public void setRevenueAccountTransactions(Set<RevenueAccountTransaction> revenueAccountTransactions) {
		this.revenueAccountTransactions = revenueAccountTransactions;
	}
	
	

}
