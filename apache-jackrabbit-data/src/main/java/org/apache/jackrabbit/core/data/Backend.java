package org.apache.jackrabbit.core.data;

import java.io.File;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import com.newrelic.api.agent.DatastoreParameters;
import com.newrelic.api.agent.NewRelic;
import com.newrelic.api.agent.Trace;
import com.newrelic.api.agent.weaver.MatchType;
import com.newrelic.api.agent.weaver.Weave;
import com.newrelic.api.agent.weaver.Weaver;
import com.newrelic.instrumentation.labs.jackrabbit.data.Util;

@Weave(type = MatchType.Interface)
public abstract class Backend {

	@Trace(dispatcher = true)
	public Set<DataIdentifier> deleteAllOlderThan(long timestamp)
			throws DataStoreException{

		Set<DataIdentifier> result = null;
		NewRelic.getAgent().getTracedMethod().setMetricName(new String[] {"Custom", "JackRabbit", "Backend", getClass().getSimpleName(), "deleteAllOlderThan"});

		try {
			result= Weaver.callOriginal();
		} catch (Exception e) {
			if(DataStoreException.class.isInstance(e)) {
				NewRelic.noticeError(e);
				throw (DataStoreException)e;
			}

		}
		return result;
	}


	@Trace(dispatcher = true)
	public void deleteRecord(DataIdentifier identifier) throws DataStoreException{

		NewRelic.getAgent().getTracedMethod().setMetricName(new String[] {"Custom", "JackRabbit", "Backend", getClass().getSimpleName(), "deleteRecord"});
		try {
			Weaver.callOriginal();
		} catch (Exception e) {
			if(DataStoreException.class.isInstance(e)) {
				NewRelic.noticeError(e);
				throw (DataStoreException)e;
			}

		}
	}

	@Trace(dispatcher = true)
	public InputStream read(DataIdentifier identifier) throws DataStoreException{

		InputStream result = null;
		NewRelic.getAgent().getTracedMethod().setMetricName(new String[] {"Custom", "JackRabbit", "Backend", getClass().getSimpleName(), "read"});
		try {
			result= Weaver.callOriginal();
		} catch (Exception e) {
			if(DataStoreException.class.isInstance(e)) {
				NewRelic.noticeError(e);
				throw (DataStoreException)e;
			}

		}
		return result;
	}

	@Trace(dispatcher = true)
	public void write(DataIdentifier identifier, File file) throws DataStoreException{

		Map<String, Object> attrs = new HashMap<>();
		NewRelic.getAgent().getTracedMethod().setMetricName(new String[] {"Custom", "JackRabbit", "Backend", getClass().getSimpleName(), "write"});
		try {
			if (file != null ) {
				Util.recordValue(attrs, "file.name",file.getName());
				Util.recordValue(attrs, "file.path",file.getPath());

			}
			if ( attrs != null ) {
				NewRelic.getAgent().getTracedMethod().addCustomAttributes(attrs);
			}
		}
		catch (Exception e) {
			Util.handleException(getClass().getSimpleName(),"error evaluating write", e);

		}
		try {
			Weaver.callOriginal();
		} catch (Exception e) {
			if(DataStoreException.class.isInstance(e)) {
				NewRelic.noticeError(e);
				throw (DataStoreException)e;
			}

		}
	}

	@Trace(dispatcher = true)
	public void touch(final DataIdentifier identifier, long minModifiedDate)
			throws DataStoreException{

		NewRelic.getAgent().getTracedMethod().setMetricName(new String[] {"Custom", "JackRabbit", "touch", getClass().getSimpleName(), "deleteRecord"});
		try {
			Weaver.callOriginal();
		} catch (Exception e) {
			if(DataStoreException.class.isInstance(e)) {
				NewRelic.noticeError(e);
				throw (DataStoreException)e;
			}

		}
	}


	// Async Methods with no @Trace
	public void touchAsync( DataIdentifier identifier, long minModifiedDate,
			AsyncTouchCallback callback) throws DataStoreException{
		DatastoreParameters dsParams = DatastoreParameters.product("Custom/JackRabbit/Backend").collection(identifier.toString()).operation("touchAsync").noInstance().noDatabaseName().build();


		if(callback != null) {
			if(callback.segment == null) {
				String opName = "touchAsync";
				callback.segment = NewRelic.getAgent().getTransaction().startSegment("Custom/JackRabbit/Backend-"+opName+"-"+identifier.toString());
			}
			callback.params = dsParams;
		}
		try {
			Weaver.callOriginal();
		} catch (Exception e) {
			if(DataStoreException.class.isInstance(e)) {
				NewRelic.noticeError(e);
				throw (DataStoreException)e;

			}
		}

	}

	public  void writeAsync(DataIdentifier identifier, File file,
			AsyncUploadCallback callback) throws DataStoreException{
		DatastoreParameters dsParams = DatastoreParameters.product("Custom/JackRabbit/Backend").collection(identifier.toString()).operation("writeAsync").noInstance().noDatabaseName().build();


		if(callback != null) {
			if(callback.segment == null) {
				String opName = "writeAsync";
				callback.segment = NewRelic.getAgent().getTransaction().startSegment("Custom/JackRabbit/Backend-"+opName+"-"+identifier.toString());
			}
			callback.params = dsParams;
		}
		try {
			Weaver.callOriginal();
		} catch (Exception e) {
			if(DataStoreException.class.isInstance(e)) {
				NewRelic.noticeError(e);
				throw (DataStoreException)e;

			}
		}

	}
}