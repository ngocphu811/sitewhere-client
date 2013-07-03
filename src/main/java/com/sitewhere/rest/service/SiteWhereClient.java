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
import com.sitewhere.spi.ISiteWhereClient;
import com.sitewhere.spi.SiteWhereException;

/**
 * Client for interacting with SiteWhere REST services.
 * 
 * @author dadams
 */
public class SiteWhereClient implements ISiteWhereClient {

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

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.sitewhere.spi.ISiteWhereClient#getDeviceByHardwareId(java.lang.String)
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.sitewhere.spi.ISiteWhereClient#updateDeviceMetadata(java.lang.String,
	 * com.sitewhere.rest.model.device.MetadataProvider)
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.sitewhere.spi.ISiteWhereClient#listDeviceAssignmentHistory(java.lang.String)
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.sitewhere.spi.ISiteWhereClient#findDeviceAssignmentsNear(double, double, double, int)
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.sitewhere.spi.ISiteWhereClient#updateDeviceAssignmentMetadata(java.lang.String,
	 * com.sitewhere.rest.model.device.MetadataProvider)
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.sitewhere.spi.ISiteWhereClient#updateDeviceAssignmentLocation(java.lang.String,
	 * com.sitewhere.rest.model.device.DeviceLocation)
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.sitewhere.spi.ISiteWhereClient#createDeviceMeasurements(com.sitewhere.rest.model.device.
	 * DeviceMeasurements)
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.sitewhere.spi.ISiteWhereClient#listDeviceMeasurements(java.lang.String, int)
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.sitewhere.spi.ISiteWhereClient#createDeviceLocation(com.sitewhere.rest.model.device.DeviceLocation)
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.sitewhere.spi.ISiteWhereClient#listDeviceLocations(java.lang.String, int)
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.sitewhere.spi.ISiteWhereClient#createAlertForDeviceLocation(java.lang.String,
	 * com.sitewhere.rest.model.device.DeviceAlert)
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.sitewhere.spi.ISiteWhereClient#createDeviceAlert(com.sitewhere.rest.model.device.DeviceAlert)
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.sitewhere.spi.ISiteWhereClient#listDeviceAlerts(java.lang.String, int)
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.sitewhere.spi.ISiteWhereClient#createZone(com.sitewhere.rest.model.device.Zone)
	 */
	public Zone createZone(Zone zone) throws SiteWhereException {
		try {
			return getClient().postForObject(getBaseUrl() + "zones", zone, Zone.class);
		} catch (RestClientException e) {
			throw new SiteWhereException(e);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.sitewhere.spi.ISiteWhereClient#listZonesForSite(java.lang.String)
	 */
	public ZoneSearchResults listZonesForSite(String siteToken) throws SiteWhereException {
		try {
			Map<String, String> vars = new HashMap<String, String>();
			vars.put("siteToken", siteToken);
			return getClient().getForObject(getBaseUrl() + "sites/{siteToken}/zones",
					ZoneSearchResults.class, vars);
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