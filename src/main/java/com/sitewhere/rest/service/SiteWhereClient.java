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

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;

import com.sitewhere.rest.model.common.MetadataProvider;
import com.sitewhere.rest.model.device.Device;
import com.sitewhere.rest.model.device.DeviceAlert;
import com.sitewhere.rest.model.device.DeviceAssignment;
import com.sitewhere.rest.model.device.DeviceEventBatch;
import com.sitewhere.rest.model.device.DeviceEventBatchResponse;
import com.sitewhere.rest.model.device.DeviceLocation;
import com.sitewhere.rest.model.device.DeviceMeasurements;
import com.sitewhere.rest.model.device.Zone;
import com.sitewhere.rest.model.device.request.DeviceAlertCreateRequest;
import com.sitewhere.rest.model.device.request.DeviceCreateRequest;
import com.sitewhere.rest.model.device.request.DeviceLocationCreateRequest;
import com.sitewhere.rest.model.device.request.DeviceMeasurementsCreateRequest;
import com.sitewhere.rest.model.device.request.ZoneCreateRequest;
import com.sitewhere.rest.service.search.DeviceAlertSearchResults;
import com.sitewhere.rest.service.search.DeviceAssignmentSearchResults;
import com.sitewhere.rest.service.search.DeviceLocationSearchResults;
import com.sitewhere.rest.service.search.DeviceMeasurementsSearchResults;
import com.sitewhere.rest.service.search.SearchResults;
import com.sitewhere.rest.service.search.ZoneSearchResults;
import com.sitewhere.rest.spring.MappingJackson2HttpMessageConverter;
import com.sitewhere.spi.ISiteWhereClient;
import com.sitewhere.spi.SiteWhereException;
import com.sitewhere.spi.SiteWhereSystemException;
import com.sitewhere.spi.device.request.IDeviceAssignmentCreateRequest;

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
		converters.add(new MappingJackson2HttpMessageConverter());
		client.setMessageConverters(converters);
		client.setErrorHandler(new SiteWhereErrorHandler());
		this.baseUrl = url;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.sitewhere.spi.ISiteWhereClient#createDevice(com.sitewhere.rest.model.device
	 * .request.DeviceCreateRequest )
	 */
	public Device createDevice(DeviceCreateRequest request) throws SiteWhereException {
		Map<String, String> vars = new HashMap<String, String>();
		return sendRest(getBaseUrl() + "devices", HttpMethod.POST, request, Device.class, vars);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.sitewhere.spi.ISiteWhereClient#getDeviceByHardwareId(java.lang.String)
	 */
	public Device getDeviceByHardwareId(String hardwareId) throws SiteWhereException {
		Map<String, String> vars = new HashMap<String, String>();
		vars.put("hardwareId", hardwareId);
		return sendRest(getBaseUrl() + "devices/{hardwareId}", HttpMethod.GET, null, Device.class, vars);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.sitewhere.spi.ISiteWhereClient#updateDevice(java.lang.String,
	 * com.sitewhere.rest.model.device.request.DeviceCreateRequest)
	 */
	public Device updateDevice(String hardwareId, DeviceCreateRequest request) throws SiteWhereException {
		Map<String, String> vars = new HashMap<String, String>();
		vars.put("hardwareId", hardwareId);
		return sendRest(getBaseUrl() + "devices/{hardwareId}", HttpMethod.PUT, request, Device.class, vars);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.sitewhere.spi.ISiteWhereClient#deleteDevice(java.lang.String, boolean)
	 */
	public Device deleteDevice(String hardwareId, boolean force) throws SiteWhereException {
		Map<String, String> vars = new HashMap<String, String>();
		vars.put("hardwareId", hardwareId);
		String url = getBaseUrl() + "devices/{hardwareId}";
		if (force) {
			url += "?force=true";
		}
		return sendRest(url, HttpMethod.DELETE, null, Device.class, vars);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.sitewhere.spi.ISiteWhereClient#getCurrentAssignmentForDevice(java.lang.String)
	 */
	public DeviceAssignment getCurrentAssignmentForDevice(String hardwareId) throws SiteWhereException {
		Map<String, String> vars = new HashMap<String, String>();
		vars.put("hardwareId", hardwareId);
		return sendRest(getBaseUrl() + "devices/{hardwareId}/assignment", HttpMethod.GET, null,
				DeviceAssignment.class, vars);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.sitewhere.spi.ISiteWhereClient#createDeviceAssignment(com.sitewhere.spi.device
	 * .request. IDeviceAssignmentCreateRequest)
	 */
	public DeviceAssignment createDeviceAssignment(IDeviceAssignmentCreateRequest request)
			throws SiteWhereException {
		Map<String, String> vars = new HashMap<String, String>();
		return sendRest(getBaseUrl() + "assignments", HttpMethod.POST, request, DeviceAssignment.class, vars);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.sitewhere.spi.ISiteWhereClient#getDeviceAssignmentByToken(java.lang.String)
	 */
	public DeviceAssignment getDeviceAssignmentByToken(String assignmentToken) throws SiteWhereException {
		Map<String, String> vars = new HashMap<String, String>();
		vars.put("assignmentToken", assignmentToken);
		return sendRest(getBaseUrl() + "assignments/{assignmentToken}", HttpMethod.GET, null,
				DeviceAssignment.class, vars);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.sitewhere.spi.ISiteWhereClient#deleteDeviceAssignment(java.lang.String,
	 * boolean)
	 */
	public DeviceAssignment deleteDeviceAssignment(String assignmentToken, boolean force)
			throws SiteWhereException {
		Map<String, String> vars = new HashMap<String, String>();
		vars.put("assignmentToken", assignmentToken);
		String url = getBaseUrl() + "assignments/{assignmentToken}";
		if (force) {
			url += "?force=true";
		}
		return sendRest(url, HttpMethod.DELETE, null, DeviceAssignment.class, vars);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.sitewhere.spi.ISiteWhereClient#listDeviceAssignmentHistory(java.lang.String)
	 */
	public DeviceAssignmentSearchResults listDeviceAssignmentHistory(String hardwareId)
			throws SiteWhereException {
		Map<String, String> vars = new HashMap<String, String>();
		vars.put("hardwareId", hardwareId);
		return sendRest(getBaseUrl() + "devices/{hardwareId}/assignments", HttpMethod.GET, null,
				DeviceAssignmentSearchResults.class, vars);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.sitewhere.spi.ISiteWhereClient#addDeviceEventBatch(java.lang.String,
	 * com.sitewhere.rest.model.device.DeviceEventBatch)
	 */
	public DeviceEventBatchResponse addDeviceEventBatch(String hardwareId, DeviceEventBatch batch)
			throws SiteWhereException {
		Map<String, String> vars = new HashMap<String, String>();
		vars.put("hardwareId", hardwareId);
		return sendRest(getBaseUrl() + "devices/{hardwareId}/batch", HttpMethod.POST, batch,
				DeviceEventBatchResponse.class, vars);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.sitewhere.spi.ISiteWhereClient#findDeviceAssignmentsNear(double, double,
	 * double, int)
	 */
	public DeviceAssignmentSearchResults findDeviceAssignmentsNear(double latitude, double longitude,
			double maxDistance, int maxResults) throws SiteWhereException {
		Map<String, String> vars = new HashMap<String, String>();
		vars.put("latitude", String.valueOf(latitude));
		vars.put("longitude", String.valueOf(longitude));
		vars.put("maxDistance", String.valueOf(maxDistance));
		vars.put("maxResults", String.valueOf(maxResults));
		String url = getBaseUrl() + "assignments/near/{latitude}/{longitude}"
				+ "?maxDistance={maxDistance}&maxResults={maxResults}";
		return sendRest(url, HttpMethod.GET, null, DeviceAssignmentSearchResults.class, vars);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.sitewhere.spi.ISiteWhereClient#updateDeviceAssignmentMetadata(java.lang.String,
	 * com.sitewhere.rest.model.device.MetadataProvider)
	 */
	public DeviceAssignment updateDeviceAssignmentMetadata(String token, MetadataProvider metadata)
			throws SiteWhereException {
		Map<String, String> vars = new HashMap<String, String>();
		vars.put("token", token);
		return sendRest(getBaseUrl() + "assignments/{token}/metadata", HttpMethod.POST, metadata,
				DeviceAssignment.class, vars);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.sitewhere.spi.ISiteWhereClient#updateDeviceAssignmentLocation(java.lang.String,
	 * java.lang.String)
	 */
	public DeviceAssignment updateDeviceAssignmentLocation(String token, String locationId)
			throws SiteWhereException {
		Map<String, String> vars = new HashMap<String, String>();
		vars.put("token", token);
		vars.put("locationId", locationId);
		return sendRest(getBaseUrl() + "assignments/{token}/location/{locationId}", HttpMethod.PUT, null,
				DeviceAssignment.class, vars);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.sitewhere.spi.ISiteWhereClient#createDeviceMeasurements(java.lang.String,
	 * com.sitewhere.rest.model.device.request.DeviceMeasurementsCreateRequest)
	 */
	public DeviceMeasurements createDeviceMeasurements(String assignmentToken,
			DeviceMeasurementsCreateRequest request) throws SiteWhereException {
		Map<String, String> vars = new HashMap<String, String>();
		vars.put("token", assignmentToken);
		return sendRest(getBaseUrl() + "assignments/{token}/measurements", HttpMethod.POST, request,
				DeviceMeasurements.class, vars);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.sitewhere.spi.ISiteWhereClient#listDeviceMeasurements(java.lang.String,
	 * int)
	 */
	public SearchResults<DeviceMeasurements> listDeviceMeasurements(String assignmentToken, int maxCount)
			throws SiteWhereException {
		Map<String, String> vars = new HashMap<String, String>();
		vars.put("token", assignmentToken);
		vars.put("count", String.valueOf(maxCount));
		String url = getBaseUrl() + "assignments/{token}/measurements?count={count}";
		return sendRest(url, HttpMethod.GET, null, DeviceMeasurementsSearchResults.class, vars);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.sitewhere.spi.ISiteWhereClient#createDeviceLocation(java.lang.String,
	 * com.sitewhere.rest.model.device.request.DeviceLocationCreateRequest)
	 */
	public DeviceLocation createDeviceLocation(String assignmentToken, DeviceLocationCreateRequest request)
			throws SiteWhereException {
		Map<String, String> vars = new HashMap<String, String>();
		vars.put("token", assignmentToken);
		return sendRest(getBaseUrl() + "assignments/{token}/locations", HttpMethod.POST, request,
				DeviceLocation.class, vars);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.sitewhere.spi.ISiteWhereClient#listDeviceLocations(java.lang.String, int)
	 */
	public DeviceLocationSearchResults listDeviceLocations(String assignmentToken, int maxCount)
			throws SiteWhereException {
		Map<String, String> vars = new HashMap<String, String>();
		vars.put("token", assignmentToken);
		vars.put("count", String.valueOf(maxCount));
		String url = getBaseUrl() + "assignments/{token}/locations?count={count}";
		return sendRest(url, HttpMethod.GET, null, DeviceLocationSearchResults.class, vars);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.sitewhere.spi.ISiteWhereClient#associateAlertWithDeviceLocation(java.lang.String
	 * , java.lang.String)
	 */
	public DeviceLocation associateAlertWithDeviceLocation(String alertId, String locationId)
			throws SiteWhereException {
		Map<String, String> vars = new HashMap<String, String>();
		vars.put("locationId", locationId);
		vars.put("alertId", alertId);
		String url = getBaseUrl() + "locations/{locationId}/alerts/{alertId}";
		return sendRest(url, HttpMethod.PUT, null, DeviceLocation.class, vars);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.sitewhere.spi.ISiteWhereClient#createDeviceAlert(java.lang.String,
	 * com.sitewhere.rest.model.device.request.DeviceAlertCreateRequest)
	 */
	public DeviceAlert createDeviceAlert(String assignmentToken, DeviceAlertCreateRequest request)
			throws SiteWhereException {
		Map<String, String> vars = new HashMap<String, String>();
		vars.put("token", assignmentToken);
		return sendRest(getBaseUrl() + "assignments/{token}/alerts", HttpMethod.POST, request,
				DeviceAlert.class, vars);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.sitewhere.spi.ISiteWhereClient#listDeviceAlerts(java.lang.String, int)
	 */
	public DeviceAlertSearchResults listDeviceAlerts(String assignmentToken, int maxCount)
			throws SiteWhereException {
		Map<String, String> vars = new HashMap<String, String>();
		vars.put("token", assignmentToken);
		vars.put("count", String.valueOf(maxCount));
		String url = getBaseUrl() + "assignments/{token}/alerts?count={count}";
		return sendRest(url, HttpMethod.GET, null, DeviceAlertSearchResults.class, vars);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.sitewhere.spi.ISiteWhereClient#createZone(java.lang.String,
	 * com.sitewhere.rest.model.device.request.ZoneCreateRequest)
	 */
	public Zone createZone(String siteToken, ZoneCreateRequest request) throws SiteWhereException {
		Map<String, String> vars = new HashMap<String, String>();
		vars.put("siteToken", siteToken);
		return sendRest(getBaseUrl() + "sites/{siteToken}/zones", HttpMethod.POST, request, Zone.class, vars);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.sitewhere.spi.ISiteWhereClient#listZonesForSite(java.lang.String)
	 */
	public ZoneSearchResults listZonesForSite(String siteToken) throws SiteWhereException {
		Map<String, String> vars = new HashMap<String, String>();
		vars.put("siteToken", siteToken);
		String url = getBaseUrl() + "sites/{siteToken}/zones";
		return sendRest(url, HttpMethod.GET, null, ZoneSearchResults.class, vars);
	}

	/**
	 * Send a REST request and handle the response.
	 * 
	 * @param url
	 * @param method
	 * @param input
	 * @param clazz
	 * @param vars
	 * @return
	 * @throws SiteWhereSystemException
	 */
	protected <S, T> S sendRest(String url, HttpMethod method, T input, Class<S> clazz,
			Map<String, String> vars) throws SiteWhereSystemException {
		try {
			HttpEntity<T> entity = new HttpEntity<T>(input);
			ResponseEntity<S> response = getClient().exchange(url, method, entity, clazz, vars);
			return response.getBody();
		} catch (ResourceAccessException e) {
			if (e.getCause() instanceof SiteWhereSystemException) {
				throw (SiteWhereSystemException) e.getCause();
			}
			throw new RuntimeException(e);
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