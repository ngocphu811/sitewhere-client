/*
* $Id$
* --------------------------------------------------------------------------------------
* Copyright (c) Reveal Technologies, LLC. All rights reserved. http://www.reveal-tech.com
*
* The software in this package is published under the terms of the CPAL v1.0
* license, a copy of which has been included with this distribution in the
* LICENSE.txt file.
*/

package com.sitewhere.spi.device;

import java.util.Calendar;

import com.sitewhere.spi.common.ISiteWhereEntity;

/**
 * High-level abstract of a site where devices are used to track assets.
 * 
 * @author Derek
 */
public interface ISite extends IMetadataProvider, ISiteWhereEntity {

	/**
	 * Get unique token.
	 * 
	 * @return
	 */
	public String getToken();

	/**
	 * Get the site name.
	 * 
	 * @return
	 */
	public String getName();

	/**
	 * Get the description.
	 * 
	 * @return
	 */
	public String getDescription();

	/**
	 * Get the image URL.
	 * 
	 * @return
	 */
	public String getImageUrl();

	/**
	 * Get map type.
	 * 
	 * @return
	 */
	public String getMapType();

	/**
	 * Get map metadata.
	 * 
	 * @return
	 */
	public IMetadataProvider getMapMetadata();

	/**
	 * Get the created date.
	 * 
	 * @return
	 */
	public Calendar getCreatedDate();
}