/*
 * $Id$
 * --------------------------------------------------------------------------------------
 * Copyright (c) Reveal Technologies, LLC. All rights reserved. http://www.reveal-tech.com
 *
 * The software in this package is published under the terms of the CPAL v1.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE.txt file.
 */
package com.sitewhere.rest.model.user;

import java.util.Date;

import com.sitewhere.rest.model.common.MetadataProviderEntity;
import com.sitewhere.spi.user.AccountStatus;
import com.sitewhere.spi.user.IUser;

/**
 * Model class for a User.
 * 
 * @author Derek Adams
 */
public class User extends MetadataProviderEntity implements IUser {

	/** Unique username */
	private String username;

	/** Hashed password */
	private String hashedPassword;

	/** First name */
	private String firstName;

	/** Last name */
	private String lastName;

	/** Last login */
	private Date lastLogin;

	/** Account status */
	private AccountStatus status;

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
	public Date getLastLogin() {
		return lastLogin;
	}

	public void setLastLogin(Date lastLogin) {
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
	 * @param input
	 * @return
	 */
	public static User copy(IUser input) {
		User result = new User();
		result.setUsername(input.getUsername());
		result.setHashedPassword(input.getHashedPassword());
		result.setFirstName(input.getFirstName());
		result.setLastName(input.getLastName());
		result.setLastLogin(input.getLastLogin());
		result.setStatus(input.getStatus());
		MetadataProviderEntity.copy(input, result);
		return result;
	}
}