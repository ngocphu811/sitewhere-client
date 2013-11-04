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

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.sitewhere.rest.model.device.DeviceMeasurements;
import com.sitewhere.rest.model.device.MeasurementEntry;
import com.sitewhere.rest.model.device.MeasurementsProvider;
import com.sitewhere.spi.common.IMeasurementEntry;
import com.sitewhere.spi.device.request.IDeviceMeasurementsCreateRequest;

/**
 * Model object used to create a new {@link DeviceMeasurements} via REST APIs.
 * 
 * @author Derek
 */
@JsonIgnoreProperties
@JsonInclude(Include.NON_NULL)
public class DeviceMeasurementsCreateRequest extends DeviceEventCreateRequest implements
		IDeviceMeasurementsCreateRequest {

	/** Measurements metadata */
	private MeasurementsProvider measurementsMetadata = new MeasurementsProvider();

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.sitewhere.spi.device.IMeasurementsProvider#addOrReplaceMeasurement(java.lang
	 * .String, java.lang.Double)
	 */
	public void addOrReplaceMeasurement(String name, Double value) {
		measurementsMetadata.addOrReplaceMeasurement(name, value);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.sitewhere.spi.device.IMeasurementsProvider#removeMeasurement(java.lang.String)
	 */
	public IMeasurementEntry removeMeasurement(String name) {
		return measurementsMetadata.removeMeasurement(name);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.sitewhere.spi.device.IMeasurementsProvider#getMeasurement(java.lang.String)
	 */
	public IMeasurementEntry getMeasurement(String name) {
		return measurementsMetadata.getMeasurement(name);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.sitewhere.spi.device.IMeasurementsProvider#getMeasurements()
	 */
	public List<IMeasurementEntry> getMeasurements() {
		return measurementsMetadata.getMeasurements();
	}

	/**
	 * Needed for JSON marshaling.
	 * 
	 * @param entries
	 */
	public void setMeasurements(List<MeasurementEntry> entries) {
		this.measurementsMetadata = new MeasurementsProvider();
		measurementsMetadata.setMeasurements(entries);
	}
}