package bjm.bc.repository;

import org.springframework.data.repository.CrudRepository;

import bjm.bc.model.ExpenseAccount;

public interface ExpenseAccountRepository extends CrudRepository<ExpenseAccount, Long> {

}
