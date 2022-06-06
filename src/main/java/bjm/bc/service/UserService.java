package bjm.bc.service;

import java.time.LocalDateTime;
import java.time.temporal.ChronoField;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import bjm.bc.model.User;
import bjm.bc.repository.UserRepository;
import bjm.bc.util.PasswordUtil;

@Service
@Transactional
public class UserService {
	
	public static final int MAX_FAILED_ATTEMPTS = 3;
    
    private static final long LOCK_TIME_DURATION = 24 * 60 * 60 * 1000; // 24 hours
    
    @Autowired
    private UserRepository userRepository;
    
    public void lock(User user) {
        user.setAccountLocked(true);
        user.setLockTime(LocalDateTime.now());
         
        userRepository.save(user);
    }
     
    public boolean unlockWhenTimeExpired(User user) {
        long lockTimeInMillis = user.getLockTime().getLong(ChronoField.INSTANT_SECONDS);
        long currentTimeInMillis = System.currentTimeMillis();
         
        if (lockTimeInMillis + LOCK_TIME_DURATION < currentTimeInMillis) {
            user.setAccountLocked(false);
            user.setLockTime(null);
            user.setFailedAttempts(0);
            userRepository.save(user);
            return true;
        }
        return false;
    }

	public User getByEmail(String email, String password) {
		User user = userRepository.findByEmail(email);
		return user;
	}
	
	/**
	 * @param user
	 * @return user. failed attempts have been updated. if it has been a last attempt, the account has been locked
	 */
	public User increaseFailedLoginnAttempt(User user) {
		user.setFailedAttempts(user.getFailedAttempts()+1);
		if (user.getFailedAttempts() ==  MAX_FAILED_ATTEMPTS) {
			user.setAccountLocked(true);
		}
		userRepository.save(user);
		return user;
	}
    
    

}
