package org.apache.jackrabbit.webdav.search;



import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;

import javax.servlet.ServletException;

import org.apache.jackrabbit.webdav.DavException;
import org.apache.jackrabbit.webdav.DavResource;
import org.apache.jackrabbit.webdav.MultiStatus;
import org.apache.jackrabbit.webdav.WebdavRequest;
import org.apache.jackrabbit.webdav.WebdavResponse;

import com.newrelic.api.agent.NewRelic;
import com.newrelic.api.agent.Trace;
import com.newrelic.api.agent.weaver.MatchType;
import com.newrelic.api.agent.weaver.Weave;
import com.newrelic.api.agent.weaver.Weaver;
import com.newrelic.instrumentation.labs.jackrabbit.webdav.Util;



@Weave(originalName = "org.apache.jackrabbit.webdav.search.SearchResource", type = MatchType.Interface)
abstract public class SearchResource_instrumentation {


	@Trace(dispatcher = true)
	public MultiStatus search(SearchInfo sInfo) throws DavException  {

		Map<String, Object> attrs = new HashMap<>();

		NewRelic.getAgent().getTracedMethod().setMetricName(new String[] {"Custom", "JackRabbit", "SearchResource", getClass().getSimpleName(), "search"});

		try {


			if (sInfo != null ) {
				Util.recordValue(attrs, "searchInfo.query", sInfo.getQuery());
				Util.recordValue(attrs, "searchInfo.results", sInfo.getNumberResults());

			}
			
			if ( attrs != null ) {
				NewRelic.getAgent().getTracedMethod().addCustomAttributes(attrs);
			}

		}
		catch (Exception e) {
			handleException("error evaluating search", e);

		}
		return Weaver.callOriginal();
	}



	private void handleException(String message, Throwable e) {
		NewRelic.getAgent().getLogger().log(Level.INFO, "Custom SearchResource Instrumentation - " + message);
		NewRelic.getAgent().getLogger().log(Level.FINER, "Custom SearchResource Instrumentation - " + message + ": " + e.getMessage());
	}
}
