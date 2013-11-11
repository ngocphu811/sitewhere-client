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

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.sitewhere.rest.model.common.MetadataProvider;
import com.sitewhere.rest.model.datatype.JsonDateSerializer;
import com.sitewhere.spi.device.IDeviceEvent;

/**
 * Model object for an event originating from a remote device.
 * 
 * @author dadams
 */
public abstract class DeviceEvent extends MetadataProvider implements IDeviceEvent, Comparable<IDeviceEvent> {

	/** Site token */
	private String siteToken;

	/** Device assignment token */
	private String deviceAssignmentToken;

	/** Asset name at time of event */
	private String assetName;

	/** Date event occurred */
	private Date eventDate;

	/** Date event was received */
	private Date receivedDate;

	/** List of alert ids related to the event */
	private List<String> alertIds = new ArrayList<String>();

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.sitewhere.spi.device.IDeviceEvent#getSiteToken()
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
	 * @see com.sitewhere.spi.device.IDeviceEvent#getDeviceAssignmentToken()
	 */
	public String getDeviceAssignmentToken() {
		return deviceAssignmentToken;
	}

	public void setDeviceAssignmentToken(String deviceAssignmentToken) {
		this.deviceAssignmentToken = deviceAssignmentToken;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.sitewhere.spi.device.IDeviceEvent#getAssetName()
	 */
	public String getAssetName() {
		return assetName;
	}

	public void setAssetName(String assetName) {
		this.assetName = assetName;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.sitewhere.spi.device.IDeviceEvent#getEventDate()
	 */
	@JsonSerialize(using = JsonDateSerializer.class)
	public Date getEventDate() {
		return eventDate;
	}

	public void setEventDate(Date eventDate) {
		this.eventDate = eventDate;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.sitewhere.spi.device.IDeviceEvent#getReceivedDate()
	 */
	@JsonSerialize(using = JsonDateSerializer.class)
	public Date getReceivedDate() {
		return receivedDate;
	}

	public void setReceivedDate(Date receivedDate) {
		this.receivedDate = receivedDate;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.sitewhere.spi.device.IDeviceEvent#getAlertIds()
	 */
	public List<String> getAlertIds() {
		return alertIds;
	}

	public void setAlertIds(List<String> alertIds) {
		this.alertIds = alertIds;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	public int compareTo(IDeviceEvent other) {
		if ((getEventDate() != null) && (other.getEventDate() != null)) {
			return getEventDate().compareTo(other.getEventDate());
		}
		return 0;
	}

	/**
	 * Create a copy of an SPI object. Used by web services for marshaling.
	 * 
	 * @param source
	 * @param target
	 */
	public static void copy(IDeviceEvent source, DeviceEvent target) {
		target.setSiteToken(source.getSiteToken());
		target.setDeviceAssignmentToken(source.getDeviceAssignmentToken());
		target.setAssetName(source.getAssetName());
		target.setReceivedDate(source.getReceivedDate());
		target.setEventDate(source.getEventDate());
		target.getAlertIds().clear();
		if (source.getAlertIds() != null) {
			for (String alertId : source.getAlertIds()) {
				target.getAlertIds().add(alertId);
			}
		}
		MetadataProvider.copy(source, target);
	}
}