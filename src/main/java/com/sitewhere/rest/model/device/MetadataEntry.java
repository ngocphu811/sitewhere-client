/*
* $Id$
* --------------------------------------------------------------------------------------
* Copyright (c) Reveal Technologies, LLC. All rights reserved. http://www.reveal-tech.com
*
* The software in this package is published under the terms of the CPAL v1.0
* license, a copy of which has been included with this distribution in the
* LICENSE.txt file.
*/

package com.sitewhere.rest.model.device;

import com.sitewhere.spi.device.IMetadataEntry;

/**
 * A single entry for a metadata provider.
 * 
 * @author Derek Adams
 */
public class MetadataEntry implements IMetadataEntry {

	/** Entry name */
	private String name;

	/** Entry value */
	private String value;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.sitewhere.spi.device.IMetadataEntry#getName()
	 */
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.sitewhere.spi.device.IMetadataEntry#getValue()
	 */
	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
}