/*
* $Id$
* --------------------------------------------------------------------------------------
* Copyright (c) Reveal Technologies, LLC. All rights reserved. http://www.reveal-tech.com
*
* The software in this package is published under the terms of the CPAL v1.0
* license, a copy of which has been included with this distribution in the
* LICENSE.txt file.
*/

package com.sitewhere.spi;

import java.util.List;

import com.sitewhere.spi.device.IDevice;
import com.sitewhere.spi.device.IDeviceAlert;
import com.sitewhere.spi.device.IDeviceAssignment;
import com.sitewhere.spi.device.IDeviceLocation;
import com.sitewhere.spi.device.IDeviceMeasurements;
import com.sitewhere.spi.device.ISite;

/**
 * Holds SiteWhere information associated with a reqeust.
 * 
 * @author dadams
 */
public interface ISiteWhereContext {

	/**
	 * Get site for assignment associated with current request.
	 * 
	 * @return
	 */
	public ISite getSite();

	/**
	 * Get the device associated with the current request.
	 * 
	 * @return
	 */
	public IDevice getDevice();

	/**
	 * Get current assignment for device associated with the request.
	 * 
	 * @return
	 */
	public IDeviceAssignment getDeviceAssignment();

	/**
	 * Get the device measurements associated with the current request.
	 * 
	 * @return
	 */
	public List<IDeviceMeasurements> getDeviceMeasurements();

	/**
	 * Get the device locations associated with the current request.
	 * 
	 * @return
	 */
	public List<IDeviceLocation> getDeviceLocations();

	/**
	 * Get the device alerts associated with the current request.
	 * 
	 * @return
	 */
	public List<IDeviceAlert> getDeviceAlerts();
}