package me.schnaidt.example.gwt.client;

import me.schnaidt.example.gwt.client.dto.LoadResult;

import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * The async counterpart of <code>DataService</code>.
 */
public interface DataServiceAsync {

	void loadData(int start, int length, String dataStoreName, boolean isAscending, boolean newSearch, AsyncCallback<LoadResult> callback);
}
