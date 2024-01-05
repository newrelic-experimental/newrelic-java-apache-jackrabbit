package org.apache.jackrabbit.webdav.server;



import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

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



@Weave(type = MatchType.BaseClass)
abstract public class AbstractWebdavServlet {


	@Trace(dispatcher = true)
	protected boolean execute(WebdavRequest request, WebdavResponse response,
			int method, DavResource resource)
					throws ServletException, IOException, DavException  {

		Map<String, Object> attrs = new HashMap<>();
		boolean result = false;

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
			Util.handleException(getClass().getSimpleName(),"error evaluating execute", e);

		}
		try {
			result = Weaver.callOriginal();
		} catch (Exception e) {
			if(ServletException.class.isInstance(e)) {
				NewRelic.noticeError(e);
				throw (ServletException)e;
			}
			else if(IOException.class.isInstance(e)) {
				NewRelic.noticeError(e);
				throw (IOException)e;
			}
			else if(DavException.class.isInstance(e)) {
				NewRelic.noticeError(e);
				throw (DavException)e;
			}
		}
		return result;
	}


}
