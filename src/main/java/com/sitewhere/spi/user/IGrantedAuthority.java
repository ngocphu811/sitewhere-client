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

/**
 * Interface for granted authority information.
 * 
 * @author Derek
 */
public interface IGrantedAuthority {

	/**
	 * Get the unique authority id.
	 * 
	 * @return
	 */
	public long getId();

	/**
	 * Get the authority name.
	 * 
	 * @return
	 */
	public String getAuthority();

	/**
	 * Set the authority name.
	 * 
	 * @param name
	 */
	public void setAuthority(String name);

	/**
	 * Get the description.
	 * 
	 * @return
	 */
	public String getDescription();

	/**
	 * Set the description.
	 * 
	 * @param description
	 */
	public void setDescription(String description);
}