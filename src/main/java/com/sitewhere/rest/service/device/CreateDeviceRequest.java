/*
* $Id$
* --------------------------------------------------------------------------------------
* Copyright (c) Reveal Technologies, LLC. All rights reserved. http://www.reveal-tech.com
*
* The software in this package is published under the terms of the CPAL v1.0
* license, a copy of which has been included with this distribution in the
* LICENSE.txt file.
*/

package com.sitewhere.rest.service.device;

/**
 * Holds fields needed to create a new device.
 * 
 * @author Derek Adams
 */
public class CreateDeviceRequest {

	/** Hardware id for new device */
	private String hardwareId;

	/** Hardware asset id */
	private String assetId;

	/** Comments */
	private String comments;

	public String getHardwareId() {
		return hardwareId;
	}

	public void setHardwareId(String hardwareId) {
		this.hardwareId = hardwareId;
	}

	public String getAssetId() {
		return assetId;
	}

	public void setAssetId(String assetId) {
		this.assetId = assetId;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}
}