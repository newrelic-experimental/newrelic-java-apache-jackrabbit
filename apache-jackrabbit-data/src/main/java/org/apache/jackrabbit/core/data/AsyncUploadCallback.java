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
public abstract class AsyncUploadCallback {

	@NewField
	public Segment segment = null;

	@NewField
	public DatastoreParameters params = null;
	
	/**
     * Callback method for successful asynchronous upload.
     */
	@Trace(async=true)
	public void onSuccess(AsyncUploadResult result){
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
     * Callback method for failed asynchronous upload.
     */
	@Trace(async=true)
    public void onFailure(AsyncUploadResult result){

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
     * Callback method for aborted asynchronous upload.
     */
	@Trace(async=true)
    public void onAbort(AsyncUploadResult result){

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
