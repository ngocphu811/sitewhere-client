package com.sitewhere.rest.model.user;

import com.sitewhere.spi.user.IGroup;

/**
 * Model object for a group of users.
 * 
 * @author Derek Adams
 */
public class Group implements IGroup {

	/** Unique id */
	private long id;

	/** Group name */
	private String name;

	/** Group description */
	private String description;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.sitewhere.spi.user.IGroup#getId()
	 */
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.sitewhere.spi.user.IGroup#getName()
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
	 * @see com.sitewhere.spi.user.IGroup#getDescription()
	 */
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}