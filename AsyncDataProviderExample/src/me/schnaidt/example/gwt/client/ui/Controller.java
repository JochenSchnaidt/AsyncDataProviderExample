package me.schnaidt.example.gwt.client.ui;

import java.util.ArrayList;
import java.util.List;

import me.schnaidt.example.gwt.client.DataService;
import me.schnaidt.example.gwt.client.DataServiceAsync;
import me.schnaidt.example.gwt.client.dto.LoadResult;
import me.schnaidt.example.gwt.client.i18n.ExampleConstants;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.cellview.client.ColumnSortList;
import com.google.gwt.user.cellview.client.ColumnSortList.ColumnSortInfo;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.view.client.AsyncDataProvider;
import com.google.gwt.view.client.HasData;
import com.google.gwt.view.client.Range;

public class Controller {

	public static final ExampleConstants CONSTANTS = (ExampleConstants) GWT.create(ExampleConstants.class);

	private final DataServiceAsync dataService = GWT.create(DataService.class);

	private AsyncDataProvider<Model> provider;
	private List<Model> dataList = new ArrayList<Model>();

	private View view;

	boolean newSearch = false;
	boolean firstRun = true;

	int count;

	public Controller(final View pview) {
		super();
		this.view = pview;

		// Associate an async data provider to the table
		provider = new AsyncDataProvider<Model>() {

			@Override
			protected void onRangeChanged(HasData<Model> display) {
				final int start = display.getVisibleRange().getStart();
				int length = display.getVisibleRange().getLength();

				// Get the ColumnSortInfo from the table.
				final ColumnSortList sortList = view.cellTable.getColumnSortList();
				final ColumnSortInfo sortInfo = sortList.get(0);
				final String dataStoreName = sortInfo.getColumn().getDataStoreName();
				final boolean isAscending = sortInfo.isAscending();

				AsyncCallback<LoadResult> callback = new AsyncCallback<LoadResult>() {
					@Override
					public void onFailure(Throwable caught) {
						if (!GWT.isProdMode()) {
							GWT.log(this.getClass() + ": Remote Procedure Call - Failure");
							GWT.log(caught.getMessage());
						} else {

							Window.alert(caught.getMessage());
						}
					}

					@Override
					public void onSuccess(LoadResult data) {

						if (!GWT.isProdMode()) {
							GWT.log(this.getClass() + ": Remote Procedure Call - Success");
							GWT.log(this.getClass() + ": No. of results = " + data.getCount());
						}

						if (data.getCount() != 0) {

							dataList.addAll(data.getResults());

							updateRowData(start, data.getResults());
							updateRowCount(data.getCount(), true);

							count = data.getCount();

							if (data.isGoToFirstPage()) {
								view.setPagerFirstPage();
							}

						} else {

							dataList.clear();
							updateRowData(start, dataList);
							updateRowCount(data.getCount(), true);

							count = data.getCount();

						}
						newSearch = false;
					}
				};

				if (!firstRun) {
					dataService.loadData(start, length, dataStoreName, isAscending, newSearch, callback);
				}
			}
		};
		
	} // end Controller()

	void load() {

		newSearch = true;
		firstRun = false;

		Range range = view.cellTable.getVisibleRange();
		view.cellTable.setVisibleRangeAndClearData(range, true);

	} // end load()

	/**
	 * @return the provider
	 */
	public AsyncDataProvider<Model> getProvider() {
		return provider;
	}

}
