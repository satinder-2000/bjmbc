package bjm.bc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import bjm.bc.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
	
	User findByEmail(String email);

}
