package me.schnaidt.example.gwt.client.ui;

import me.schnaidt.example.gwt.client.i18n.ExampleConstants;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.cellview.client.SimplePager;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;

public class View extends Composite {

	private static ViewUiBinder uiBinder = GWT.create(ViewUiBinder.class);

	public static final ExampleConstants CONSTANTS = (ExampleConstants) GWT.create(ExampleConstants.class);

	interface ViewUiBinder extends UiBinder<Widget, View> {
	}

	private Controller controller;

	@UiField
	Label introLabel;

	@UiField
	Button loadButton;

	@UiField(provided = true)
	ExampleCellTable cellTable = new ExampleCellTable();

	@UiField
	SimplePager pager;

	public View() {
		initWidget(uiBinder.createAndBindUi(this));

		initStaticWindow();
	}

	private void initStaticWindow() {

		introLabel.setText(CONSTANTS.introLabel());
		loadButton.setText(CONSTANTS.loadButtonLabel());

		pager.setDisplay(cellTable);
		
	} // end initStaticWindow()

	@UiHandler("loadButton")
	void loadButtonClick(ClickEvent event) {

		controller.load();

	} // end loadButtonClick()

	void setPagerFirstPage() {
		pager.firstPage();
	} // end setPagerFirstPage()

	
	/**
	 * @param controller the controller to set
	 */
	public void setController(Controller controller) {
		this.controller = controller;
	}

	/**
	 * @return the cellTable
	 */
	public ExampleCellTable getCellTable() {
		return cellTable;
	}



}
