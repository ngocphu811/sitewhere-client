/*
 * $Id$
 * --------------------------------------------------------------------------------------
 * Copyright (c) Reveal Technologies, LLC. All rights reserved. http://www.reveal-tech.com
 *
 * The software in this package is published under the terms of the CPAL v1.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE.txt file.
 */

package com.sitewhere.test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;
import org.springframework.http.HttpStatus;
import org.springframework.web.client.HttpClientErrorException;

import com.sitewhere.rest.model.common.Location;
import com.sitewhere.rest.model.device.Device;
import com.sitewhere.rest.model.device.DeviceMeasurements;
import com.sitewhere.rest.model.device.Zone;
import com.sitewhere.rest.model.device.request.DeviceCreateRequest;
import com.sitewhere.rest.model.device.request.DeviceMeasurementsCreateRequest;
import com.sitewhere.rest.service.SiteWhereClient;
import com.sitewhere.rest.service.search.DeviceAssignmentSearchResults;
import com.sitewhere.rest.service.search.ZoneSearchResults;
import com.sitewhere.spi.ISiteWhereClient;
import com.sitewhere.spi.SiteWhereException;

/**
 * Test cases for client API calls.
 * 
 * @author dadams
 */
public class ApiTests {

	/** Hardware id used for test cases */
	public static final String TEST_HARDWARE_ID = "12356789-TEST-123";

	/** Asset id for testing */
	public static final String TEST_ASSET_ID = "174";

	/** SiteWhere client */
	private ISiteWhereClient client;

	@Before
	public void setup() {
		this.client = new SiteWhereClient();
	}

	@Test
	public void testDeviceCRUD() throws SiteWhereException {
		Device existing = client.getDeviceByHardwareId(TEST_HARDWARE_ID);
		if (existing != null) {
			client.deleteDevice(TEST_HARDWARE_ID, true);
		}

		// Test initial create.
		DeviceCreateRequest request = new DeviceCreateRequest();
		request.setHardwareId(TEST_HARDWARE_ID);
		request.setAssetId(TEST_ASSET_ID);
		request.setComments("This is a test device.");
		request.addOrReplaceMetadata("name1", "value1");
		request.addOrReplaceMetadata("name2", "value2");
		Device device = client.createDevice(request);
		Assert.assertNotNull("Device create returned null.", device);
		Assert.assertEquals("Metadata not stored properly.", 2, device.getMetadata().size());

		// Test duplicate.
		try {
			device = client.createDevice(request);
			Assert.fail("Create device allowed duplicate.");
		} catch (SiteWhereException e) {
			verifyErrorCode(e, HttpStatus.CONFLICT);
		}

		// Delete device.
		client.deleteDevice(TEST_HARDWARE_ID, true);
	}

	@Test
	public void getDeviceByHardwareId() throws SiteWhereException {
		Device device = client.getDeviceByHardwareId("23438373447-MEI-0933");
		if (device != null) {
			System.out.println(device.getHardwareId() + " " + device.getComments());
		} else {
			System.out.println("Device not found.");
		}
	}

	@Test
	public void getDeviceAssignmentHistory() throws SiteWhereException {
		DeviceAssignmentSearchResults history = client.listDeviceAssignmentHistory("38729342-BB-3847389");
		System.out.println("Found " + history.getNumResults() + " history records.");
	}

	@Test
	public void testCreateMeasurements() throws SiteWhereException {
		SiteWhereClient client = new SiteWhereClient();
		String assignmentToken = "7139bbf5-f65d-42d7-9783-5d8603526f0d";
		DeviceMeasurementsCreateRequest measurements = new DeviceMeasurementsCreateRequest();
		measurements.setEventDate(new Date());
		measurements.addOrReplaceMeasurement("test", "123");
		measurements.addOrReplaceMeasurement("another", "another");
		DeviceMeasurements results = client.createDeviceMeasurements(assignmentToken, measurements);
		System.out.println("Created " + results.getMeasurements().size() + " measurements.");
	}

	@Test
	public void testCreateZone() throws SiteWhereException {
		SiteWhereClient client = new SiteWhereClient();
		Zone zone = new Zone();
		zone.setName("My Test Zone");
		zone.setSiteToken("b2229cb1-de4e-4114-9863-08d0efd81064");
		List<Location> coords = new ArrayList<Location>();
		coords.add(new Location(30.0, -85.0));
		coords.add(new Location(30.0, -90.0));
		coords.add(new Location(35.0, -90.0));
		coords.add(new Location(35.0, -85.0));
		zone.setCoordinates(coords);
		Zone results = client.createZone(zone);
		System.out.println("Created zone: " + results.getName());
		ZoneSearchResults search = client.listZonesForSite("b2229cb1-de4e-4114-9863-08d0efd81064");
		System.out.println("Found " + search.getNumResults() + " results.");
	}

	/**
	 * Verifies that
	 * 
	 * @param e
	 */
	protected void verifyErrorCode(SiteWhereException e, HttpStatus status) {
		if (e.getCause() instanceof HttpClientErrorException) {
			HttpClientErrorException http = (HttpClientErrorException) e.getCause();
			if (http.getStatusCode() != status) {
				Assert.fail("Unexpected error code returned. Expected " + status.getReasonPhrase()
						+ " but got: " + http.getStatusText());
			}
		} else {
			Assert.fail("Unexpected exception: " + e.getMessage());
		}
	}
}