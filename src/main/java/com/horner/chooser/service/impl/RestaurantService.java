package com.horner.chooser.service.impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.horner.chooser.service.RandomService;

@Service
public class RestaurantService extends AbstractRandomService implements RandomService<String> {
	
	@Value("${restaurants.path}") private String restaurantPath; 
	
	@Override
	public String getFilePath() {
		return restaurantPath;
	}
}