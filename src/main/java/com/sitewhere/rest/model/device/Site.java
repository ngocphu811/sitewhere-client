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

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.sitewhere.spi.device.ISite;

/**
 * Model object for site information.
 * 
 * @author dadams
 */
@JsonInclude(Include.NON_NULL)
public class Site extends MetadataProviderEntity implements ISite {

	/** Unique token */
	private String token;

	/** Site name */
	private String name;

	/** Site description */
	private String description;

	/** Image URL */
	private String imageUrl;

	/** Map type */
	private String mapType;

	/** Map metadata */
	private MetadataProvider mapMetadata = new MetadataProvider();

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.sitewhere.spi.device.ISite#getMapType()
	 */
	public String getMapType() {
		return mapType;
	}

	public void setMapType(String mapType) {
		this.mapType = mapType;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.sitewhere.spi.device.ISite#getMapMetadata()
	 */
	public MetadataProvider getMapMetadata() {
		return mapMetadata;
	}

	public void setMapMetadata(MetadataProvider mapMetadata) {
		this.mapMetadata = mapMetadata;
	}

	/**
	 * Create a copy of an SPI object. Used by web services for marshaling.
	 * 
	 * @param input
	 * @return
	 */
	public static Site copy(ISite input) {
		Site result = new Site();
		result.setToken(input.getToken());
		result.setName(input.getName());
		result.setDescription(input.getDescription());
		result.setImageUrl(input.getImageUrl());
		result.setMapType(input.getMapType());
		MetadataProviderEntity.copy(input.getMapMetadata(), result.getMapMetadata());
		MetadataProviderEntity.copy(input, result);
		return result;
	}
}