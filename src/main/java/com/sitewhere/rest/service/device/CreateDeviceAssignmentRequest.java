/*
 * CreateDeviceAssignmentRequest.java 
 * --------------------------------------------------------------------------------------
 * Copyright (c) Reveal Technologies, LLC. All rights reserved. http://www.reveal-tech.com
 *
 * The software in this package is published under the terms of the CPAL v1.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE.txt file.
 */
package com.sitewhere.rest.service.device;

import com.sitewhere.spi.asset.AssetType;

/**
 * Holds fields needed to create a device assignment.
 * 
 * @author Derek Adams
 */
public class CreateDeviceAssignmentRequest {

	/** Device hardware id */
	private String deviceHardwareId;

	/** Unique site token */
	private String siteToken;

	/** Type of asset */
	private AssetType assetType;

	/** Unique asset id */
	private String assetId;

	public String getDeviceHardwareId() {
		return deviceHardwareId;
	}

	public void setDeviceHardwareId(String deviceHardwareId) {
		this.deviceHardwareId = deviceHardwareId;
	}

	public String getSiteToken() {
		return siteToken;
	}

	public void setSiteToken(String siteToken) {
		this.siteToken = siteToken;
	}

	public AssetType getAssetType() {
		return assetType;
	}

	public void setAssetType(AssetType assetType) {
		this.assetType = assetType;
	}

	public String getAssetId() {
		return assetId;
	}

	public void setAssetId(String assetId) {
		this.assetId = assetId;
	}
}