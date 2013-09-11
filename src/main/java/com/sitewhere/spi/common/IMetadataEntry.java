/*
* $Id$
* --------------------------------------------------------------------------------------
* Copyright (c) Reveal Technologies, LLC. All rights reserved. http://www.reveal-tech.com
*
* The software in this package is published under the terms of the CPAL v1.0
* license, a copy of which has been included with this distribution in the
* LICENSE.txt file.
*/

package com.sitewhere.spi.common;

/**
 * A single entry for a metadataprovider.
 * 
 * @author Derek Adams
 */
public interface IMetadataEntry {

	/**
	 * Get metadata field name.
	 * 
	 * @return
	 */
	public String getName();

	/**
	 * Get metadata field value.
	 * 
	 * @return
	 */
	public String getValue();
}