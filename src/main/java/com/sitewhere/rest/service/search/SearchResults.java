/*
* $Id$
* --------------------------------------------------------------------------------------
* Copyright (c) Reveal Technologies, LLC. All rights reserved. http://www.reveal-tech.com
*
* The software in this package is published under the terms of the CPAL v1.0
* license, a copy of which has been included with this distribution in the
* LICENSE.txt file.
*/

package com.sitewhere.rest.service.search;

import java.util.List;

/**
 * Used to return search result metadata in response to AJAX calls.
 * 
 * @author dadams
 */
public class SearchResults<T> {

	/** Number of total results */
	private int numResults;

	/** List of results */
	private List<T> results;

	public SearchResults(List<T> results) {
		setNumResults(results.size());
		setResults(results);
	}

	public int getNumResults() {
		return numResults;
	}

	public void setNumResults(int numResults) {
		this.numResults = numResults;
	}

	public List<T> getResults() {
		return results;
	}

	public void setResults(List<T> results) {
		this.results = results;
	}
}