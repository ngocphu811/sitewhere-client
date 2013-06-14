/*
* $Id$
* --------------------------------------------------------------------------------------
* Copyright (c) Reveal Technologies, LLC. All rights reserved. http://www.reveal-tech.com
*
* The software in this package is published under the terms of the CPAL v1.0
* license, a copy of which has been included with this distribution in the
* LICENSE.txt file.
*/

package com.sitewhere.spi.user;

import java.util.Calendar;

/**
 * Interface for accessing user information.
 * 
 * @author Derek
 */
public interface IUser {

	/**
	 * Get the unique user id.
	 * 
	 * @return
	 */
	public long getId();

	/**
	 * Get the username.
	 * 
	 * @return
	 */
	public String getUsername();

	/**
	 * Set the username.
	 * 
	 * @param username
	 */
	public void setUsername(String username);

	/**
	 * Get the password.
	 * 
	 * @return
	 */
	public String getHashedPassword();

	/**
	 * Set the password.
	 * 
	 * @param password
	 */
	public void setHashedPassword(String password);

	/**
	 * Get the common name.
	 * 
	 * @return
	 */
	public String getFirstName();

	/**
	 * Set the common name.
	 * 
	 * @param first
	 */
	public void setFirstName(String first);

	/**
	 * Get the surname.
	 * 
	 * @return
	 */
	public String getLastName();

	/**
	 * Set the surname.
	 * 
	 * @param last
	 */
	public void setLastName(String last);

	/**
	 * Get the last login date.
	 * 
	 * @return
	 */
	public Calendar getLastLogin();

	/**
	 * Set the last login date.
	 * 
	 * @param value
	 */
	public void setLastLogin(Calendar value);

	/**
	 * Get the account status.
	 * 
	 * @return
	 */
	public AccountStatus getStatus();

	/**
	 * Set the account status.
	 * 
	 * @param status
	 */
	public void setStatus(AccountStatus status);
}