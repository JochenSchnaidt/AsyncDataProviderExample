package me.schnaidt.example.gwt.server;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import me.schnaidt.example.gwt.client.ui.Model;
import me.schnaidt.example.gwt.client.ui.Status;

public class DbMockServiceImpl implements DbMockService {

	@Override
	public List<Model> getList() {

		List<Model> result = new ArrayList<Model>();

		for (int i = 1; i <= 250; i++) {
			Model model = new Model();
			model.setID(i);
			model.setValue(randomString(10));
			model.setStatus(Status.NEW);
			result.add(model);
		}

		return result;
	}

	static final String AB = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	static Random rnd = new Random();

	String randomString(int len) {
		StringBuilder sb = new StringBuilder(len);
		for (int i = 0; i < len; i++)
			sb.append(AB.charAt(rnd.nextInt(AB.length())));
		return sb.toString();
	}
}
