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

import com.sitewhere.rest.service.search.SearchResults;
import com.sitewhere.spi.SiteWhereException;
import com.sitewhere.spi.common.IMetadataProvider;
import com.sitewhere.spi.common.ISearchCriteria;
import com.sitewhere.spi.device.request.IDeviceAlertCreateRequest;
import com.sitewhere.spi.device.request.IDeviceAssignmentCreateRequest;
import com.sitewhere.spi.device.request.IDeviceCreateRequest;
import com.sitewhere.spi.device.request.IDeviceLocationCreateRequest;
import com.sitewhere.spi.device.request.IDeviceMeasurementsCreateRequest;
import com.sitewhere.spi.device.request.ISiteCreateRequest;
import com.sitewhere.spi.device.request.IZoneCreateRequest;

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
	 * Update device information.
	 * 
	 * @param hardwareId
	 * @param request
	 * @return
	 * @throws SiteWhereException
	 */
	public IDevice updateDevice(String hardwareId, IDeviceCreateRequest request) throws SiteWhereException;

	/**
	 * Gets the current assignment for a device. Null if none.
	 * 
	 * @param device
	 * @return
	 * @throws SiteWhereException
	 */
	public IDeviceAssignment getCurrentDeviceAssignment(IDevice device) throws SiteWhereException;

	/**
	 * List devices that meet the given criteria.
	 * 
	 * @param includeDeleted
	 * @param criteria
	 * @return
	 * @throws SiteWhereException
	 */
	public SearchResults<IDevice> listDevices(boolean includeDeleted, ISearchCriteria criteria)
			throws SiteWhereException;

	/**
	 * List all devices that are not currently assigned.
	 * 
	 * @param criteria
	 * @return
	 * @throws SiteWhereException
	 */
	public SearchResults<IDevice> listUnassignedDevices(ISearchCriteria criteria) throws SiteWhereException;

	/**
	 * Delete an existing device.
	 * 
	 * @param hardwareId
	 * @param force
	 * @return
	 * @throws SiteWhereException
	 */
	public IDevice deleteDevice(String hardwareId, boolean force) throws SiteWhereException;

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
	 * Get a device assignment by unique token.
	 * 
	 * @param token
	 * @return
	 * @throws SiteWhereException
	 */
	public IDeviceAssignment getDeviceAssignmentByToken(String token) throws SiteWhereException;

	/**
	 * Delete a device assignment. Depending on 'force' flag the assignment will be marked
	 * for delete or actually be deleted.
	 * 
	 * @param token
	 * @param force
	 * @return
	 * @throws SiteWhereException
	 */
	public IDeviceAssignment deleteDeviceAssignment(String token, boolean force) throws SiteWhereException;

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
	 * @param locationId
	 * @return
	 * @throws SiteWhereException
	 */
	public IDeviceAssignment updateDeviceAssignmentLocation(String token, String locationId)
			throws SiteWhereException;

	/**
	 * Add a batch of events for the given assignment.
	 * 
	 * @param assignmentToken
	 * @param batch
	 * @return
	 * @throws SiteWhereException
	 */
	public IDeviceEventBatchResponse addDeviceEventBatch(String assignmentToken, IDeviceEventBatch batch)
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
	 * @param criteria
	 * @return
	 * @throws SiteWhereException
	 */
	public SearchResults<IDeviceAssignment> getDeviceAssignmentHistory(String hardwareId,
			ISearchCriteria criteria) throws SiteWhereException;

	/**
	 * Get a list of device assignments for a site.
	 * 
	 * @param siteToken
	 * @param criteria
	 * @return
	 * @throws SiteWhereException
	 */
	public SearchResults<IDeviceAssignment> getDeviceAssignmentsForSite(String siteToken,
			ISearchCriteria criteria) throws SiteWhereException;

	/**
	 * Find assignments within a given distance of a location.
	 * 
	 * @param latitude
	 * @param longitude
	 * @param maxDistance
	 * @param criteria
	 * @return
	 * @throws SiteWhereException
	 */
	public SearchResults<IDeviceAssignment> getDeviceAssignmentsNear(double latitude, double longitude,
			double maxDistance, ISearchCriteria criteria) throws SiteWhereException;

	/**
	 * Add measurements for a given device assignment.
	 * 
	 * @param assignment
	 * @param measurements
	 * @return
	 * @throws SiteWhereException
	 */
	public IDeviceMeasurements addDeviceMeasurements(IDeviceAssignment assignment,
			IDeviceMeasurementsCreateRequest measurements) throws SiteWhereException;

	/**
	 * Gets device measurement entries for an assignment based on criteria.
	 * 
	 * @param siteToken
	 * @param criteria
	 * @return
	 * @throws SiteWhereException
	 */
	public SearchResults<IDeviceMeasurements> listDeviceMeasurements(String siteToken,
			ISearchCriteria criteria) throws SiteWhereException;

	/**
	 * List device measurements for a site.
	 * 
	 * @param siteToken
	 * @param criteria
	 * @return
	 * @throws SiteWhereException
	 */
	public SearchResults<IDeviceMeasurements> listDeviceMeasurementsForSite(String siteToken,
			ISearchCriteria criteria) throws SiteWhereException;

	/**
	 * Associates an alert with measurements.
	 * 
	 * @param alertId
	 * @param measurementsId
	 * @throws SiteWhereException
	 */
	public void associateAlertWithMeasurements(String alertId, String measurementsId)
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
	 * Gets device location entries for an assignment.
	 * 
	 * @param assignmentToken
	 * @param criteria
	 * @return
	 * @throws SiteWhereException
	 */
	public SearchResults<IDeviceLocation> listDeviceLocations(String assignmentToken, ISearchCriteria criteria)
			throws SiteWhereException;

	/**
	 * List device locations for a site.
	 * 
	 * @param siteToken
	 * @param criteria
	 * @return
	 * @throws SiteWhereException
	 */
	public SearchResults<IDeviceLocation> listDeviceLocationsForSite(String siteToken,
			ISearchCriteria criteria) throws SiteWhereException;

	/**
	 * List device locations for the given tokens within the given time range.
	 * 
	 * @param assignmentTokens
	 * @param start
	 * @param end
	 * @param criteria
	 * @return
	 * @throws SiteWhereException
	 */
	public SearchResults<IDeviceLocation> listDeviceLocations(List<String> assignmentTokens, Date start,
			Date end, ISearchCriteria criteria) throws SiteWhereException;

	/**
	 * Associates an alert with a device location.
	 * 
	 * @param alertId
	 *            unique identifier for alert
	 * @param locationId
	 *            unique identifier for location
	 * @return the updated device location
	 * @throws SiteWhereException
	 */
	public IDeviceLocation associateAlertWithLocation(String alertId, String locationId)
			throws SiteWhereException;

	/**
	 * Add alert for a given device assignment.
	 * 
	 * @param assignment
	 * @param request
	 * @return
	 * @throws SiteWhereException
	 */
	public IDeviceAlert addDeviceAlert(IDeviceAssignment assignment, IDeviceAlertCreateRequest request)
			throws SiteWhereException;

	/**
	 * Gets the most recent device alert entries for an assignment.
	 * 
	 * @param assignmentToken
	 * @param criteria
	 * @return
	 * @throws SiteWhereException
	 */
	public SearchResults<IDeviceAlert> listDeviceAlerts(String assignmentToken, ISearchCriteria criteria)
			throws SiteWhereException;

	/**
	 * List device alerts for a site.
	 * 
	 * @param siteToken
	 * @param criteria
	 * @return
	 * @throws SiteWhereException
	 */
	public SearchResults<IDeviceAlert> listDeviceAlertsForSite(String siteToken, ISearchCriteria criteria)
			throws SiteWhereException;

	/**
	 * Create a site based on the given input.
	 * 
	 * @param request
	 * @return
	 * @throws SiteWhereException
	 */
	public ISite createSite(ISiteCreateRequest request) throws SiteWhereException;

	/**
	 * Delete a site based on unique site token. If 'force' is specified, the database
	 * object will be deleted, otherwise the deleted flag will be set to true.
	 * 
	 * @param siteToken
	 * @param force
	 * @return
	 * @throws SiteWhereException
	 */
	public ISite deleteSite(String siteToken, boolean force) throws SiteWhereException;

	/**
	 * Update information for a site.
	 * 
	 * @param siteToken
	 * @param request
	 * @return
	 * @throws SiteWhereException
	 */
	public ISite updateSite(String siteToken, ISiteCreateRequest request) throws SiteWhereException;

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
	 * @param criteria
	 * @return
	 * @throws SiteWhereException
	 */
	public SearchResults<ISite> listSites(ISearchCriteria criteria) throws SiteWhereException;

	/**
	 * Create a new zone.
	 * 
	 * @param site
	 * @param request
	 * @return
	 * @throws SiteWhereException
	 */
	public IZone createZone(ISite site, IZoneCreateRequest request) throws SiteWhereException;

	/**
	 * Update an existing zone.
	 * 
	 * @param token
	 * @param request
	 * @return
	 * @throws SiteWhereException
	 */
	public IZone updateZone(String token, IZoneCreateRequest request) throws SiteWhereException;

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
	 * @param criteria
	 * @return
	 * @throws SiteWhereException
	 */
	public SearchResults<IZone> listZones(String siteToken, ISearchCriteria criteria)
			throws SiteWhereException;

	/**
	 * Delete a zone given its unique token.
	 * 
	 * @param zoneToken
	 * @param force
	 * @return
	 * @throws SiteWhereException
	 */
	public IZone deleteZone(String zoneToken, boolean force) throws SiteWhereException;
}