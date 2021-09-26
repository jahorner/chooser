package com.horner.chooser.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.horner.chooser.service.impl.CookieService;
import com.horner.chooser.service.impl.DateService;
import com.horner.chooser.service.impl.DinnerService;
import com.horner.chooser.service.impl.RestaurantService;

@Controller
public class IndexController {
	
	@Autowired CookieService cookieService;
	@Autowired DinnerService dinnerService;
	@Autowired RestaurantService restaurantService;
	@Autowired DateService dateService;

	@RequestMapping("/")
    public String home(Map<String, Object> model) {
        model.put("message", "HowToDoInJava Reader !!");
        return "index";
    }
	
	@RequestMapping("/cookie")
	@ResponseBody
    public String cookie() {
        return cookieService.getRandom();
    }
	
	@RequestMapping("/dinner")
	@ResponseBody
    public String dinner() {
		return dinnerService.getRandom();
    }
	
	@RequestMapping("/restaurant")
	@ResponseBody
    public String restaurant() {
		return restaurantService.getRandom();
    }
	
	@RequestMapping("/date")
	@ResponseBody
    public String date() {
		return dateService.getRandom();
    }
 

}