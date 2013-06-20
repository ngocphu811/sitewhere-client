package com.sitewhere.rest.model.user;

import com.sitewhere.spi.user.IGrantedAuthority;

/**
 * Model object for a granted authority.
 * 
 * @author Derek Adams
 */
public class GrantedAuthority implements IGrantedAuthority {

	/** Unique id */
	private long id;
	
	/** Authority */
	private String authority;
	
	/** Description */
	private String description;

	/* (non-Javadoc)
	 * @see com.sitewhere.spi.user.IGrantedAuthority#getId()
	 */
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	/* (non-Javadoc)
	 * @see com.sitewhere.spi.user.IGrantedAuthority#getAuthority()
	 */
	public String getAuthority() {
		return authority;
	}

	public void setAuthority(String authority) {
		this.authority = authority;
	}

	/* (non-Javadoc)
	 * @see com.sitewhere.spi.user.IGrantedAuthority#getDescription()
	 */
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}