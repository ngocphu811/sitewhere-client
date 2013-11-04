/*
 * IMeasurementProvider.java 
 * --------------------------------------------------------------------------------------
 * Copyright (c) Reveal Technologies, LLC. All rights reserved. http://www.reveal-tech.com
 *
 * The software in this package is published under the terms of the CPAL v1.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE.txt file.
 */
package com.sitewhere.spi.device;

import java.util.List;

import com.sitewhere.spi.common.IMeasurementEntry;

/**
 * Class that stores measurement metadata.
 * 
 * @author Derek
 */
public interface IMeasurementsProvider {

	/**
	 * Add or replace measurement data.
	 * 
	 * @param name
	 * @param value
	 */
	public void addOrReplaceMeasurement(String name, Double value);

	/**
	 * Remove a measurement.
	 * 
	 * @param name
	 * @return
	 */
	public IMeasurementEntry removeMeasurement(String name);

	/**
	 * Get measurement field.
	 * 
	 * @param name
	 * @return
	 */
	public IMeasurementEntry getMeasurement(String name);

	/**
	 * Get all measurement entries.
	 * 
	 * @return
	 */
	public List<IMeasurementEntry> getMeasurements();
}