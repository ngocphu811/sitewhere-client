/*
 * $Id$
 * --------------------------------------------------------------------------------------
 * Copyright (c) Reveal Technologies, LLC. All rights reserved. http://www.reveal-tech.com
 *
 * The software in this package is published under the terms of the CPAL v1.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE.txt file.
 */

package com.sitewhere.spi.user;

import java.util.List;

import com.sitewhere.spi.SiteWhereException;
import com.sitewhere.spi.user.request.IUserCreateRequest;

/**
 * Interface for user management operations.
 * 
 * @author Derek
 */
public interface IUserManagement {

	/**
	 * Create a new user based on the given input.
	 * 
	 * @param request
	 * @return
	 * @throws SiteWhereException
	 */
	public IUser createUser(IUserCreateRequest request) throws SiteWhereException;

	/**
	 * Authenticate the given username and password.
	 * 
	 * @param username
	 * @param password
	 * @return
	 * @throws SiteWhereException
	 */
	public IUser authenticate(String username, String password) throws SiteWhereException;

	/**
	 * Update details for a user.
	 * 
	 * @param username
	 * @param request
	 * @return
	 * @throws SiteWhereException
	 */
	public IUser updateUser(String username, IUserCreateRequest request) throws SiteWhereException;

	/**
	 * Get a user given unique username.
	 * 
	 * @param username
	 * @return
	 * @throws SiteWhereException
	 */
	public IUser getUserByUsername(String username) throws SiteWhereException;

	/**
	 * Get the granted authorities for a specific user. Does not include any authorities inherited from
	 * groups.
	 * 
	 * @param username
	 * @return
	 * @throws SiteWhereException
	 */
	public List<IGrantedAuthority> getGrantedAuthorities(String username) throws SiteWhereException;

	/**
	 * Add user authorities. Duplicates are ignored.
	 * 
	 * @param username
	 * @param authorities
	 * @return
	 * @throws SiteWhereException
	 */
	public List<IGrantedAuthority> addGrantedAuthorities(String username, List<String> authorities)
			throws SiteWhereException;

	/**
	 * Remove user authorities. Ignore if not previously granted.
	 * 
	 * @param username
	 * @param authorities
	 * @return
	 * @throws SiteWhereException
	 */
	public List<IGrantedAuthority> removeGrantedAuthorities(String username, List<String> authorities)
			throws SiteWhereException;

	/**
	 * Get the list of all users.
	 * 
	 * @return
	 * @throws SiteWhereException
	 */
	public List<IUser> listUsers() throws SiteWhereException;

	/**
	 * Delete the user with the given username.
	 * 
	 * @param username
	 * @throws SiteWhereException
	 */
	public void deleteUser(String username) throws SiteWhereException;

	/**
	 * Create a new granted authority.
	 * 
	 * @param auth
	 * @throws SiteWhereException
	 */
	public IGrantedAuthority createGrantedAuthority(IGrantedAuthority auth) throws SiteWhereException;

	/**
	 * Get a granted authority by name.
	 * 
	 * @param name
	 * @return
	 * @throws SiteWhereException
	 */
	public IGrantedAuthority getGrantedAuthorityByName(String name) throws SiteWhereException;

	/**
	 * Update a granted authority.
	 * 
	 * @param name
	 * @param auth
	 * @return
	 * @throws SiteWhereException
	 */
	public IGrantedAuthority updateGrantedAuthority(String name, IGrantedAuthority auth)
			throws SiteWhereException;

	/**
	 * List all granted authorities.
	 * 
	 * @return
	 * @throws SiteWhereException
	 */
	public List<IGrantedAuthority> listGrantedAuthorities() throws SiteWhereException;

	/**
	 * Delete a granted authority.
	 * 
	 * @param authority
	 * @throws SiteWhereException
	 */
	public void deleteGrantedAuthority(String authority) throws SiteWhereException;
}