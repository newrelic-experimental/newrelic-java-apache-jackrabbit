package com.newrelic.instrumentation.labs.jackrabbit.webdav;

import java.util.Enumeration;
import java.util.Map;
import java.util.logging.Level;

import org.apache.jackrabbit.webdav.WebdavRequest;

import com.newrelic.api.agent.NewRelic;

public class Util {


	public static void recordValue(Map<String,Object> attributes, String key, Object value) {
		if(key != null && !key.isEmpty() && value != null) {
			attributes.put(key, value);
		}
	}


	public static void recordRequest(Map<String, Object> attributes, WebdavRequest request) {


		if (request != null) {
			Enumeration<String> attributeNamesIterator = request.getAttributeNames();


			while (attributeNamesIterator.hasMoreElements()) {
				String attributeName = attributeNamesIterator.nextElement();
				Object value = request.getAttribute(attributeName);

				if (value != null) {
					// If you want to include the attribute name in the recorded value
					Util.recordValue(attributes, "request.attribute." + attributeName, value);
				} else {
					// If the attribute value is null, you can skip recording it or handle it as needed
				}

			}
		}
	}


	public static  void handleException(String className, String message, Throwable e) {
		//NewRelic.getAgent().getLogger().log(Level.INFO, "Custom" + className  +" Instrumentation - " + message);
		NewRelic.getAgent().getLogger().log(Level.FINER, "Custom" + className +" Instrumentation - " + message + ": " + e.getMessage());
	}

}
