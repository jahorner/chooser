package com.horner.chooser.controller;

import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.horner.chooser.service.impl.CookieService;
import com.horner.chooser.service.impl.DateService;
import com.horner.chooser.service.impl.DinnerService;
import com.horner.chooser.service.impl.RestaurantService;

@Controller
public class ManageController {
	
	@Autowired CookieService cookieService;
	@Autowired DinnerService dinnerService;
	@Autowired RestaurantService restaurantService;
	@Autowired DateService dateService;

	
	@RequestMapping("/manage")
	public String manage(Map<String, Object> model) {
		model.put("list", cookieService.getAll());
		return "manage";
	}
	
	@RequestMapping("/manage/list/{type}")
	public @ResponseBody String typeList(@PathVariable String type) {
		String listStr = "";
		switch(type.toLowerCase()) {
			case "cookie": 
				listStr = StringUtils.join(cookieService.getAll(), "|");
				break;
			case "dinner":
				listStr = StringUtils.join(dinnerService.getAll(), "|");
				break;
			case "restaurant":
				listStr = StringUtils.join(restaurantService.getAll(), "|");
				break;
			case "date":
				listStr = StringUtils.join(dateService.getAll(), "|");
				break;
			default:
				System.out.println("Could Not Match "+type);
				listStr = "Could Not Match";
		}
		return listStr;
	}
	
	@RequestMapping("/manage/add/{type}/{item}")
	public @ResponseBody String addItem(@PathVariable String type, @PathVariable String item) {
		String error = "";
		switch(type.toLowerCase()) {
			case "cookie": 
				cookieService.add(item);
				break;
			case "dinner":
				dinnerService.add(item);
				break;
			case "restaurant":
				restaurantService.add(item);
				break;
			case "date":
				dateService.add(item);
				break;
			default:
				System.out.println("Could Not Match "+type);
				error = "Could Not Match";
		}
		return error;
	}
	
	@RequestMapping("/manage/remove/{type}/{item}")
	public @ResponseBody String removeItem(@PathVariable String type, @PathVariable String item) {
		String error = "";
		switch(type.toLowerCase()) {
			case "cookie": 
				cookieService.remove(item);
				break;
			case "dinner":
				dinnerService.remove(item);
				break;
			case "restaurant":
				restaurantService.remove(item);
				break;
			case "date":
				dateService.remove(item);
				break;
			default:
				System.out.println("Could Not Match "+type);
				error = "Could Not Match";
		}
		return error;
	}
	

}
