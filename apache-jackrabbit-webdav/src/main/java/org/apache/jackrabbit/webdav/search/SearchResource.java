package org.apache.jackrabbit.webdav.search;



import java.util.HashMap;
import java.util.Map;

import org.apache.jackrabbit.webdav.DavException;
import org.apache.jackrabbit.webdav.MultiStatus;

import com.newrelic.api.agent.NewRelic;
import com.newrelic.api.agent.Trace;
import com.newrelic.api.agent.weaver.MatchType;
import com.newrelic.api.agent.weaver.Weave;
import com.newrelic.api.agent.weaver.Weaver;
import com.newrelic.instrumentation.labs.jackrabbit.webdav.Util;



@Weave(type = MatchType.Interface)
abstract public class SearchResource {


	@Trace(dispatcher = true)
	public MultiStatus search(SearchInfo sInfo) throws DavException  {

		Map<String, Object> attrs = new HashMap<>();
		MultiStatus result = null;

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
			Util.handleException(getClass().getSimpleName(),"error evaluating search", e);

		}
		try {
			result = Weaver.callOriginal();
		} catch (Exception e) {
			if(DavException.class.isInstance(e)) {
				NewRelic.noticeError(e);
				throw (DavException)e;
			}

		}
		return result;
	}
}

