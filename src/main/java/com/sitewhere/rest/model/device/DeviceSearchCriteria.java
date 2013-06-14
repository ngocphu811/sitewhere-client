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

import com.sitewhere.spi.device.IDeviceSearchCriteria;

/**
 * Implementation of IDeviceSearchCriteria.
 * 
 * @author Derek Adams
 */
public class DeviceSearchCriteria implements IDeviceSearchCriteria {

	/** Flag for whether deleted devices are included */
	private boolean includeDeleted = false;

	public boolean isIncludeDeleted() {
		return includeDeleted;
	}

	public void setIncludeDeleted(boolean includeDeleted) {
		this.includeDeleted = includeDeleted;
	}
}