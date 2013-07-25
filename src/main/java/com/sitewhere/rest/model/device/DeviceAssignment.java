/*
 * $Id$
 * --------------------------------------------------------------------------------------
 * Copyright (c) Reveal Technologies, LLC. All rights reserved. http://www.reveal-tech.com
 *
 * The software in this package is published under the terms of the CPAL v1.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE.txt file.
 */

package com.sitewhere.rest.model.device;

import java.util.Date;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize.Inclusion;
import com.sitewhere.rest.model.asset.HardwareAsset;
import com.sitewhere.rest.model.asset.PersonAsset;
import com.sitewhere.rest.model.datatype.JsonDateSerializer;
import com.sitewhere.spi.asset.AssetType;
import com.sitewhere.spi.device.DeviceAssignmentStatus;
import com.sitewhere.spi.device.IDeviceAssignment;

/**
 * Device assignment value object used for marshaling.
 * 
 * @author dadams
 */
@JsonSerialize(include = Inclusion.NON_NULL)
public class DeviceAssignment extends MetadataProviderEntity implements IDeviceAssignment {

	/** Unique assignment token */
	private String token;

	/** Device being assigned */
	private Device device;

	/** Device hardware id */
	private String deviceHardwareId;

	/** Type of associated asset */
	private AssetType assetType;

	/** Id of associated asset */
	private String assetId;

	/** Associated asset name */
	private String assetName;

	/** Associated asset image */
	private String assetImageUrl;

	/** Assigned site */
	private Site site;

	/** Site token */
	private String siteToken;

	/** Assignment status */
	private DeviceAssignmentStatus status;

	/** Assignment start date */
	private Date activeDate;

	/** Assignment end date */
	private Date releasedDate;

	/** Last known location */
	private DeviceLocation lastLocation;

	/** Associated person asset */
	private PersonAsset associatedPerson;

	/** Associated hardware asset */
	private HardwareAsset associatedHardware;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.sitewhere.spi.device.IDeviceAssignment#getToken()
	 */
	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public Device getDevice() {
		return device;
	}

	public void setDevice(Device device) {
		this.device = device;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.sitewhere.spi.device.IDeviceAssignment#getDeviceHardwareId()
	 */
	public String getDeviceHardwareId() {
		return deviceHardwareId;
	}

	public void setDeviceHardwareId(String deviceHardwareId) {
		this.deviceHardwareId = deviceHardwareId;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.sitewhere.spi.device.IDeviceAssignment#getAssetType()
	 */
	public AssetType getAssetType() {
		return assetType;
	}

	public void setAssetType(AssetType assetType) {
		this.assetType = assetType;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.sitewhere.spi.device.IDeviceAssignment#getAssetId()
	 */
	public String getAssetId() {
		return assetId;
	}

	public void setAssetId(String assetId) {
		this.assetId = assetId;
	}

	public String getAssetName() {
		return assetName;
	}

	public void setAssetName(String assetName) {
		this.assetName = assetName;
	}

	public String getAssetImageUrl() {
		return assetImageUrl;
	}

	public void setAssetImageUrl(String assetImageUrl) {
		this.assetImageUrl = assetImageUrl;
	}

	public Site getSite() {
		return site;
	}

	public void setSite(Site site) {
		this.site = site;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.sitewhere.spi.device.IDeviceAssignment#getSiteToken()
	 */
	public String getSiteToken() {
		return siteToken;
	}

	public void setSiteToken(String siteToken) {
		this.siteToken = siteToken;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.sitewhere.spi.device.IDeviceAssignment#getStatus()
	 */
	public DeviceAssignmentStatus getStatus() {
		return status;
	}

	public void setStatus(DeviceAssignmentStatus status) {
		this.status = status;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.sitewhere.spi.device.IDeviceAssignment#getActiveDate()
	 */
	@JsonSerialize(using = JsonDateSerializer.class)
	public Date getActiveDate() {
		return activeDate;
	}

	public void setActiveDate(Date activeDate) {
		this.activeDate = activeDate;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.sitewhere.spi.device.IDeviceAssignment#getReleasedDate()
	 */
	@JsonSerialize(using = JsonDateSerializer.class)
	public Date getReleasedDate() {
		return releasedDate;
	}

	public void setReleasedDate(Date releasedDate) {
		this.releasedDate = releasedDate;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.sitewhere.spi.device.IDeviceAssignment#getLastLocation()
	 */
	public DeviceLocation getLastLocation() {
		return lastLocation;
	}

	public void setLastLocation(DeviceLocation lastLocation) {
		this.lastLocation = lastLocation;
	}

	public PersonAsset getAssociatedPerson() {
		return associatedPerson;
	}

	public void setAssociatedPerson(PersonAsset associatedPerson) {
		this.associatedPerson = associatedPerson;
	}

	public HardwareAsset getAssociatedHardware() {
		return associatedHardware;
	}

	public void setAssociatedHardware(HardwareAsset associatedHardware) {
		this.associatedHardware = associatedHardware;
	}
}