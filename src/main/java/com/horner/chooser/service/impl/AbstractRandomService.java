package com.horner.chooser.service.impl;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.text.WordUtils;

import com.horner.chooser.dao.impl.ChooserFileReader;
import com.horner.chooser.service.RandomService;

public abstract class AbstractRandomService implements RandomService<String> {

	private ChooserFileReader reader = new ChooserFileReader();
	
	public abstract String getFilePath();

	public String getRandom() {
		try {
			List<String> allItems = reader.readFile(getFilePath());
			Random r = new Random();
			return allItems.get(r.nextInt(allItems.size())).trim();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public List<String> getAll() {
		try {
			List<String> allItems = reader.readFile(getFilePath());
			return trimArrayAndSort(allItems);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public void add(String item) {
		try {
			item = WordUtils.capitalize(item.toLowerCase());
			List<String> allItems = reader.readFile(getFilePath());
			allItems.add(item);
			reader.saveFile(trimArrayAndSort(allItems), getFilePath());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void remove(String item) {
		try {
			List<String> allItems = reader.readFile(getFilePath());
			Iterator<String> iter = allItems.iterator();
			while(iter.hasNext()) {
				String next = iter.next().trim();
				if(StringUtils.equalsIgnoreCase(next, item.trim())) {
					iter.remove();
				}
			}
			reader.saveFile(trimArrayAndSort(allItems), getFilePath());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private List<String> trimArrayAndSort(List<String> toTrim){
		ArrayList<String> newList = new ArrayList<String>();
		for(String s : toTrim) {
			newList.add(s.trim());
		}
		newList.sort(new Comparator<String>() {
			@Override
			public int compare(String o1, String o2) {
				return StringUtils.compare(o1, o2);
			}
		});
		return newList;
	}
}
