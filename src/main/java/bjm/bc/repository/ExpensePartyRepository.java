package bjm.bc.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import bjm.bc.model.ExpenseParty;

public interface ExpensePartyRepository extends JpaRepository<ExpenseParty, Long> {
	
	public ExpenseParty findByEmail(String email);

}
