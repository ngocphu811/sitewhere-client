/*
 * LargeDataset.java 
 * --------------------------------------------------------------------------------------
 * Copyright (c) Reveal Technologies, LLC. All rights reserved. http://www.reveal-tech.com
 *
 * The software in this package is published under the terms of the CPAL v1.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE.txt file.
 */
package com.sitewhere.test;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.junit.Before;
import org.junit.Test;

import com.sitewhere.rest.model.common.Location;
import com.sitewhere.rest.model.device.Device;
import com.sitewhere.rest.model.device.DeviceAssignment;
import com.sitewhere.rest.model.device.Site;
import com.sitewhere.rest.model.device.Zone;
import com.sitewhere.rest.model.device.request.DeviceAssignmentCreateRequest;
import com.sitewhere.rest.model.device.request.DeviceCreateRequest;
import com.sitewhere.rest.model.device.request.SiteCreateRequest;
import com.sitewhere.rest.model.device.request.ZoneCreateRequest;
import com.sitewhere.rest.service.SiteWhereClient;
import com.sitewhere.spi.ISiteWhereClient;
import com.sitewhere.spi.SiteWhereException;
import com.sitewhere.spi.device.DeviceAssignmentType;

/**
 * Tests that can be used to populate a SiteWhere backend with large amounts of data
 * useful for performance analysis.
 * 
 * @author Derek
 */
public class LargeDataset {

	/** SiteWhere client */
	private ISiteWhereClient client;

	/** Number of devices to create */
	public static final int NUM_SITES = 1;

	/** Number of devices to create */
	public static final int ASSIGNMENTS_PER_SITE = 50;

	/** Number of devices to create */
	public static final int ZONES_PER_SITE = 5;

	/** Image URL assocaited with sites */
	public static final String SITE_IMAGE_URL = "http://i.telegraph.co.uk/multimedia/archive/01809/satellite_1809335c.jpg";

	@Before
	public void setup() {
		this.client = new SiteWhereClient();
	}

	@Test
	public void createData() throws SiteWhereException {
		List<Site> sites = createSites();
		for (Site site : sites) {
			List<Device> devices = createDevices();
			for (Device device : devices) {
				createDeviceAssignment(device, site);
			}
			createZones(site);
		}
	}

	/**
	 * Create a site.
	 * 
	 * @return
	 * @throws SiteWhereException
	 */
	public List<Site> createSites() throws SiteWhereException {
		List<Site> results = new ArrayList<Site>();
		for (int x = 0; x < NUM_SITES; x++) {
			SiteCreateRequest siteCreate = new SiteCreateRequest();
			siteCreate.setName("Test Site");
			siteCreate.setDescription("Sample description for Test Site.");
			siteCreate.setImageUrl(SITE_IMAGE_URL);
			siteCreate.setMapType("mapquest");
			results.add(client.createSite(siteCreate));
		}
		return results;
	}

	/**
	 * Create devices.
	 * 
	 * @return
	 * @throws SiteWhereException
	 */
	public List<Device> createDevices() throws SiteWhereException {
		List<Device> results = new ArrayList<Device>();
		for (int x = 0; x < ASSIGNMENTS_PER_SITE; x++) {
			DeviceCreateRequest request = new DeviceCreateRequest();
			request.setHardwareId(UUID.randomUUID().toString());
			request.setComments("Test device " + x + ".");
			request.setAssetId("200");
			results.add(client.createDevice(request));
		}
		return results;
	}

	/**
	 * Create a device assignment.
	 * 
	 * @param device
	 * @param site
	 * @return
	 * @throws SiteWhereException
	 */
	public DeviceAssignment createDeviceAssignment(Device device, Site site) throws SiteWhereException {
		DeviceAssignmentCreateRequest request = new DeviceAssignmentCreateRequest();
		request.setDeviceHardwareId(device.getHardwareId());
		request.setSiteToken(site.getToken());
		request.setAssignmentType(DeviceAssignmentType.Person);
		request.setAssetId("1");
		return client.createDeviceAssignment(request);
	}

	/**
	 * Create zones for a site.
	 * 
	 * @param site
	 * @return
	 * @throws SiteWhereException
	 */
	public List<Zone> createZones(Site site) throws SiteWhereException {
		List<Zone> results = new ArrayList<Zone>();
		for (int x = 0; x < ZONES_PER_SITE; x++) {
			ZoneCreateRequest request = new ZoneCreateRequest();
			request.setName("Zone " + x);
			request.setBorderColor("black");
			request.setFillColor("blue");
			request.setOpacity(0.4);
			List<Location> coords = new ArrayList<Location>();
			coords.add(new Location(33.7550, -84.3900));
			coords.add(new Location(32.4922, -84.9403));
			coords.add(new Location(34.2600, -85.1850));
			request.setCoordinates(coords);
			results.add(client.createZone(site.getToken(), request));
		}
		return results;
	}
}