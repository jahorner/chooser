package com.horner.chooser.dao.impl;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ChooserFileReader {
	
	public List<String> readFile(String filename) throws IOException{
		
	    Path path = Paths.get(filename);

	    String read = Files.readAllLines(path).get(0);
	    List<String> items = Arrays.asList(read.split(","));
	    ArrayList<String> newItems = new ArrayList<String>(items);
		
		return newItems;
	}
	
	public void saveFile(List<String> items, String filename) {
		String source = items.stream().collect(Collectors.joining(", "));
		try {
		    FileWriter fileWriter = new FileWriter(filename, false);
		    fileWriter.write(source);
		    fileWriter.close();
		} catch (IOException e) {
		    e.printStackTrace();
		}  
	}

}
