/*
* $Id$
* --------------------------------------------------------------------------------------
* Copyright (c) Reveal Technologies, LLC. All rights reserved. http://www.reveal-tech.com
*
* The software in this package is published under the terms of the CPAL v1.0
* license, a copy of which has been included with this distribution in the
* LICENSE.txt file.
*/

package com.sitewhere.spi.mule;

import org.mule.api.MuleEvent;

import com.sitewhere.rest.service.SiteWhereClient;
import com.sitewhere.spi.ISiteWhereContext;
import com.sitewhere.spi.SiteWhereException;

/**
 * Delegate that allows SiteWhere functionality to be executed before and after an ESB operation.
 * 
 * @author dadams
 */
public interface ISiteWhereDelegate {

	/**
	 * Called before an ESB operation is executed.
	 * 
	 * @param context
	 * @param client
	 * @throws SiteWhereException
	 */
	public void beforeOperation(ISiteWhereContext context, SiteWhereClient client, MuleEvent event)
			throws SiteWhereException;

	/**
	 * Called before an ESB operation is executed.
	 * 
	 * @param context
	 * @param client
	 * @throws SiteWhereException
	 */
	public void afterOperation(ISiteWhereContext context, SiteWhereClient client, MuleEvent event)
			throws SiteWhereException;
}