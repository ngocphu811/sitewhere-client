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

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.codehaus.jackson.annotate.JsonIgnore;

import com.sitewhere.spi.asset.AssetType;
import com.sitewhere.spi.asset.IAsset;

/**
 * Base model class for an asset.
 * 
 * @author dadams
 */
public class Asset implements IAsset {

	/** Unique id */
	private String id;

	/** Asset type indicator */
	private AssetType type;

	private Map<String, String> properties = new HashMap<String, String>();

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.sitewhere.spi.asset.IAsset#getId()
	 */
	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.sitewhere.spi.asset.IAsset#getType()
	 */
	public AssetType getType() {
		return type;
	}

	public void setType(AssetType type) {
		this.type = type;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.sitewhere.spi.asset.IAsset#getPropertyNames()
	 */
	@JsonIgnore
	public Set<String> getPropertyNames() {
		return properties.keySet();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.sitewhere.spi.asset.IAsset#setProperty(java.lang.String, java.lang.String)
	 */
	public void setProperty(String name, String value) {
		properties.put(name, value);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.sitewhere.spi.asset.IAsset#getProperty(java.lang.String)
	 */
	public String getProperty(String name) {
		return properties.get(name);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.sitewhere.spi.asset.IAsset#getIdProperty()
	 */
	@JsonIgnore
	public String getIdProperty() {
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.sitewhere.spi.asset.IAsset#loadFromProperties()
	 */
	public void loadFromProperties() {
		this.setId(getProperty(getIdProperty()));
	}

	public Map<String, String> getProperties() {
		return properties;
	}
}