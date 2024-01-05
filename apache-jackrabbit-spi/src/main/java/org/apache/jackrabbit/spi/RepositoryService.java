package org.apache.jackrabbit.spi;



import java.util.HashMap;
import java.util.Map;

import javax.jcr.RepositoryException;

import com.newrelic.api.agent.NewRelic;
import com.newrelic.api.agent.Trace;
import com.newrelic.api.agent.weaver.MatchType;
import com.newrelic.api.agent.weaver.Weave;
import com.newrelic.api.agent.weaver.Weaver;
import com.newrelic.instrumentation.labs.jackrabbit.spi.Util;



@Weave(type = MatchType.Interface)
abstract public class RepositoryService {


	@Trace(dispatcher = true)
	public  void dispose(SessionInfo sessionInfo) throws RepositoryException  {

		Map<String, Object> attrs = new HashMap<>();

		NewRelic.getAgent().getTracedMethod().setMetricName(new String[] {"Custom", "JackRabbit", "RepositoryService", getClass().getSimpleName(), "dispose"});

		try {


			if (sessionInfo != null ) {
				Util.recordValue(attrs, "sessionInfo.workspace", sessionInfo.getWorkspaceName());
			}

			if ( attrs != null ) {
				NewRelic.getAgent().getTracedMethod().addCustomAttributes(attrs);
			}

		}
		catch (Exception e) {
			Util.handleException(getClass().getSimpleName(),"error evaluating dispose", e);

		}
		try {
			Weaver.callOriginal();
		} catch (Exception e) {
			if(RepositoryException.class.isInstance(e)) {
				NewRelic.noticeError(e);
				throw (RepositoryException)e;
			}

		}
	}


	@Trace(dispatcher = true)
	public void submit(Batch batch) {
		Map<String, Object> attrs = new HashMap<>();

		NewRelic.getAgent().getTracedMethod().setMetricName(new String[] {"Custom", "JackRabbit", "RepositoryService", getClass().getSimpleName(), "submit"});

		try {


			if (batch != null ) {
				Util.recordValue(attrs, "batch.name", batch.toString());
			}

			if ( attrs != null ) {
				NewRelic.getAgent().getTracedMethod().addCustomAttributes(attrs);
			}

		}
		catch (Exception e) {
			Util.handleException(getClass().getSimpleName(),"error evaluating submit", e);

		}
		Weaver.callOriginal();
	}


	@Trace(dispatcher = true)
	public void move(SessionInfo sessionInfo, NodeId srcNodeId, NodeId destParentNodeId, Name destName) {

		Map<String, Object> attrs = new HashMap<>();

		NewRelic.getAgent().getTracedMethod().setMetricName(new String[] {"Custom", "JackRabbit", "RepositoryService", getClass().getSimpleName(), "move"});

		try {


			if (sessionInfo != null ) {
				Util.recordValue(attrs, "sessionInfo.workspace", sessionInfo.getWorkspaceName());
			}

			if (srcNodeId != null ) {
				Util.recordValue(attrs, "node.path", srcNodeId.getPath());
			}

			if (destParentNodeId != null ) {
				Util.recordValue(attrs, "parentnode.path", destParentNodeId.getPath());
			}

			if (destName != null ) {
				Util.recordValue(attrs, "destination.name", destName);
			}



			if ( attrs != null ) {
				NewRelic.getAgent().getTracedMethod().addCustomAttributes(attrs);
			}

		}
		catch (Exception e) {
			Util.handleException(getClass().getSimpleName(),"error evaluating move", e);

		}
		Weaver.callOriginal();
	}

	@Trace(dispatcher = true)
	public void copy(SessionInfo sessionInfo, String srcWorkspaceName, NodeId srcNodeId, NodeId destParentNodeId, Name destName) {

		Map<String, Object> attrs = new HashMap<>();

		NewRelic.getAgent().getTracedMethod().setMetricName(new String[] {"Custom", "JackRabbit", "RepositoryService", getClass().getSimpleName(), "copy"});

		try {


			if (sessionInfo != null ) {
				Util.recordValue(attrs, "sessionInfo.workspace", sessionInfo.getWorkspaceName());
			}

			if (srcNodeId != null ) {
				Util.recordValue(attrs, "node.path", srcNodeId.getPath());
			}

			if (destParentNodeId != null ) {
				Util.recordValue(attrs, "parentnode.path", destParentNodeId.getPath());
			}

			if (destName != null ) {
				Util.recordValue(attrs, "destination.name", destName);
			}



			if ( attrs != null ) {
				NewRelic.getAgent().getTracedMethod().addCustomAttributes(attrs);
			}

		}
		catch (Exception e) {
			Util.handleException(getClass().getSimpleName(),"error evaluating copy", e);

		}
		Weaver.callOriginal();
	}


}
