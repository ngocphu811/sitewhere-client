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
 * Indicates source that generated an alert.
 * 
 * @author dadams
 */
public enum AlertSource {

	/** Device generated */
	Device('D'),

	/** System generated */
	System('S');

	/** Code */
	private char code;

	private AlertSource(char code) {
		this.code = code;
	}

	/**
	 * Get the code.
	 * 
	 * @return
	 */
	public char getCode() {
		return code;
	}

	/**
	 * Get AlertSource from code.
	 * 
	 * @param type
	 * @return
	 */
	public static AlertSource fromCode(char code) {
		for (AlertSource current : AlertSource.values()) {
			if (current.getCode() == code) {
				return current;
			}
		}
		throw new RuntimeException("Unknown AlertSource code: " + code);
	}
}