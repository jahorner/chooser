package com.horner.chooser.service.impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.horner.chooser.service.RandomService;

@Service
public class DateService extends AbstractRandomService implements RandomService<String> {
	
//	private static final String FILE_PATH = "C:/Users/Jim/dates.txt";
	@Value("${dates.path}") private String datePath; 
	
	@Override
	public String getFilePath() {
		return datePath;
	}
}