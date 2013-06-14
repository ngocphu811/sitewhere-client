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

/**
 * Used as a clear indicator of the current status of a device assignment.
 * 
 * @author dadams
 */
public enum DeviceAssignmentStatus {

	/** Device is active */
	Active('A'),

	/** Device reported missing */
	Missing('M'),

	/** Device released */
	Released('R');

	/** Status code */
	private char statusCode;

	private DeviceAssignmentStatus(char statusCode) {
		this.statusCode = statusCode;
	}

	/**
	 * Get the status code.
	 * 
	 * @return
	 */
	public char getStatusCode() {
		return statusCode;
	}

	/**
	 * Get DeviceAssignmentStatus from status code.
	 * 
	 * @param type
	 * @return
	 */
	public static DeviceAssignmentStatus fromStatusCode(char code) {
		for (DeviceAssignmentStatus status : DeviceAssignmentStatus.values()) {
			if (status.getStatusCode() == code) {
				return status;
			}
		}
		throw new RuntimeException("Unknown DeviceAssignmentStatus code: " + code);
	}
}