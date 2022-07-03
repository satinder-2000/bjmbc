package bjm.bc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import bjm.bc.model.RevenueParty;

public interface RevenuePartyRepository extends JpaRepository<RevenueParty, Long> {
	
	public RevenueParty findByEmail(String email);
	
}
