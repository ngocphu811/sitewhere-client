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

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize.Inclusion;
import com.sitewhere.spi.device.IDeviceMeasurements;
import com.sitewhere.spi.device.IMetadataEntry;
import com.sitewhere.spi.device.IMetadataProvider;

/**
 * Implementation of device measurements.
 * 
 * @author dadams
 */
@JsonIgnoreProperties
@JsonSerialize(include = Inclusion.NON_NULL)
public class DeviceMeasurements extends DeviceEvent implements IDeviceMeasurements {

	/** Holder for measurements */
	protected MetadataProvider measurements = new MetadataProvider();

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.sitewhere.spi.device.IDeviceMeasurements#getMeasurementsMetadata()
	 */
	public IMetadataProvider getMeasurementsMetadata() {
		return measurements;
	}
	
	public void setMeasurementsMetadata(MetadataProvider measurements) {
		this.measurements = measurements;
	}

	@JsonIgnore
	protected MetadataProvider getMeasurements() {
		return measurements;
	}

	/** Used for list presentation */
	private String propertiesSummary;

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
		for (IMetadataEntry entry : measurements.getMetadata()) {
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
		MetadataProvider.copy(input.getMeasurementsMetadata(), result.getMeasurements());
		result.calculatePropertiesSummary();
		return result;
	}
}