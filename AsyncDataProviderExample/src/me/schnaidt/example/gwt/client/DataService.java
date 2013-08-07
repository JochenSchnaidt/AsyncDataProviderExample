package me.schnaidt.example.gwt.client;

import me.schnaidt.example.gwt.client.dto.LoadResult;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;


/**
 * The client side stub for the RPC service.
 */
@RemoteServiceRelativePath("greet")
public interface DataService extends RemoteService {
		
	public LoadResult loadData(int start, int length, String dataStoreName, boolean isAscending, boolean newSearch);
	
}
