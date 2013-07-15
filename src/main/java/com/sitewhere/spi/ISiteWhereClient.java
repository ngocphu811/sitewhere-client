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
import com.sitewhere.rest.model.device.DeviceEventBatch;
import com.sitewhere.rest.model.device.DeviceEventBatchResponse;
import com.sitewhere.rest.model.device.DeviceLocation;
import com.sitewhere.rest.model.device.DeviceMeasurements;
import com.sitewhere.rest.model.device.MetadataProvider;
import com.sitewhere.rest.model.device.Zone;
import com.sitewhere.rest.model.device.request.DeviceAlertCreateRequest;
import com.sitewhere.rest.model.device.request.DeviceCreateRequest;
import com.sitewhere.rest.model.device.request.DeviceLocationCreateRequest;
import com.sitewhere.rest.model.device.request.DeviceMeasurementsCreateRequest;
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
	 * Create a new device.
	 * 
	 * @param request
	 *            information about device to be created
	 * @return the created device
	 * @throws SiteWhereException
	 */
	public Device createDevice(DeviceCreateRequest request) throws SiteWhereException;

	/**
	 * Get a device by its unique hardware id.
	 * 
	 * @param hardwareId
	 *            hardware id of device to return
	 * @return device if found or null if not
	 * @throws SiteWhereException
	 */
	public Device getDeviceByHardwareId(String hardwareId) throws SiteWhereException;

	/**
	 * Delete a device.
	 * 
	 * @param hardwareId
	 *            hardware id of device to delete
	 * @param force
	 *            if true, data is deleted. if false, delete flag is set to true
	 * @throws SiteWhereException
	 */
	public void deleteDevice(String hardwareId, boolean force) throws SiteWhereException;

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
	 * Add a batch of events to the current assignment for the given hardware id.
	 * 
	 * @param hardwareId
	 * @param batch
	 * @return
	 * @throws SiteWhereException
	 */
	public DeviceEventBatchResponse addDeviceEventBatch(String hardwareId, DeviceEventBatch batch)
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
	 * Create measurements for an assignment.
	 * 
	 * @param assignmentToken
	 * @param measurements
	 * @return
	 * @throws SiteWhereException
	 */
	public DeviceMeasurements createDeviceMeasurements(String assignmentToken,
			DeviceMeasurementsCreateRequest measurements) throws SiteWhereException;

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
	 * Create a new device location for an assignment.
	 * 
	 * @param assignmentToken
	 * @param request
	 * @return
	 * @throws SiteWhereException
	 */
	public DeviceLocation createDeviceLocation(String assignmentToken, DeviceLocationCreateRequest request)
			throws SiteWhereException;

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
	 * Associates an alert with a device location.
	 * 
	 * @param alertId
	 * @param locationId
	 * @throws SiteWhereException
	 */
	public void associateAlertWithDeviceLocation(String alertId, String locationId) throws SiteWhereException;

	/**
	 * Create a new alert for a device assignment.
	 * 
	 * @param assignmentToken
	 * @param request
	 * @return
	 * @throws SiteWhereException
	 */
	public DeviceAlert createDeviceAlert(String assignmentToken, DeviceAlertCreateRequest request)
			throws SiteWhereException;

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