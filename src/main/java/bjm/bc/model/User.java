package bjm.bc.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

@Entity(name = "USER")
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private long id;
	@Email
	@Pattern(regexp = "^(.+)@(.+)$", message = "Invalid Email")
	@Column(name = "EMAIL")
	private String email;
	
	@NotEmpty
	//@Pattern(regexp = "^(?=.*\\\\d).{8,14}$", message="Password must be between 8 and 14 chars long and include at least one numeric digit.")
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
