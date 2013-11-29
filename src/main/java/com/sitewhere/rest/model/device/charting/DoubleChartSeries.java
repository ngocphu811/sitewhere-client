/*
 * DoubleChartSeries.java 
 * --------------------------------------------------------------------------------------
 * Copyright (c) Reveal Technologies, LLC. All rights reserved. http://www.reveal-tech.com
 *
 * The software in this package is published under the terms of the CPAL v1.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE.txt file.
 */
package com.sitewhere.rest.model.device.charting;

import java.util.ArrayList;
import java.util.List;

import com.sitewhere.spi.device.charting.IChartEntry;
import com.sitewhere.spi.device.charting.IChartSeries;

public class DoubleChartSeries implements IChartSeries<Double> {

	/** Measurement id */
	private String measurementId;

	/** Entries for the chart series */
	private List<IChartEntry<Double>> entries = new ArrayList<IChartEntry<Double>>();

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.sitewhere.spi.device.charting.IChartSeries#getMeasurementId()
	 */
	@Override
	public String getMeasurementId() {
		return measurementId;
	}

	public void setMeasurementId(String measurementId) {
		this.measurementId = measurementId;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.sitewhere.spi.device.charting.IChartSeries#getEntries()
	 */
	@Override
	public List<IChartEntry<Double>> getEntries() {
		return entries;
	}

	public void setEntries(List<IChartEntry<Double>> entries) {
		this.entries = entries;
	}
}