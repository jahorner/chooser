package com.horner.chooser.service.impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.horner.chooser.service.RandomService;

@Service
public class CookieService extends AbstractRandomService implements RandomService<String> {
	
//	private static final String FILE_PATH = "C:/Users/Jim/cookies.txt";
	@Value("${cookies.path}") private String cookiePath; 
	
	@Override
	public String getFilePath() {
		return cookiePath;
	}
}
