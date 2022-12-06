package com.horner.chooser.service.impl;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.horner.chooser.service.RandomService;

@Service
public class CookieService extends AbstractRandomService implements RandomService<String> {
	
	private static final Logger LOG = LoggerFactory.getLogger(CookieService.class);
	
//	private static final String FILE_PATH = "C:/Users/Jim/cookies.txt";
	@Value("${cookies.path}") private String cookiePath; 
	
	@PostConstruct
	public void postConstruct() {
		LOG.info("Cookie Path: "+cookiePath);
	}
	
	@Override
	public String getFilePath() {
		return cookiePath;
	}
}
