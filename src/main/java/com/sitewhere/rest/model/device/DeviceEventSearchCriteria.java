/*
 * DeviceEventSearchCriteria.java 
 * --------------------------------------------------------------------------------------
 * Copyright (c) Reveal Technologies, LLC. All rights reserved. http://www.reveal-tech.com
 *
 * The software in this package is published under the terms of the CPAL v1.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE.txt file.
 */
package com.sitewhere.rest.model.device;

import com.sitewhere.rest.model.common.SearchCriteria;
import com.sitewhere.spi.device.IDeviceEventSearchCriteria;

/**
 * Search criteria that applies to device event subclasses.
 * 
 * @author Derek
 */
public class DeviceEventSearchCriteria extends SearchCriteria implements IDeviceEventSearchCriteria {

	/** Token of parent device assignment */
	private String deviceAssignmentToken;

	public String getDeviceAssignmentToken() {
		return deviceAssignmentToken;
	}

	public void setDeviceAssignmentToken(String deviceAssignmentToken) {
		this.deviceAssignmentToken = deviceAssignmentToken;
	}
}