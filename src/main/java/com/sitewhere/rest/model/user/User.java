package com.sitewhere.rest.model.user;

import java.util.Calendar;

import com.sitewhere.spi.user.AccountStatus;
import com.sitewhere.spi.user.IUser;

/**
 * Model class for a User.
 * 
 * @author Derek Adams
 */
public class User implements IUser {

	/** Unique id */
	private long id;

	/** Unique username */
	private String username;

	/** Hashed password */
	private String hashedPassword;

	/** First name */
	private String firstName;

	/** Last name */
	private String lastName;

	/** Last login */
	private Calendar lastLogin;

	/** Account status */
	private AccountStatus status;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.sitewhere.spi.user.IUser#getId()
	 */
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.sitewhere.spi.user.IUser#getUsername()
	 */
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.sitewhere.spi.user.IUser#getHashedPassword()
	 */
	public String getHashedPassword() {
		return hashedPassword;
	}

	public void setHashedPassword(String hashedPassword) {
		this.hashedPassword = hashedPassword;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.sitewhere.spi.user.IUser#getFirstName()
	 */
	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.sitewhere.spi.user.IUser#getLastName()
	 */
	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.sitewhere.spi.user.IUser#getLastLogin()
	 */
	public Calendar getLastLogin() {
		return lastLogin;
	}

	public void setLastLogin(Calendar lastLogin) {
		this.lastLogin = lastLogin;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.sitewhere.spi.user.IUser#getStatus()
	 */
	public AccountStatus getStatus() {
		return status;
	}

	public void setStatus(AccountStatus status) {
		this.status = status;
	}

	/**
	 * Copy contents from the SPI class.
	 * 
	 * @param user
	 * @return
	 */
	public static User copy(IUser user) {
		User result = new User();
		result.setId(user.getId());
		result.setUsername(user.getUsername());
		result.setFirstName(user.getFirstName());
		result.setLastName(user.getLastName());
		result.setLastLogin(user.getLastLogin());
		result.setStatus(user.getStatus());
		return result;
	}
}