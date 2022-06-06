package bjm.bc.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name = "USER")
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private long id;
	@Column(name = "EMAIL")
	private String email;
	@Column(name = "PASSWORD")
    private String password;
	@Column(name = "FAILED_ATTEMPTS")
	private int failedAttempts;
	@Column(name = "ACCOUNT_LOCKED")
	private boolean accountLocked;
	@Column(name = "LOCK_TIME")
	private LocalDateTime lockTime;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getFailedAttempts() {
		return failedAttempts;
	}
	public void setFailedAttempts(int failedAttempts) {
		this.failedAttempts = failedAttempts;
	}
	
	public boolean isAccountLocked() {
		return accountLocked;
	}
	public void setAccountLocked(boolean accountLocked) {
		this.accountLocked = accountLocked;
	}
	public LocalDateTime getLockTime() {
		return lockTime;
	}
	public void setLockTime(LocalDateTime lockTime) {
		this.lockTime = lockTime;
	}
	
	
	
	

}
