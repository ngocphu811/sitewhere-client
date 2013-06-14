/*
* $Id$
* --------------------------------------------------------------------------------------
* Copyright (c) Reveal Technologies, LLC. All rights reserved. http://www.reveal-tech.com
*
* The software in this package is published under the terms of the CPAL v1.0
* license, a copy of which has been included with this distribution in the
* LICENSE.txt file.
*/

package com.sitewhere.spi.asset;

/**
 * Indicates the general type of an asset.
 * 
 * @author dadams
 */
public enum AssetType {

	/** No associated asset */
	Unassociated('U'),

	/** Asset is a person */
	Person('P'),

	/** Asset is a piece of hardware */
	Hardware('H');

	/** Code */
	private char code;

	private AssetType(char code) {
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
	 * Get AssetType from code.
	 * 
	 * @param type
	 * @return
	 */
	public static AssetType fromCode(char code) {
		for (AssetType current : AssetType.values()) {
			if (current.getCode() == code) {
				return current;
			}
		}
		throw new RuntimeException("Unknown AssetType code: " + code);
	}
}