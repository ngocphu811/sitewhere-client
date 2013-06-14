/*
* $Id$
* --------------------------------------------------------------------------------------
* Copyright (c) Reveal Technologies, LLC. All rights reserved. http://www.reveal-tech.com
*
* The software in this package is published under the terms of the CPAL v1.0
* license, a copy of which has been included with this distribution in the
* LICENSE.txt file.
*/

package com.sitewhere.rest.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJacksonHttpMessageConverter;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

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
import com.sitewhere.spi.SiteWhereException;

/**
 * Client for interacting with SiteWhere REST services.
 * 
 * @author dadams
 */
public class SiteWhereClient {

	/** Default base url for calling REST services */
	private static final String DEFAULT_BASE_URL = "http://localhost:8080/sitewhere/api/";

	/** Use CXF web client to send requests */
	private RestTemplate client;

	/** Base URL used for REST calls */
	private String baseUrl = DEFAULT_BASE_URL;

	public SiteWhereClient() {
		this(DEFAULT_BASE_URL);
	}

	public SiteWhereClient(String url) {
		this.client = new RestTemplate();
		List<HttpMessageConverter<?>> converters = new ArrayList<HttpMessageConverter<?>>();
		converters.add(new MappingJacksonHttpMessageConverter());
		client.setMessageConverters(converters);
		this.baseUrl = url;
	}

	/**
	 * Get a device by its unique hardware id.
	 * 
	 * @param hardwareId
	 * @return
	 * @throws SiteWhereException
	 */
	public Device getDeviceByHardwareId(String hardwareId) throws SiteWhereException {
		try {
			Map<String, String> vars = new HashMap<String, String>();
			vars.put("hardwareId", hardwareId);
			return getClient().getForObject(getBaseUrl() + "devices/{hardwareId}", Device.class, vars);
		} catch (HttpClientErrorException e) {
			HttpStatus status = e.getStatusCode();
			if (HttpStatus.NOT_FOUND == status) {
				return null;
			}
			throw new SiteWhereException(e);
		}
	}

	/**
	 * Update the metadata for an existing device.
	 * 
	 * @param hardwareId
	 * @param metadata
	 * @return
	 * @throws SiteWhereException
	 */
	public Device updateDeviceMetadata(String hardwareId, MetadataProvider metadata)
			throws SiteWhereException {
		MetadataProvider update = new MetadataProvider();
		MetadataProvider.copy(metadata, update);
		try {
			Map<String, String> vars = new HashMap<String, String>();
			vars.put("hardwareId", hardwareId);
			return getClient().postForObject(getBaseUrl() + "devices/{hardwareId}/metadata", update,
					Device.class, vars);
		} catch (RestClientException e) {
			throw new SiteWhereException(e);
		}
	}

	/**
	 * Get the history of device assignments for a given hardware id.
	 * 
	 * @param hardwareId
	 * @return
	 * @throws SiteWhereException
	 */
	public DeviceAssignmentSearchResults listDeviceAssignmentHistory(String hardwareId)
			throws SiteWhereException {
		try {
			Map<String, String> vars = new HashMap<String, String>();
			vars.put("hardwareId", hardwareId);
			return getClient().getForObject(getBaseUrl() + "devices/{hardwareId}/assignments",
					DeviceAssignmentSearchResults.class, vars);
		} catch (RestClientException e) {
			throw new SiteWhereException(e);
		}
	}

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
			double maxDistance, int maxResults) throws SiteWhereException {
		try {
			Map<String, String> vars = new HashMap<String, String>();
			vars.put("latitude", String.valueOf(latitude));
			vars.put("longitude", String.valueOf(longitude));
			vars.put("maxDistance", String.valueOf(maxDistance));
			vars.put("maxResults", String.valueOf(maxResults));
			return getClient().getForObject(
					getBaseUrl() + "assignments/near/{latitude}/{longitude}"
							+ "?maxDistance={maxDistance}&maxResults={maxResults}",
					DeviceAssignmentSearchResults.class, vars);
		} catch (RestClientException e) {
			throw new SiteWhereException(e);
		}
	}

	/**
	 * Update the metadata for an existing device assignment.
	 * 
	 * @param token
	 * @param metadata
	 * @return
	 * @throws SiteWhereException
	 */
	public DeviceAssignment updateDeviceAssignmentMetadata(String token, MetadataProvider metadata)
			throws SiteWhereException {
		MetadataProvider update = new MetadataProvider();
		MetadataProvider.copy(metadata, update);
		try {
			Map<String, String> vars = new HashMap<String, String>();
			vars.put("token", token);
			return getClient().postForObject(getBaseUrl() + "assignments/{token}/metadata", update,
					DeviceAssignment.class, vars);
		} catch (RestClientException e) {
			throw new SiteWhereException(e);
		}
	}

	/**
	 * Update the location for an existing device assignment.
	 * 
	 * @param token
	 * @param location
	 * @return
	 * @throws SiteWhereException
	 */
	public void updateDeviceAssignmentLocation(String token, DeviceLocation location)
			throws SiteWhereException {
		try {
			Map<String, String> vars = new HashMap<String, String>();
			vars.put("token", token);
			getClient().put(getBaseUrl() + "assignments/{token}/location", location, vars);
		} catch (RestClientException e) {
			throw new SiteWhereException(e);
		}
	}

	/**
	 * Create device measurements.
	 * 
	 * @param measurements
	 * @throws SiteWhereException
	 */
	public DeviceMeasurements createDeviceMeasurements(DeviceMeasurements measurements)
			throws SiteWhereException {
		try {
			Map<String, String> vars = new HashMap<String, String>();
			vars.put("token", measurements.getDeviceAssignmentToken());
			return getClient().postForObject(getBaseUrl() + "assignments/{token}/measurements", measurements,
					DeviceMeasurements.class, vars);
		} catch (RestClientException e) {
			throw new SiteWhereException(e);
		}
	}

	/**
	 * Get most recent device measurements for a given assignment.
	 * 
	 * @param assignmentToken
	 * @param maxCount
	 * @return
	 * @throws SiteWhereException
	 */
	public DeviceMeasurementsSearchResults listDeviceMeasurements(String assignmentToken, int maxCount)
			throws SiteWhereException {
		try {
			Map<String, String> vars = new HashMap<String, String>();
			vars.put("token", assignmentToken);
			vars.put("count", String.valueOf(maxCount));
			return getClient().getForObject(getBaseUrl() + "assignments/{token}/measurements?count={count}",
					DeviceMeasurementsSearchResults.class, vars);
		} catch (RestClientException e) {
			throw new SiteWhereException(e);
		}
	}

	/**
	 * Create device location.
	 * 
	 * @param location
	 * @throws SiteWhereException
	 */
	public DeviceLocation createDeviceLocation(DeviceLocation location) throws SiteWhereException {
		try {
			Map<String, String> vars = new HashMap<String, String>();
			vars.put("token", location.getDeviceAssignmentToken());
			DeviceLocation result = getClient().postForObject(getBaseUrl() + "assignments/{token}/locations",
					location, DeviceLocation.class, vars);
			return result;
		} catch (RestClientException e) {
			throw new SiteWhereException(e);
		}
	}

	/**
	 * Get most recent device locations for a given assignment.
	 * 
	 * @param assignmentToken
	 * @param maxCount
	 * @return
	 * @throws SiteWhereException
	 */
	public DeviceLocationSearchResults listDeviceLocations(String assignmentToken, int maxCount)
			throws SiteWhereException {
		try {
			Map<String, String> vars = new HashMap<String, String>();
			vars.put("token", assignmentToken);
			vars.put("count", String.valueOf(maxCount));
			return getClient().getForObject(getBaseUrl() + "assignments/{token}/locations?count={count}",
					DeviceLocationSearchResults.class, vars);
		} catch (RestClientException e) {
			throw new SiteWhereException(e);
		}
	}

	/**
	 * Create an alert an associate it with a device location.
	 * 
	 * @param locationId
	 * @param alert
	 * @return
	 * @throws SiteWhereException
	 */
	public DeviceAlert createAlertForDeviceLocation(String locationId, DeviceAlert alert)
			throws SiteWhereException {
		try {
			Map<String, String> vars = new HashMap<String, String>();
			vars.put("locationId", locationId);
			DeviceAlert result = getClient().postForObject(getBaseUrl() + "locations/{locationId}/alerts",
					alert, DeviceAlert.class, vars);
			return result;
		} catch (RestClientException e) {
			throw new SiteWhereException(e);
		}
	}

	/**
	 * Create device alert.
	 * 
	 * @param alert
	 * @throws SiteWhereException
	 */
	public DeviceAlert createDeviceAlert(DeviceAlert alert) throws SiteWhereException {
		try {
			Map<String, String> vars = new HashMap<String, String>();
			vars.put("token", alert.getDeviceAssignmentToken());
			DeviceAlert result = getClient().postForObject(getBaseUrl() + "assignments/{token}/alerts",
					alert, DeviceAlert.class, vars);
			return result;
		} catch (RestClientException e) {
			throw new SiteWhereException(e);
		}
	}

	/**
	 * Get most recent device alerts for a given assignment.
	 * 
	 * @param assignmentToken
	 * @param maxCount
	 * @return
	 * @throws SiteWhereException
	 */
	public DeviceAlertSearchResults listDeviceAlerts(String assignmentToken, int maxCount)
			throws SiteWhereException {
		try {
			Map<String, String> vars = new HashMap<String, String>();
			vars.put("token", assignmentToken);
			vars.put("count", String.valueOf(maxCount));
			return getClient().getForObject(getBaseUrl() + "assignments/{token}/alerts?count={count}",
					DeviceAlertSearchResults.class, vars);
		} catch (RestClientException e) {
			throw new SiteWhereException(e);
		}
	}

	/**
	 * Create a new zone.
	 * 
	 * @param zone
	 * @return
	 * @throws SiteWhereException
	 */
	public Zone createZone(Zone zone) throws SiteWhereException {
		try {
			return getClient().postForObject(getBaseUrl() + "zones", zone, Zone.class);
		} catch (RestClientException e) {
			throw new SiteWhereException(e);
		}
	}

	/**
	 * List zones associated with a given site.
	 * 
	 * @param siteToken
	 * @return
	 * @throws SiteWhereException
	 */
	public ZoneSearchResults listZonesForSite(String siteToken) throws SiteWhereException {
		try {
			Map<String, String> vars = new HashMap<String, String>();
			vars.put("siteToken", siteToken);
			return getClient().getForObject(getBaseUrl() + "sites/{siteToken}/zones", ZoneSearchResults.class,
					vars);
		} catch (RestClientException e) {
			throw new SiteWhereException(e);
		}
	}

	public RestTemplate getClient() {
		return client;
	}

	public void setClient(RestTemplate client) {
		this.client = client;
	}

	public String getBaseUrl() {
		return baseUrl;
	}

	public void setBaseUrl(String baseUrl) {
		this.baseUrl = baseUrl;
	}
}