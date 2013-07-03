/*
 * ISiteWhereClient.java 
 * --------------------------------------------------------------------------------------
 * Copyright (c) Reveal Technologies, LLC. All rights reserved. http://www.reveal-tech.com
 *
 * The software in this package is published under the terms of the CPAL v1.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE.txt file.
 */
package com.sitewhere.spi;

import com.sitewhere.rest.model.device.Device;
import com.sitewhere.rest.model.device.DeviceAlert;
import com.sitewhere.rest.model.device.DeviceAssignment;
import com.sitewhere.rest.model.device.DeviceLocation;
import com.sitewhere.rest.model.device.DeviceMeasurements;
import com.sitewhere.rest.model.device.MetadataProvider;
import com.sitewhere.rest.model.device.Zone;
import com.sitewhere.rest.service.search.DeviceAlertSearchResults;
import com.sitewhere.rest.service.search.DeviceAssignmentSearchResults;
import com.sitewhere.rest.service.search.DeviceLocationSearchResults;
import com.sitewhere.rest.service.search.DeviceMeasurementsSearchResults;
import com.sitewhere.rest.service.search.ZoneSearchResults;

/**
 * Interface for SiteWhere client calls.
 * 
 * @author Derek Adams
 */
public interface ISiteWhereClient {

	/**
	 * Get a device by its unique hardware id.
	 * 
	 * @param hardwareId
	 * @return
	 * @throws SiteWhereException
	 */
	public Device getDeviceByHardwareId(String hardwareId) throws SiteWhereException;

	/**
	 * Update the metadata for an existing device.
	 * 
	 * @param hardwareId
	 * @param metadata
	 * @return
	 * @throws SiteWhereException
	 */
	public Device updateDeviceMetadata(String hardwareId, MetadataProvider metadata)
			throws SiteWhereException;

	/**
	 * Get the history of device assignments for a given hardware id.
	 * 
	 * @param hardwareId
	 * @return
	 * @throws SiteWhereException
	 */
	public DeviceAssignmentSearchResults listDeviceAssignmentHistory(String hardwareId)
			throws SiteWhereException;

	/**
	 * Find device assignments within a given distance of the given point.
	 * 
	 * @param latitude
	 * @param longitude
	 * @param maxDistance
	 * @param maxResults
	 * @return
	 * @throws SiteWhereException
	 */
	public DeviceAssignmentSearchResults findDeviceAssignmentsNear(double latitude, double longitude,
			double maxDistance, int maxResults) throws SiteWhereException;

	/**
	 * Update the metadata for an existing device assignment.
	 * 
	 * @param token
	 * @param metadata
	 * @return
	 * @throws SiteWhereException
	 */
	public DeviceAssignment updateDeviceAssignmentMetadata(String token, MetadataProvider metadata)
			throws SiteWhereException;

	/**
	 * Update the location for an existing device assignment.
	 * 
	 * @param token
	 * @param location
	 * @return
	 * @throws SiteWhereException
	 */
	public void updateDeviceAssignmentLocation(String token, DeviceLocation location)
			throws SiteWhereException;

	/**
	 * Create device measurements.
	 * 
	 * @param measurements
	 * @throws SiteWhereException
	 */
	public DeviceMeasurements createDeviceMeasurements(DeviceMeasurements measurements)
			throws SiteWhereException;

	/**
	 * Get most recent device measurements for a given assignment.
	 * 
	 * @param assignmentToken
	 * @param maxCount
	 * @return
	 * @throws SiteWhereException
	 */
	public DeviceMeasurementsSearchResults listDeviceMeasurements(String assignmentToken, int maxCount)
			throws SiteWhereException;

	/**
	 * Create device location.
	 * 
	 * @param location
	 * @throws SiteWhereException
	 */
	public DeviceLocation createDeviceLocation(DeviceLocation location) throws SiteWhereException;

	/**
	 * Get most recent device locations for a given assignment.
	 * 
	 * @param assignmentToken
	 * @param maxCount
	 * @return
	 * @throws SiteWhereException
	 */
	public DeviceLocationSearchResults listDeviceLocations(String assignmentToken, int maxCount)
			throws SiteWhereException;

	/**
	 * Create an alert an associate it with a device location.
	 * 
	 * @param locationId
	 * @param alert
	 * @return
	 * @throws SiteWhereException
	 */
	public DeviceAlert createAlertForDeviceLocation(String locationId, DeviceAlert alert)
			throws SiteWhereException;

	/**
	 * Create device alert.
	 * 
	 * @param alert
	 * @throws SiteWhereException
	 */
	public DeviceAlert createDeviceAlert(DeviceAlert alert) throws SiteWhereException;

	/**
	 * Get most recent device alerts for a given assignment.
	 * 
	 * @param assignmentToken
	 * @param maxCount
	 * @return
	 * @throws SiteWhereException
	 */
	public DeviceAlertSearchResults listDeviceAlerts(String assignmentToken, int maxCount)
			throws SiteWhereException;

	/**
	 * Create a new zone.
	 * 
	 * @param zone
	 * @return
	 * @throws SiteWhereException
	 */
	public Zone createZone(Zone zone) throws SiteWhereException;

	/**
	 * List zones associated with a given site.
	 * 
	 * @param siteToken
	 * @return
	 * @throws SiteWhereException
	 */
	public ZoneSearchResults listZonesForSite(String siteToken) throws SiteWhereException;
}