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

import org.junit.Test;

import com.sitewhere.rest.model.common.Location;
import com.sitewhere.rest.model.device.Device;
import com.sitewhere.rest.model.device.DeviceMeasurements;
import com.sitewhere.rest.model.device.Zone;
import com.sitewhere.rest.service.SiteWhereClient;
import com.sitewhere.rest.service.search.DeviceAssignmentSearchResults;
import com.sitewhere.rest.service.search.ZoneSearchResults;
import com.sitewhere.spi.SiteWhereException;

/**
 * Test cases for client API calls.
 * 
 * @author dadams
 */
public class ApiTests {

	/** Amazon instance URL */
	// private static final String AWS_URL = "http://107.21.251.203/sitewhere/api/";

	@Test
	public void getDeviceByHardwareId() throws SiteWhereException {
		SiteWhereClient client = new SiteWhereClient();
		Device device = client.getDeviceByHardwareId("bb-12335-76454");
		// Device device = client.getDeviceByHardwareId("3287423-ERICSON-5657");
		if (device != null) {
			System.out.println(device.getHardwareId() + " " + device.getComments());
		} else {
			System.out.println("Device not found.");
		}
	}

	@Test
	public void getDeviceAssignmentHistory() throws SiteWhereException {
		SiteWhereClient client = new SiteWhereClient();
		DeviceAssignmentSearchResults history = client.listDeviceAssignmentHistory("38729342-BB-3847389");
		System.out.println("Found " + history.getNumResults() + " history records.");
	}

	@Test
	public void testCreateMeasurements() throws SiteWhereException {
		SiteWhereClient client = new SiteWhereClient();
		DeviceMeasurements create = new DeviceMeasurements();
		create.setDeviceAssignmentToken("7139bbf5-f65d-42d7-9783-5d8603526f0d");
		create.setReceivedDate(new Date());
		create.setEventDate(new Date());
		create.addOrReplaceMeasurement("test", "123");
		create.addOrReplaceMeasurement("another", "another");
		DeviceMeasurements results = client.createDeviceMeasurements(create);
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
}