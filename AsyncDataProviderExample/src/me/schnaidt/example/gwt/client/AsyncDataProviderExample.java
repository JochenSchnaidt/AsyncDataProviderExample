package me.schnaidt.example.gwt.client;

import me.schnaidt.example.gwt.client.ui.Controller;
import me.schnaidt.example.gwt.client.ui.View;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.RootPanel;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class AsyncDataProviderExample implements EntryPoint {


	/**
	 * This is the entry point method.
	 */
	public void onModuleLoad() {
		
		View view = new View();

		
		Controller controller = new Controller(view);
		
		view.setController(controller);
		
		controller.getProvider().addDataDisplay(view.getCellTable());
		

		
		RootPanel.get().add(view);

	}
}
