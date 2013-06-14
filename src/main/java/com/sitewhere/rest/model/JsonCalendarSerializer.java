/*
* $Id$
* --------------------------------------------------------------------------------------
* Copyright (c) Reveal Technologies, LLC. All rights reserved. http://www.reveal-tech.com
*
* The software in this package is published under the terms of the CPAL v1.0
* license, a copy of which has been included with this distribution in the
* LICENSE.txt file.
*/

package com.sitewhere.rest.model;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.JsonSerializer;
import org.codehaus.jackson.map.SerializerProvider;

/**
 * Used for serializing Calendar objects for JSON output.
 * 
 * @author dadams
 */
public class JsonCalendarSerializer extends JsonSerializer<Calendar> {

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.codehaus.jackson.map.JsonSerializer#serialize(java.lang.Object,
	 * org.codehaus.jackson.JsonGenerator, org.codehaus.jackson.map.SerializerProvider)
	 */
	public void serialize(Calendar value, JsonGenerator gen, SerializerProvider arg2) throws IOException,
			JsonProcessingException {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ");
		String formattedDate = formatter.format(value.getTime());
		gen.writeString(formattedDate);
	}
}