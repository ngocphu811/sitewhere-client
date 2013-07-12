/*
 * DeviceMeasurementsCreateRequest.java 
 * --------------------------------------------------------------------------------------
 * Copyright (c) Reveal Technologies, LLC. All rights reserved. http://www.reveal-tech.com
 *
 * The software in this package is published under the terms of the CPAL v1.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE.txt file.
 */
package com.sitewhere.rest.model.device.request;

import com.sitewhere.rest.model.device.DeviceMeasurements;
import com.sitewhere.rest.model.device.MetadataProvider;
import com.sitewhere.spi.device.request.IDeviceMeasurementsCreateRequest;

/**
 * Model object used to create a new {@link DeviceMeasurements} via REST APIs.
 * 
 * @author Derek
 */
public class DeviceMeasurementsCreateRequest extends DeviceEventCreateRequest implements
		IDeviceMeasurementsCreateRequest {

	/** Measurements metadata */
	private MetadataProvider measurementsMetadata;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.sitewhere.spi.device.request.IDeviceMeasurementsCreateRequest#getMeasurementsMetadata()
	 */
	public MetadataProvider getMeasurementsMetadata() {
		return measurementsMetadata;
	}

	public void setMeasurementsMetadata(MetadataProvider measurementsMetadata) {
		this.measurementsMetadata = measurementsMetadata;
	}
}