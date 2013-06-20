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
 * Interface for accessing group information.
 * 
 * @author Derek
 */
public interface IGroup {

	/**
	 * Get the unique group id.
	 * 
	 * @return
	 */
	public long getId();

	/**
	 * Get the group name.
	 * 
	 * @return
	 */
	public String getName();

	/**
	 * Get the group description.
	 * 
	 * @return
	 */
	public String getDescription();
}