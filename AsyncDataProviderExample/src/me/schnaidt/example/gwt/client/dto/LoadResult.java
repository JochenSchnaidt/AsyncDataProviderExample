package me.schnaidt.example.gwt.client.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import me.schnaidt.example.gwt.client.ui.Model;

public class LoadResult implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2108029114141711364L;
	
	private List<Model> results = new ArrayList<Model>();
	private int count;
	private boolean goToFirstPage;

	/**
	 * @return the results
	 */
	public List<Model> getResults() {
		return results;
	}

	/**
	 * @param results
	 *            the results to set
	 */
	public void setResults(List<Model> results) {
		this.results = results;
	}

	/**
	 * @return the count
	 */
	public int getCount() {
		return count;
	}

	/**
	 * @param count
	 *            the count to set
	 */
	public void setCount(int count) {
		this.count = count;
	}

	/**
	 * @return the goToFirstPage
	 */
	public boolean isGoToFirstPage() {
		return goToFirstPage;
	}

	/**
	 * @param goToFirstPage
	 *            the goToFirstPage to set
	 */
	public void setGoToFirstPage(boolean goToFirstPage) {
		this.goToFirstPage = goToFirstPage;
	}

}
