/*
 * MeasurementsProvider.java 
 * --------------------------------------------------------------------------------------
 * Copyright (c) Reveal Technologies, LLC. All rights reserved. http://www.reveal-tech.com
 *
 * The software in this package is published under the terms of the CPAL v1.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE.txt file.
 */
package com.sitewhere.rest.model.device;

import java.util.ArrayList;
import java.util.List;

import com.sitewhere.spi.common.IMeasurementEntry;
import com.sitewhere.spi.device.IMeasurementsProvider;

/**
 * Handles creating/modifying a collection of measurements.
 * 
 * @author Derek
 */
public class MeasurementsProvider implements IMeasurementsProvider {

	/** List of measurement entries */
	private List<MeasurementEntry> entries = new ArrayList<MeasurementEntry>();

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.sitewhere.spi.device.IMeasurementsProvider#addOrReplaceMeasurement(java.lang
	 * .String, java.lang.Double)
	 */
	public void addOrReplaceMeasurement(String name, Double value) {
		for (MeasurementEntry entry : entries) {
			if (entry.getName().equals(name)) {
				entry.setValue(value);
				return;
			}
		}
		MeasurementEntry entry = new MeasurementEntry();
		entry.setName(name);
		entry.setValue(value);
		entries.add(entry);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.sitewhere.spi.device.IMeasurementsProvider#removeMeasurement(java.lang.String)
	 */
	public IMeasurementEntry removeMeasurement(String name) {
		MeasurementEntry toRemove = null;
		for (MeasurementEntry entry : entries) {
			if (entry.getName().equals(name)) {
				toRemove = entry;
				break;
			}
		}
		if (toRemove != null) {
			entries.remove(toRemove);
		}
		return toRemove;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.sitewhere.spi.device.IMeasurementsProvider#getMeasurement(java.lang.String)
	 */
	public IMeasurementEntry getMeasurement(String name) {
		for (MeasurementEntry entry : entries) {
			if (entry.getName().equals(name)) {
				return entry;
			}
		}
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.sitewhere.spi.device.IMeasurementsProvider#getMeasurements()
	 */
	@SuppressWarnings("unchecked")
	public List<IMeasurementEntry> getMeasurements() {
		return (List<IMeasurementEntry>) (List<? extends IMeasurementEntry>) entries;
	}

	public void setMeasurements(List<MeasurementEntry> entries) {
		this.entries = entries;
	}

	/**
	 * Copy contents of one metadata provider to another.
	 * 
	 * @param source
	 * @param target
	 */
	public static void copy(IMeasurementsProvider source, MeasurementsProvider target) {
		if (source != null) {
			List<IMeasurementEntry> sourceEntries = source.getMeasurements();
			for (IMeasurementEntry entry : sourceEntries) {
				target.addOrReplaceMeasurement(entry.getName(), entry.getValue());
			}
		}
	}
}