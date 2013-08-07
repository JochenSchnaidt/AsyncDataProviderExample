package me.schnaidt.example.gwt.server;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import me.schnaidt.example.gwt.client.DataService;
import me.schnaidt.example.gwt.client.dto.LoadResult;
import me.schnaidt.example.gwt.client.i18n.DataStoreConstants;
import me.schnaidt.example.gwt.client.ui.Model;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

/**
 * The server side implementation of the RPC service.
 */
@SuppressWarnings("serial")
public class DataServiceImpl extends RemoteServiceServlet implements DataService {

	String dataStoreName = "";

	boolean isAscending = false;

	private List<Model> tempList = new ArrayList<Model>();

	
	
	public DataServiceImpl() {
		super();

		DbMockServiceImpl dbMock = new DbMockServiceImpl();
	
			tempList.addAll(dbMock.getList());


	}



	@Override
	public LoadResult loadData(int start, int length, final String pDataStoreName, final boolean pIsAscending, boolean newSearch) {

		System.out.println("loadData called!");
		System.out.println("Flag 'newSearch' is: " + newSearch);
		
		
		LoadResult result = new LoadResult();

		if (newSearch) {

			result.setGoToFirstPage(true);

		}

		// Sorting

		if (!dataStoreName.equals(pDataStoreName) || isAscending != pIsAscending) {

			System.out.println("Sorting criterias are different");
			System.out.println("Old dataStoreName: " + dataStoreName + ", isAscending: " + isAscending);
			System.out.println("New dataStoreName: " + pDataStoreName + ", isAscending: " + pIsAscending);
			System.out.println("List gets sorted");
			
			
			Collections.sort(tempList, new Comparator<Model>() {

				@Override
				public int compare(Model o1, Model o2) {

					// Compare the name columns.
					int diff = -1;

					// Sehr spezielles compare das einzig die beiden
					// sortierbaren Spalten miteinander vergleicht .

					if (pDataStoreName.equals(DataStoreConstants.getIdColumn())) {

						diff = (o2 != null) ? new Integer(o1.getID()).compareTo(new Integer(o2.getID())) : 1;

					} else if (pDataStoreName.equals(DataStoreConstants.getValueColumn())) {

						diff = (o2 != null) ? o1.getValue().compareTo(o2.getValue()) : 1;

					} else if (pDataStoreName.equals(DataStoreConstants.getStatusColumn())) {

						diff = (o2 != null) ? o1.getStatus().compareTo(o2.getStatus()) : 1;

					}

					return pIsAscending ? diff : -diff;
				}
			});

			dataStoreName = pDataStoreName;
			isAscending = pIsAscending;

			result.setGoToFirstPage(true);

		}

		if (tempList.isEmpty()) {
			
			result.setCount(0);
			result.setGoToFirstPage(true);
			
		} else {

			@SuppressWarnings("rawtypes")
			List sublist = new ArrayList();

			if (newSearch) {
				start = 0;
			}

			int temp = start + length;

			if (temp > tempList.size()) {
				sublist = new ArrayList<Model>(tempList.subList(start, tempList.size()));
			} else {
				sublist = new ArrayList<Model>(tempList.subList(start, temp));
			}

			result.setResults(sublist);
			result.setCount(tempList.size());

		}

		return result;

	} // end search()

}
