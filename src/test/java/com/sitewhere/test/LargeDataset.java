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

import org.junit.Before;
import org.junit.Test;

import com.sitewhere.rest.model.device.request.SiteCreateRequest;
import com.sitewhere.rest.service.SiteWhereClient;
import com.sitewhere.spi.ISiteWhereClient;
import com.sitewhere.spi.SiteWhereException;

/**
 * Tests that can be used to populate a SiteWhere backend with large amounts of data
 * useful for performance analysis.
 * 
 * @author Derek
 */
public class LargeDataset {

	/** SiteWhere client */
	private ISiteWhereClient client;

	/** Number of sites to create */
	public static final int NUM_SITES = 1;

	/** Image URL assocaited with sites */
	public static final String SITE_IMAGE_URL = "http://i.telegraph.co.uk/multimedia/archive/01809/satellite_1809335c.jpg";

	@Before
	public void setup() {
		this.client = new SiteWhereClient();
	}

	@Test
	public void createSites() throws SiteWhereException {
		for (int x = 0; x < NUM_SITES; x++) {
			SiteCreateRequest siteCreate = new SiteCreateRequest();
			siteCreate.setName("Site " + x);
			siteCreate.setDescription("Sample description for site " + x + ".");
			siteCreate.setImageUrl(SITE_IMAGE_URL);
			siteCreate.setMapType("mapquest");
			client.createSite(siteCreate);
		}
	}
}