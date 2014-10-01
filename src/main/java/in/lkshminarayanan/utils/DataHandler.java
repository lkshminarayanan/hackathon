package in.lkshminarayanan.utils;

import java.util.List;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.FetchOptions;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.api.datastore.Query.SortDirection;

public class DataHandler {
	
	private DatastoreService datastore;
	
	public DataHandler() {
		datastore = DatastoreServiceFactory.getDatastoreService();
	}
	
	public List<Entity> executeQuery(Query query, int limit, int offset){
		return datastore.prepare(query).asList(FetchOptions.Builder.withLimit(limit).offset(offset));
	}
	
	public List<Entity> executeQuery(Query query){
		//5 is default
		return executeQuery(query, 5, 0);
	}

	public void insert(Entity entity) {
		// inserts an entity into datastore
		datastore.put(entity);
	}
	
	public long getNextAutoIncrementValue(String entityType, String property){
	    Query query = new Query(entityType)
                        .addSort(property, SortDirection.DESCENDING);
	    List<Entity> entities = datastore.prepare(query).asList(FetchOptions.Builder.withLimit(1));
	    if(entities.size() == 0){
	        return 1;
	    } else {
	        return (long)entities.get(0).getProperty(property) + 1;
	    }
	}
}
