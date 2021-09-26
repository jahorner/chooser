package com.horner.chooser.service;

import java.util.List;

public interface RandomService<T> {
	
	public T getRandom();
	public List<T> getAll();
	public void add(String item);
	public void remove(String item);
 
}
