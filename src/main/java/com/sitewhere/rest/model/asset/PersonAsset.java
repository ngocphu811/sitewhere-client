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
import com.sitewhere.spi.asset.IPersonAsset;

/**
 * Model class for a person asset.
 * 
 * @author dadams
 */
public class PersonAsset extends Asset implements IPersonAsset {

	/** Asset uaername */
	private String userName;

	/** Asset name */
	private String name;

	/** Asset email address */
	private String emailAddress;

	/** Primary photo URL */
	private String photoUrl;

	public PersonAsset() {
		setType(AssetType.Person);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.sitewhere.spi.asset.IPersonAsset#getUserName()
	 */
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.sitewhere.spi.asset.IPersonAsset#getName()
	 */
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.sitewhere.spi.asset.IPersonAsset#getEmailAddress()
	 */
	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.sitewhere.spi.asset.IPersonAsset#getPhotoUrl()
	 */
	public String getPhotoUrl() {
		return photoUrl;
	}

	public void setPhotoUrl(String photoUrl) {
		this.photoUrl = photoUrl;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.sitewhere.spi.asset.IAsset#loadFromProperties()
	 */
	public void loadFromProperties() {
		super.loadFromProperties();
		this.setName(getProperty(getNameProperty()));
		this.setUserName(getProperty(getUserNameProperty()));
		this.setEmailAddress(getProperty(getEmailAddressProperty()));
		this.setPhotoUrl(getProperty(getPhotoUrlProperty()));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.sitewhere.spi.asset.IPersonAsset#getUserNameProperty()
	 */
	@JsonIgnore
	public String getUserNameProperty() {
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.sitewhere.spi.asset.IPersonAsset#getNameProperty()
	 */
	@JsonIgnore
	public String getNameProperty() {
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.sitewhere.spi.asset.IPersonAsset#getEmailAddressProperty()
	 */
	@JsonIgnore
	public String getEmailAddressProperty() {
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.sitewhere.spi.asset.IPersonAsset#getPhotoUrlProperty()
	 */
	@JsonIgnore
	public String getPhotoUrlProperty() {
		return null;
	}
}