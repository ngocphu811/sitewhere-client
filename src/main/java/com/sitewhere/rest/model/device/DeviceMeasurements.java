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

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.sitewhere.spi.common.IMeasurementEntry;
import com.sitewhere.spi.device.IDeviceMeasurements;

/**
 * Implementation of device measurements.
 * 
 * @author dadams
 */
@JsonIgnoreProperties
@JsonInclude(Include.NON_NULL)
public class DeviceMeasurements extends DeviceEvent implements IDeviceMeasurements {

	/** Holder for measurements */
	private MeasurementsProvider measurementsMetadata = new MeasurementsProvider();

	/** Used for list presentation */
	private String propertiesSummary;

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

	public String getPropertiesSummary() {
		return propertiesSummary;
	}

	public void setPropertiesSummary(String propertiesSummary) {
		this.propertiesSummary = propertiesSummary;
	}

	/**
	 * Calculate a properties summary.
	 */
	public void calculatePropertiesSummary() {
		String result = "";
		boolean isFirst = true;
		for (IMeasurementEntry entry : measurementsMetadata.getMeasurements()) {
			if (!isFirst) {
				result += ", ";
			} else {
				isFirst = false;
			}
			result += entry.getName() + ": " + entry.getValue();
		}
		this.propertiesSummary = result;
	}

	/**
	 * Create a copy of an SPI object. Used by web services for marshaling.
	 * 
	 * @param input
	 * @return
	 */
	public static DeviceMeasurements copy(IDeviceMeasurements input) {
		DeviceMeasurements result = new DeviceMeasurements();
		DeviceEvent.copy(input, result);
		for (IMeasurementEntry entry : input.getMeasurements()) {
			result.addOrReplaceMeasurement(entry.getName(), entry.getValue());
		}
		result.calculatePropertiesSummary();
		return result;
	}
}