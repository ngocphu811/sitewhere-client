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

/**
 * Interface for user management operations.
 * 
 * @author Derek
 */
public interface IUserManagement {

	/**
	 * Create a new user based on the given input.
	 * 
	 * @param user
	 * @return
	 * @throws SiteWhereException
	 */
	public IUser createUser(IUser user) throws SiteWhereException;

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
	 * Update details for a user. Username is used for to look up the user since the updated
	 * information may include a new username.
	 * 
	 * @param username
	 * @param user
	 * @return
	 * @throws SiteWhereException
	 */
	public IUser updateUser(String username, IUser user) throws SiteWhereException;

	/**
	 * Get a user given unique username.
	 * 
	 * @param username
	 * @return
	 * @throws SiteWhereException
	 */
	public IUser getUserByUsername(String username) throws SiteWhereException;

	/**
	 * Get the list of groups a user belongs to.
	 * 
	 * @param username
	 * @return
	 * @throws SiteWhereException
	 */
	public List<IGroup> getUserGroups(String username) throws SiteWhereException;

	/**
	 * Get the granted authorities for a specific user. Does not include any authorities inherited
	 * from groups.
	 * 
	 * @param username
	 * @return
	 * @throws SiteWhereException
	 */
	public List<IGrantedAuthority> getUserGrantedAuthoritites(String username)
			throws SiteWhereException;

	/**
	 * Add user authorities. Duplicates are ignored.
	 * 
	 * @param username
	 * @param authorities
	 * @return
	 * @throws SiteWhereException
	 */
	public List<IGrantedAuthority> addUserGrantedAuthorities(String username,
			List<String> authorities) throws SiteWhereException;

	/**
	 * Remove user authorities. Ignore if not previously granted.
	 * 
	 * @param username
	 * @param authorities
	 * @return
	 * @throws SiteWhereException
	 */
	public List<IGrantedAuthority> removeUserGrantedAuthorities(String username,
			List<String> authorities) throws SiteWhereException;

	/**
	 * Get the union of all user granted and group granted authorities for a user.
	 * 
	 * @param username
	 * @return
	 * @throws SiteWhereException
	 */
	public List<IGrantedAuthority> getAllGrantedAuthorities(String username)
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
	 * Create a new group.
	 * 
	 * @param group
	 * @return
	 * @throws SiteWhereException
	 */
	public IGroup createGroup(IGroup group) throws SiteWhereException;

	/**
	 * Update group information. Name is used to look up the group since the new info may include a
	 * changed group name.
	 * 
	 * @param groupName
	 * @param info
	 * @return
	 * @throws SiteWhereException
	 */
	public IGroup updateGroup(String groupName, IGroup info) throws SiteWhereException;

	/**
	 * Get a group by unique name.
	 * 
	 * @param groupName
	 * @return
	 * @throws SiteWhereException
	 */
	public IGroup getGroupByName(String groupName) throws SiteWhereException;

	/**
	 * Get the users associated with a group.
	 * 
	 * @param groupName
	 * @return
	 * @throws SiteWhereException
	 */
	public List<IUser> getGroupUsers(String groupName) throws SiteWhereException;

	/**
	 * Remove a user to one or more groups.
	 * 
	 * @param username
	 * @param groupNames
	 * @return
	 * @throws SiteWhereException
	 */
	public List<IGroup> addUserToGroups(String username, List<String> groupNames)
			throws SiteWhereException;

	/**
	 * Remove a user from one or more groups.
	 * 
	 * @param username
	 * @param groupNames
	 * @return
	 * @throws SiteWhereException
	 */
	public List<IGroup> removeUserFromGroups(String username, List<String> groupNames)
			throws SiteWhereException;

	/**
	 * Remove users from a group.
	 * 
	 * @param groupName
	 * @param usernames
	 * @return
	 * @throws SiteWhereException
	 */
	public List<IUser> removeUsersFromGroup(String groupName, List<String> usernames)
			throws SiteWhereException;

	/**
	 * Get the list of granted authorities for a group.
	 * 
	 * @param groupName
	 * @return
	 * @throws SiteWhereException
	 */
	public List<IGrantedAuthority> getGroupGrantedAuthorities(String groupName)
			throws SiteWhereException;

	/**
	 * Add granted authorities for a group.
	 * 
	 * @param groupName
	 * @param authorities
	 * @return
	 * @throws SiteWhereException
	 */
	public List<IGrantedAuthority> addGroupGrantedAuthorities(String groupName,
			List<String> authorities) throws SiteWhereException;

	/**
	 * Remove a granted authority from a group.
	 * 
	 * @param groupName
	 * @param authorities
	 * @return
	 * @throws SiteWhereException
	 */
	public List<IGrantedAuthority> removeGroupGrantedAuthorities(String groupName,
			List<String> authorities) throws SiteWhereException;

	/**
	 * List all groups.
	 * 
	 * @return
	 * @throws SiteWhereException
	 */
	public List<IGroup> listGroups() throws SiteWhereException;

	/**
	 * Delete a group by unique name.
	 * 
	 * @param name
	 * @throws SiteWhereException
	 */
	public void deleteGroup(String name) throws SiteWhereException;

	/**
	 * Create a new granted authority.
	 * 
	 * @param auth
	 * @throws SiteWhereException
	 */
	public IGrantedAuthority createGrantedAuthority(IGrantedAuthority auth)
			throws SiteWhereException;

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