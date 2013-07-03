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

/**
 * Measurements associated with a device assignment at a point in time.
 * 
 * @author Derek
 */
public interface IDeviceMeasurements extends IDeviceEvent {

	/**
	 * Get metadata provider for measurements.
	 * 
	 * @return
	 */
	public IMetadataProvider getMeasurementsMetadata();
}