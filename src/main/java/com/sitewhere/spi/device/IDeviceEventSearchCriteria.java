/*
 * IDeviceEventSearchCriteria.java 
 * --------------------------------------------------------------------------------------
 * Copyright (c) Reveal Technologies, LLC. All rights reserved. http://www.reveal-tech.com
 *
 * The software in this package is published under the terms of the CPAL v1.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE.txt file.
 */
package com.sitewhere.spi.device;

import com.sitewhere.spi.common.ISearchCriteria;

/**
 * Search criteria that applies to device event subclasses.
 * 
 * @author Derek
 */
public interface IDeviceEventSearchCriteria extends ISearchCriteria {

	/**
	 * Get token of parent assignment for events.
	 * 
	 * @return
	 */
	public String getDeviceAssignmentToken();
}