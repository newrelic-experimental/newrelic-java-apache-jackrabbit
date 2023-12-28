package org.apache.jackrabbit.spi;



import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;

import javax.jcr.AccessDeniedException;
import javax.jcr.ItemExistsException;
import javax.jcr.ItemNotFoundException;
import javax.jcr.NoSuchWorkspaceException;
import javax.jcr.PathNotFoundException;
import javax.jcr.RepositoryException;
import javax.jcr.UnsupportedRepositoryOperationException;
import javax.jcr.ValueFormatException;
import javax.jcr.lock.LockException;
import javax.jcr.nodetype.ConstraintViolationException;
import javax.jcr.nodetype.NoSuchNodeTypeException;
import javax.jcr.version.VersionException;

import com.newrelic.api.agent.NewRelic;
import com.newrelic.api.agent.Trace;
import com.newrelic.api.agent.weaver.MatchType;
import com.newrelic.api.agent.weaver.Weave;
import com.newrelic.api.agent.weaver.Weaver;
import com.newrelic.instrumentation.labs.jackrabbit.spi.Util;



@Weave(originalName = "org.apache.jackrabbit.spi.RepositoryService", type = MatchType.Interface)
abstract public class RepositoryService_instrumentation {


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
			handleException("error evaluating dispose", e);

		}
		Weaver.callOriginal();
	}


	@Trace(dispatcher = true)
	public void submit(Batch batch) throws PathNotFoundException, ItemNotFoundException, NoSuchNodeTypeException, ValueFormatException, VersionException, LockException, ConstraintViolationException, AccessDeniedException, UnsupportedRepositoryOperationException, RepositoryException {

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
			handleException("error evaluating submit", e);

		}
		Weaver.callOriginal();
	}


	@Trace(dispatcher = true)
	public void move(SessionInfo sessionInfo, NodeId srcNodeId, NodeId destParentNodeId, Name destName) throws ItemExistsException, PathNotFoundException, VersionException, ConstraintViolationException, LockException, AccessDeniedException, UnsupportedRepositoryOperationException, RepositoryException{

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
			handleException("error evaluating move", e);

		}
		Weaver.callOriginal();
	}

	@Trace(dispatcher = true)
	public void copy(SessionInfo sessionInfo, String srcWorkspaceName, NodeId srcNodeId, NodeId destParentNodeId, Name destName) throws NoSuchWorkspaceException, ConstraintViolationException, VersionException, AccessDeniedException, PathNotFoundException, ItemExistsException, LockException, UnsupportedRepositoryOperationException, RepositoryException
	{

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
			handleException("error evaluating copy", e);

		}
		Weaver.callOriginal();
	}




	private void handleException(String message, Throwable e) {
		NewRelic.getAgent().getLogger().log(Level.INFO, "Custom SearchResource Instrumentation - " + message);
		NewRelic.getAgent().getLogger().log(Level.FINER, "Custom SearchResource Instrumentation - " + message + ": " + e.getMessage());
	}
}
