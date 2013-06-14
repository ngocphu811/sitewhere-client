/*
* $Id$
* --------------------------------------------------------------------------------------
* Copyright (c) Reveal Technologies, LLC. All rights reserved. http://www.reveal-tech.com
*
* The software in this package is published under the terms of the CPAL v1.0
* license, a copy of which has been included with this distribution in the
* LICENSE.txt file.
*/

package com.sitewhere.spi.asset;

/**
 * Interface for an asset that is a person.
 * 
 * @author dadams
 */
public interface IPersonAsset extends IAsset {

	/**
	 * Get the unique username.
	 * 
	 * @return
	 */
	public String getUserName();

	/**
	 * Get the full name of the person.
	 * 
	 * @return
	 */
	public String getName();

	/**
	 * Get the email address.
	 * 
	 * @return
	 */
	public String getEmailAddress();

	/**
	 * Get the primary photo URL.
	 * 
	 * @return
	 */
	public String getPhotoUrl();

	/**
	 * Get the property that holds email address.
	 * 
	 * @return
	 */
	public String getUserNameProperty();

	/**
	 * Get the property that holds name.
	 * 
	 * @return
	 */
	public String getNameProperty();

	/**
	 * Get the property that holds email address.
	 * 
	 * @return
	 */
	public String getEmailAddressProperty();

	/**
	 * Get the property that holds primary photo URL.
	 * 
	 * @return
	 */
	public String getPhotoUrlProperty();
}