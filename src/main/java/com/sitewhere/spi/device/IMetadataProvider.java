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

import java.util.List;

/**
 * Interface for an entity that has associated metadata.
 * 
 * @author dadams
 */
public interface IMetadataProvider {

	/**
	 * Add or replace metadata.
	 * 
	 * @param name
	 * @param value
	 */
	public void addOrReplaceMetadata(String name, String value);

	/**
	 * Remove a metadata entry.
	 * 
	 * @param name
	 * @return
	 */
	public IMetadataEntry removeMetadata(String name);

	/**
	 * Get metadata field as an Object.
	 * 
	 * @param name
	 * @return
	 */
	public IMetadataEntry getMetadata(String name);

	/**
	 * Get all metadata entries.
	 * 
	 * @return
	 */
	public List<IMetadataEntry> getMetadata();
}