/*
 * Copyright (c) Reveal Technologies, LLC. All rights reserved. http://www.reveal-tech.com
 *
 * The software in this package is published under the terms of the CPAL v1.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE.txt file.
 */

package com.sitewhere.spi.device;

import java.util.Date;
import java.util.List;

import com.sitewhere.spi.SiteWhereException;
import com.sitewhere.spi.device.request.IDeviceAssignmentCreateRequest;
import com.sitewhere.spi.device.request.IDeviceCreateRequest;
import com.sitewhere.spi.device.request.IDeviceLocationCreateRequest;

/**
 * Interface for device operations.
 * 
 * @author Derek
 */
public interface IDeviceManagement {

	/**
	 * Create a new device.
	 * 
	 * @param device
	 * @return
	 * @throws SiteWhereException
	 */
	public IDevice createDevice(IDeviceCreateRequest device) throws SiteWhereException;

	/**
	 * Gets a device by unique hardware id.
	 * 
	 * @param hardwareId
	 * @return
	 * @throws SiteWhereException
	 */
	public IDevice getDeviceByHardwareId(String hardwareId) throws SiteWhereException;

	/**
	 * Gets the current assignment for a device. Null if none.
	 * 
	 * @param device
	 * @return
	 * @throws SiteWhereException
	 */
	public IDeviceAssignment getCurrentDeviceAssignment(IDevice device) throws SiteWhereException;

	/**
	 * Update metadata associated with a device.
	 * 
	 * @param hardwareId
	 * @param metadata
	 * @return
	 * @throws SiteWhereException
	 */
	public IDevice updateDeviceMetadata(String hardwareId, IMetadataProvider metadata)
			throws SiteWhereException;

	/**
	 * List devices that meet the given criteria.
	 * 
	 * @param criteria
	 * @return
	 * @throws SiteWhereException
	 */
	public List<IDevice> listDevices(IDeviceSearchCriteria criteria) throws SiteWhereException;

	/**
	 * List all devices that are not currently assigned.
	 * 
	 * @return
	 * @throws SiteWhereException
	 */
	public List<IDevice> listUnassignedDevices() throws SiteWhereException;

	/**
	 * Delete an existing device.
	 * 
	 * @param hardwareId
	 * @return
	 * @throws SiteWhereException
	 */
	public IDevice deleteDevice(String hardwareId) throws SiteWhereException;

	/**
	 * Create a new device assignment.
	 * 
	 * @param request
	 * @return
	 * @throws SiteWhereException
	 */
	public IDeviceAssignment createDeviceAssignment(IDeviceAssignmentCreateRequest request)
			throws SiteWhereException;

	/**
	 * Get the device associated with an assignment.
	 * 
	 * @param assignment
	 * @return
	 * @throws SiteWhereException
	 */
	public IDevice getDeviceForAssignment(IDeviceAssignment assignment) throws SiteWhereException;

	/**
	 * Get the site associated with an assignment.
	 * 
	 * @param assignment
	 * @return
	 * @throws SiteWhereException
	 */
	public ISite getSiteForAssignment(IDeviceAssignment assignment) throws SiteWhereException;

	/**
	 * Update metadata associated with a device assignment.
	 * 
	 * @param token
	 * @param metadata
	 * @return
	 * @throws SiteWhereException
	 */
	public IDeviceAssignment updateDeviceAssignmentMetadata(String token, IMetadataProvider metadata)
			throws SiteWhereException;

	/**
	 * Update the status of an existing device assignment.
	 * 
	 * @param token
	 * @param status
	 * @return
	 * @throws SiteWhereException
	 */
	public IDeviceAssignment updateDeviceAssignmentStatus(String token, DeviceAssignmentStatus status)
			throws SiteWhereException;

	/**
	 * Updates the current location of a device assignment.
	 * 
	 * @param token
	 * @param location
	 * @return
	 * @throws SiteWhereException
	 */
	public IDeviceAssignment updateDeviceAssignmentLocation(String token, IDeviceLocation location)
			throws SiteWhereException;

	/**
	 * Add a batch of events for the given assignment.
	 * 
	 * @param assignmentToken
	 * @param batch
	 * @throws SiteWhereException
	 */
	public void addDeviceEventBatch(String assignmentToken, IDeviceEventBatch batch)
			throws SiteWhereException;

	/**
	 * Ends a device assignment.
	 * 
	 * @param token
	 * @return
	 * @throws SiteWhereException
	 */
	public IDeviceAssignment endDeviceAssignment(String token) throws SiteWhereException;

	/**
	 * Get the device assignment history for a given device.
	 * 
	 * @param hardwareId
	 * @return
	 * @throws SiteWhereException
	 */
	public List<IDeviceAssignment> getDeviceAssignmentHistory(String hardwareId) throws SiteWhereException;

	/**
	 * Get a device assignment by unique token.
	 * 
	 * @param token
	 * @return
	 * @throws SiteWhereException
	 */
	public IDeviceAssignment getDeviceAssignmentByToken(String token) throws SiteWhereException;

	/**
	 * Get a list of device assignments for a site.
	 * 
	 * @param siteToken
	 * @return
	 * @throws SiteWhereException
	 */
	public List<IDeviceAssignment> getDeviceAssignmentsForSite(String siteToken) throws SiteWhereException;

	/**
	 * Find assignments within a given distance of a location.
	 * 
	 * @param latitude
	 * @param longitude
	 * @param maxDistance
	 * @param maxResults
	 * @return
	 * @throws SiteWhereException
	 */
	public List<IDeviceAssignment> getDeviceAssignmentsNear(double latitude, double longitude,
			double maxDistance, int maxResults) throws SiteWhereException;

	/**
	 * Add measurements for a given device assignment.
	 * 
	 * @param measurements
	 * @return
	 * @throws SiteWhereException
	 */
	public IDeviceMeasurements addDeviceMeasurements(IDeviceMeasurements measurements)
			throws SiteWhereException;

	/**
	 * Gets the most recent device measurement entries for an assignment.
	 * 
	 * @param assignmentToken
	 * @param maxCount
	 * @return
	 * @throws SiteWhereException
	 */
	public List<IDeviceMeasurements> listDeviceMeasurements(String assignmentToken, int maxCount)
			throws SiteWhereException;

	/**
	 * List device measurements for a site.
	 * 
	 * @param siteToken
	 * @param maxCount
	 * @return
	 * @throws SiteWhereException
	 */
	public List<IDeviceMeasurements> listDeviceMeasurementsForSite(String siteToken, int maxCount)
			throws SiteWhereException;

	/**
	 * Adds an alert which is associated with a given set of measurements.
	 * 
	 * @param measurementsId
	 * @param alert
	 * @return
	 * @throws SiteWhereException
	 */
	public IDeviceAlert addAlertForMeasurements(String measurementsId, IDeviceAlert alert)
			throws SiteWhereException;

	/**
	 * Add location for a given device assignment.
	 * 
	 * @param assignment
	 * @param request
	 * @return
	 * @throws SiteWhereException
	 */
	public IDeviceLocation addDeviceLocation(IDeviceAssignment assignment,
			IDeviceLocationCreateRequest request) throws SiteWhereException;

	/**
	 * Gets the most recent device location entries for an assignment.
	 * 
	 * @param assignmentToken
	 * @param maxCount
	 * @return
	 * @throws SiteWhereException
	 */
	public List<IDeviceLocation> listDeviceLocations(String assignmentToken, int maxCount)
			throws SiteWhereException;

	/**
	 * List device locations for a site.
	 * 
	 * @param siteToken
	 * @param maxCount
	 * @return
	 * @throws SiteWhereException
	 */
	public List<IDeviceLocation> listDeviceLocationsForSite(String siteToken, int maxCount)
			throws SiteWhereException;

	/**
	 * List device locations for the given tokens within the given time range.
	 * 
	 * @param assignmentTokens
	 * @param start
	 * @param end
	 * @return
	 * @throws SiteWhereException
	 */
	public List<IDeviceLocation> listDeviceLocations(List<String> assignmentTokens, Date start, Date end)
			throws SiteWhereException;

	/**
	 * Adds an alert which is associated with a given location.
	 * 
	 * @param locationId
	 * @param alert
	 * @return
	 * @throws SiteWhereException
	 */
	public IDeviceAlert addAlertForLocation(String locationId, IDeviceAlert alert) throws SiteWhereException;

	/**
	 * Add alert for a given device assignment.
	 * 
	 * @param alert
	 * @return
	 * @throws SiteWhereException
	 */
	public IDeviceAlert addDeviceAlert(IDeviceAlert alert) throws SiteWhereException;

	/**
	 * Gets the most recent device alert entries for an assignment.
	 * 
	 * @param assignmentToken
	 * @param maxCount
	 * @return
	 * @throws SiteWhereException
	 */
	public List<IDeviceAlert> listDeviceAlerts(String assignmentToken, int maxCount)
			throws SiteWhereException;

	/**
	 * List device alerts for a site.
	 * 
	 * @param siteToken
	 * @param maxCount
	 * @return
	 * @throws SiteWhereException
	 */
	public List<IDeviceAlert> listDeviceAlertsForSite(String siteToken, int maxCount)
			throws SiteWhereException;

	/**
	 * Create a site based on the given input.
	 * 
	 * @param input
	 * @return
	 * @throws SiteWhereException
	 */
	public ISite createSite(ISite input) throws SiteWhereException;

	/**
	 * Update information for a site.
	 * 
	 * @param input
	 * @return
	 * @throws SiteWhereException
	 */
	public ISite updateSite(ISite input) throws SiteWhereException;

	/**
	 * Get a site by unique token.
	 * 
	 * @param token
	 * @return
	 * @throws SiteWhereException
	 */
	public ISite getSiteByToken(String token) throws SiteWhereException;

	/**
	 * Get a list of all sites.
	 * 
	 * @return
	 * @throws SiteWhereException
	 */
	public List<ISite> listSites() throws SiteWhereException;

	/**
	 * Create a new zone.
	 * 
	 * @param input
	 * @return
	 * @throws SiteWhereException
	 */
	public IZone createZone(IZone input) throws SiteWhereException;

	/**
	 * Get a zone by its unique token.
	 * 
	 * @param zoneToken
	 * @return
	 * @throws SiteWhereException
	 */
	public IZone getZone(String zoneToken) throws SiteWhereException;

	/**
	 * Get a list of all zones associated with a Site.
	 * 
	 * @param siteToken
	 * @return
	 * @throws SiteWhereException
	 */
	public List<IZone> listZones(String siteToken) throws SiteWhereException;

	/**
	 * Delete a zone given its unique token.
	 * 
	 * @param zoneToken
	 * @return
	 * @throws SiteWhereException
	 */
	public IZone deleteZone(String zoneToken) throws SiteWhereException;
}