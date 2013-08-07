package me.schnaidt.example.gwt.client.ui;

import me.schnaidt.example.gwt.client.i18n.DataStoreConstants;
import me.schnaidt.example.gwt.client.i18n.ExampleConstants;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.ColumnSortEvent.AsyncHandler;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.view.client.CellPreviewEvent;
import com.google.gwt.view.client.CellPreviewEvent.Handler;

public class ExampleCellTable extends CellTable<Model> {

//	private static interface GetValue<C> {
//		C getValue(Model model);
//	}

	public static final ExampleConstants CONSTANTS = (ExampleConstants) GWT.create(ExampleConstants.class);

	// needed for addCellPreviewHandler
	ExampleCellTable cellTable;

	public ExampleCellTable( ) {
		super();
		cellTable = this;


		TextColumn<Model> idColumn = new TextColumn<Model>() {
			@Override
			public String getValue(Model model) {
				return String.valueOf(model.getID());
			}
		};
		idColumn.setDataStoreName(DataStoreConstants.getIdColumn());
		idColumn.setSortable(true);
		this.addColumn(idColumn, CONSTANTS.IdLabel());

		TextColumn<Model> valueColumn = new TextColumn<Model>() {
			@Override
			public String getValue(Model model) {
				return model.getValue();
			}
		};
		valueColumn.setDataStoreName(DataStoreConstants.getValueColumn());
		valueColumn.setSortable(true);
		this.addColumn(valueColumn, CONSTANTS.valueLabel());

		TextColumn<Model> statusColumn = new TextColumn<Model>() {
			@Override
			public String getValue(Model model) {
				return model.getStatus().toString();
			}
		};
		statusColumn.setDataStoreName(DataStoreConstants.getStatusColumn());
		statusColumn.setSortable(true);
		this.addColumn(statusColumn, CONSTANTS.statusLabel());

		this.addCellPreviewHandler(new Handler<Model>() {

			@Override
			public void onCellPreview(CellPreviewEvent<Model> event) {
				if ("mouseover".equals(event.getNativeEvent().getType())) {

					if (cellTable.getColumn(event.getColumn()).getDataStoreName().equals(DataStoreConstants.getIdColumn())) {
						// do something useful or not
						System.out.println("Mouseover on: " + CONSTANTS.IdLabel()  );
					} else if (cellTable.getColumn(event.getColumn()).getDataStoreName().equals(DataStoreConstants.getValueColumn())) {
						System.out.println("Mouseover on: " + CONSTANTS.valueLabel()  );
					} else if (cellTable.getColumn(event.getColumn()).getDataStoreName().equals(DataStoreConstants.getStatusColumn())) {
						System.out.println("Mouseover on: " + CONSTANTS.statusLabel()  );
					} else 
					 {
					// eat anything else
					}

				} else if ("mouseout".equals(event.getNativeEvent().getType())) {
					System.out.println("Mouse out");
				}
			}
		});

		AsyncHandler columnSortHandler = new AsyncHandler(this);
		this.addColumnSortHandler(columnSortHandler);

		// We know that the data is sorted alphabetically by default.
		this.getColumnSortList().push(idColumn);

	} // end initCellTable()
}
