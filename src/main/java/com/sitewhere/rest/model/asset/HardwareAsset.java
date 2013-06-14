/*
* $Id$
* --------------------------------------------------------------------------------------
* Copyright (c) Reveal Technologies, LLC. All rights reserved. http://www.reveal-tech.com
*
* The software in this package is published under the terms of the CPAL v1.0
* license, a copy of which has been included with this distribution in the
* LICENSE.txt file.
*/

package com.sitewhere.rest.model.asset;

import org.codehaus.jackson.annotate.JsonIgnore;

import com.sitewhere.spi.asset.AssetType;
import com.sitewhere.spi.asset.IHardwareAsset;

/**
 * Model class for a hardware asset.
 * 
 * @author dadams
 */
public class HardwareAsset extends Asset implements IHardwareAsset {

	/** Asset name */
	private String name;

	/** SKU */
	private String sku;

	/** Asset description */
	private String description;

	/** Asset image URL */
	private String imageUrl;

	public HardwareAsset() {
		setType(AssetType.Hardware);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.sitewhere.spi.asset.IHardwareAsset#getName()
	 */
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.sitewhere.spi.asset.IHardwareAsset#getSku()
	 */
	public String getSku() {
		return sku;
	}

	public void setSku(String sku) {
		this.sku = sku;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.sitewhere.spi.asset.IHardwareAsset#getDescription()
	 */
	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.sitewhere.spi.asset.IHardwareAsset#getImageUrl()
	 */
	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String url) {
		this.imageUrl = url;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.sitewhere.spi.asset.IHardwareAsset#getNameProperty()
	 */
	@JsonIgnore
	public String getNameProperty() {
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.sitewhere.spi.asset.IHardwareAsset#getDescriptionProperty()
	 */
	@JsonIgnore
	public String getDescriptionProperty() {
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.sitewhere.spi.asset.IHardwareAsset#getImageUrlProperty()
	 */
	@JsonIgnore
	public String getImageUrlProperty() {
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.sitewhere.spi.asset.IHardwareAsset#getSkuProperty()
	 */
	@JsonIgnore
	public String getSkuProperty() {
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.sitewhere.spi.asset.IAsset#loadFromProperties()
	 */
	public void loadFromProperties() {
		super.loadFromProperties();
		this.setName(getProperty(getNameProperty()));
		this.setSku(getProperty(getSkuProperty()));
		this.setDescription(truncate(getProperty(getDescriptionProperty()), 175));
		this.setImageUrl(getProperty(getImageUrlProperty()));
	}

	/**
	 * Truncate the input string if necessary.
	 * 
	 * @param input
	 * @param max
	 * @return
	 */
	protected String truncate(String input, int max) {
		if (input.length() > max) {
			String truncated = input.substring(0, max);
			truncated += "...";
			return truncated;
		}
		return input;
	}
}