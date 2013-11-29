/*
 * DoubleChartEntry.java 
 * --------------------------------------------------------------------------------------
 * Copyright (c) Reveal Technologies, LLC. All rights reserved. http://www.reveal-tech.com
 *
 * The software in this package is published under the terms of the CPAL v1.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE.txt file.
 */
package com.sitewhere.rest.model.device.charting;

import java.util.Date;

import com.sitewhere.spi.device.charting.IChartEntry;

/**
 * Chart entry that has a {@link Double} value.
 * 
 * @author Derek
 */
public class DoubleChartEntry implements IChartEntry<Double> {

	/** Entry value */
	private Double value;

	/** Date of measurement */
	private Date measurementDate;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.sitewhere.spi.device.charting.IChartEntry#getValue()
	 */
	@Override
	public Double getValue() {
		return value;
	}

	public void setValue(Double value) {
		this.value = value;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.sitewhere.spi.device.charting.IChartEntry#getMeasurementDate()
	 */
	@Override
	public Date getMeasurementDate() {
		return measurementDate;
	}

	public void setMeasurementDate(Date measurementDate) {
		this.measurementDate = measurementDate;
	}
}