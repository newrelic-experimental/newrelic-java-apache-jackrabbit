package org.apache.jackrabbit.webdav.server;



import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;

import javax.servlet.ServletException;

import org.apache.jackrabbit.webdav.DavException;
import org.apache.jackrabbit.webdav.DavResource;
import org.apache.jackrabbit.webdav.WebdavRequest;
import org.apache.jackrabbit.webdav.WebdavResponse;

import com.newrelic.api.agent.NewRelic;
import com.newrelic.api.agent.Trace;
import com.newrelic.api.agent.weaver.MatchType;
import com.newrelic.api.agent.weaver.Weave;
import com.newrelic.api.agent.weaver.Weaver;
import com.newrelic.instrumentation.labs.jackrabbit.webdav.Util;



@Weave(originalName = "org.apache.jackrabbit.webdav.server.AbstractWebdavServlet", type = MatchType.BaseClass)
abstract public class AbstractWebdavServlet_instrumentation {


	@Trace(dispatcher = true)
	protected boolean execute(WebdavRequest request, WebdavResponse response,
			int method, DavResource resource)
					throws ServletException, IOException, DavException  {

		Map<String, Object> attrs = new HashMap<>();

		NewRelic.getAgent().getTracedMethod().setMetricName(new String[] {"Custom", "JackRabbit", "AbstractWebdavServlet", getClass().getSimpleName(), "execute"});

		try {

			if (request != null ) {
				Util.recordRequest(attrs, request);

			}

			if (resource != null ) {
				Util.recordValue(attrs, "resource.name", resource.getDisplayName());

			}
			if (response != null ) {
				Util.recordValue(attrs, "response.status", response.getStatus());

			}

			if ( attrs != null ) {
				NewRelic.getAgent().getTracedMethod().addCustomAttributes(attrs);
			}

		}
		catch (Exception e) {
			handleException("error evaluating execute", e);

		}
		return Weaver.callOriginal();
	}



	private void handleException(String message, Throwable e) {
		NewRelic.getAgent().getLogger().log(Level.INFO, "Custom AbstractWebdavServlet Instrumentation - " + message);
		NewRelic.getAgent().getLogger().log(Level.FINER, "Custom AbstractWebdavServlet Instrumentation - " + message + ": " + e.getMessage());
	}
}
