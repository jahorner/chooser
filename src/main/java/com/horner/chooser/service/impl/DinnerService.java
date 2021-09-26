package com.horner.chooser.service.impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.horner.chooser.service.RandomService;

@Service
public class DinnerService extends AbstractRandomService implements RandomService<String> {
	
	@Value("${dinners.path}") private String dinnerPath; 
	
	@Override
	public String getFilePath() {
		return dinnerPath;
	}
}