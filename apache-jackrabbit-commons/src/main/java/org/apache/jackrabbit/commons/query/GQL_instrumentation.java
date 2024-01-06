package org.apache.jackrabbit.commons.query;




import java.util.HashMap;
import java.util.Map;

import javax.jcr.Session;
import javax.jcr.query.RowIterator;

import org.apache.jackrabbit.commons.query.GQL.Filter;

import com.newrelic.api.agent.NewRelic;
import com.newrelic.api.agent.Trace;
import com.newrelic.api.agent.weaver.MatchType;
import com.newrelic.api.agent.weaver.Weave;
import com.newrelic.api.agent.weaver.Weaver;
import com.newrelic.instrumentation.labs.jackrabbit.commons.Util;



@Weave(originalName = "org.apache.jackrabbit.commons.query.GQL",type = MatchType.BaseClass)
abstract public class GQL_instrumentation {


	@Trace(dispatcher = true)
	public static RowIterator execute(String statement,
			Session session,
			String commonPathPrefix,
			Filter filter)  {

		Map<String, Object> attrs = new HashMap<>();

		NewRelic.getAgent().getTracedMethod().setMetricName(new String[] {"Custom", "JackRabbit", "GQL", "execute"});


		if (statement != null && !statement.isEmpty()) {
			Util.recordValue(attrs, "statement.value", statement);

		}
		if (filter != null ) {
			Util.recordValue(attrs, "filter.value", filter);

		}

		if ( attrs != null ) {
			NewRelic.getAgent().getTracedMethod().addCustomAttributes(attrs);
		}

		return Weaver.callOriginal();
	}

	@Trace(dispatcher = true)
	public static RowIterator executeXPath(String jcrQuery,
			String jcrQueryLanguage,
			Session session,
			String commonPathPrefix,
			Filter filter) {

		Map<String, Object> attrs = new HashMap<>();

		NewRelic.getAgent().getTracedMethod().setMetricName(new String[] {"Custom", "JackRabbit", "GQL", "executeXPath"});


		if (jcrQuery != null && !jcrQuery.isEmpty()) {
			Util.recordValue(attrs, "jcrQuery.value", jcrQuery);

		}

		if (jcrQueryLanguage != null && !jcrQueryLanguage.isEmpty()) {
			Util.recordValue(attrs, "jcrQueryLanguage.value", jcrQueryLanguage);

		}

		if (filter != null ) {
			Util.recordValue(attrs, "filter.value", filter);

		}

		if ( attrs != null ) {
			NewRelic.getAgent().getTracedMethod().addCustomAttributes(attrs);
		}

		return Weaver.callOriginal();
	}


}
