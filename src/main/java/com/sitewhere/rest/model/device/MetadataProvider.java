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

import java.util.ArrayList;
import java.util.List;

import com.sitewhere.spi.device.IMetadataEntry;
import com.sitewhere.spi.device.IMetadataProvider;

/**
 * Holds arbitrary metadata associated with an entity.
 * 
 * @author dadams
 */
public class MetadataProvider implements IMetadataProvider {

	/** List of metadata entries */
	private List<MetadataEntry> entries = new ArrayList<MetadataEntry>();

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.sitewhere.spi.device.IMetadataProvider#addOrReplaceMetadata(java.lang.String,
	 * java.lang.String)
	 */
	public void addOrReplaceMetadata(String name, String value) {
		for (MetadataEntry entry : entries) {
			if (entry.getName().equals(name)) {
				entry.setValue(value);
				return;
			}
		}
		MetadataEntry entry = new MetadataEntry();
		entry.setName(name);
		entry.setValue(value);
		entries.add(entry);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.sitewhere.spi.device.IMetadataProvider#removeMetadata(java.lang.String)
	 */
	public IMetadataEntry removeMetadata(String name) {
		MetadataEntry toRemove = null;
		for (MetadataEntry entry : entries) {
			if (entry.getName().equals(name)) {
				toRemove = entry;
				break;
			}
		}
		if (toRemove != null) {
			entries.remove(toRemove);
		}
		return toRemove;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.sitewhere.spi.device.IMetadataProvider#getMetadata(java.lang.String)
	 */
	public IMetadataEntry getMetadata(String name) {
		for (MetadataEntry entry : entries) {
			if (entry.getName().equals(name)) {
				return entry;
			}
		}
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.sitewhere.spi.device.IMetadataProvider#getMetadata()
	 */
	public List<IMetadataEntry> getMetadata() {
		List<IMetadataEntry> results = new ArrayList<IMetadataEntry>();
		results.addAll(entries);
		return results;
	}
	
	public void setMetadata(List<MetadataEntry> entries) {
		this.entries = entries;
	}

	/**
	 * Copy contents of one metadata provider to another.
	 * 
	 * @param source
	 * @param target
	 */
	public static void copy(IMetadataProvider source, MetadataProvider target) {
		List<IMetadataEntry> sourceEntries = source.getMetadata();
		for (IMetadataEntry entry : sourceEntries) {
			target.addOrReplaceMetadata(entry.getName(), entry.getValue());
		}
	}
}