package org.apache.jackrabbit.core.data;

import com.newrelic.api.agent.DatastoreParameters;
import com.newrelic.api.agent.NewRelic;
import com.newrelic.api.agent.Segment;
import com.newrelic.api.agent.Trace;
import com.newrelic.api.agent.weaver.MatchType;
import com.newrelic.api.agent.weaver.NewField;
import com.newrelic.api.agent.weaver.Weave;
import com.newrelic.api.agent.weaver.Weaver;

@Weave(type = MatchType.Interface)
public abstract class AsyncTouchCallback {

	@NewField
	public Segment segment = null;

	@NewField
	public DatastoreParameters params = null;


	/**
	 * Callback method for successful asynchronous touch.
	 */
	@Trace(async=true)
	public void onSuccess(AsyncTouchResult result) {
		if(segment != null) {
			if(params != null) {
				segment.reportAsExternal(params);
			}
			segment.end();
			segment = null;
		} else if(params != null) {
			NewRelic.getAgent().getTracedMethod().reportAsExternal(params);
		}
		Weaver.callOriginal();
	}

	/**
	 * Callback method for failed asynchronous touch.
	 */
	@Trace(async=true)
	public void onFailure(AsyncTouchResult result) {

		Exception e = result.getException();

		if(e != null) {
			NewRelic.noticeError(e);
		}

		if(segment != null) {
			if(params != null) {
				segment.reportAsExternal(params);
			}
			segment.end();
			segment = null;
		} else if(params != null) {
			NewRelic.getAgent().getTracedMethod().reportAsExternal(params);
		}
		Weaver.callOriginal();
	}

	/**
	 * Callback method for aborted asynchronous touch.
	 */
	@Trace(async=true)
	public void onAbort(AsyncTouchResult result) {

		Exception e = result.getException();

		if(e != null) {
			NewRelic.noticeError(e);
		}

		if(segment != null) {
			if(params != null) {
				segment.reportAsExternal(params);
			}
			segment.end();
			segment = null;
		} else if(params != null) {
			NewRelic.getAgent().getTracedMethod().reportAsExternal(params);
		}
		Weaver.callOriginal();
	}

}
