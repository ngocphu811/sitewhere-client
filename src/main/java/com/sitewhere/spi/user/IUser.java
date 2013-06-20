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
	 * Get the password.
	 * 
	 * @return
	 */
	public String getHashedPassword();

	/**
	 * Get the common name.
	 * 
	 * @return
	 */
	public String getFirstName();

	/**
	 * Get the surname.
	 * 
	 * @return
	 */
	public String getLastName();

	/**
	 * Get the last login date.
	 * 
	 * @return
	 */
	public Calendar getLastLogin();

	/**
	 * Get the account status.
	 * 
	 * @return
	 */
	public AccountStatus getStatus();
}