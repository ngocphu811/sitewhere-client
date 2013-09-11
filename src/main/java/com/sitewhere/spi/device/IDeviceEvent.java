/*
 * $Id$
 * --------------------------------------------------------------------------------------
 * Copyright (c) Reveal Technologies, LLC. All rights reserved. http://www.reveal-tech.com
 *
 * The software in this package is published under the terms of the CPAL v1.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE.txt file.
 */

package com.sitewhere.spi.device;

import java.util.Date;
import java.util.List;

import com.sitewhere.spi.common.IMetadataProvider;

/**
 * Event that originates from a device.
 * 
 * @author Derek
 */
public interface IDeviceEvent extends IMetadataProvider {

	/**
	 * Get unique id for this event.
	 * 
	 * @return
	 */
	public String getId();

	/**
	 * Get token for site the event pertains to.
	 * 
	 * @return
	 */
	public String getSiteToken();

	/**
	 * Get the device assignment the event pertains to.
	 * 
	 * @return
	 */
	public String getDeviceAssignmentToken();

	/**
	 * Get the asset name at time of creation.
	 * 
	 * @return
	 */
	public String getAssetName();

	/**
	 * Get the date the event occurred.
	 * 
	 * @return
	 */
	public Date getEventDate();

	/**
	 * Get the date this event was received.
	 * 
	 * @return
	 */
	public Date getReceivedDate();

	/**
	 * Get ids of any alerts related to the event.
	 * 
	 * @return
	 */
	public List<String> getAlertIds();
}