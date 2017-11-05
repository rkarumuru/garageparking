package com.parking.garage.controller;



import javax.ws.rs.GET;
import javax.ws.rs.Produces;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import com.parking.garage.data.UserDetails;
import com.parking.garage.service.VehicleService;

@Controller
public class VehicleController {
	private static Logger LOG = Logger.getLogger(VehicleController.class.getName()); 
	private VehicleService vehicleService;
	
	@Autowired(required=true)
	@Qualifier(value="vehicleService")
	public void setVehicleService(VehicleService vehicleService) {
		this.vehicleService = vehicleService;
	}

	
	@GET
	@Produces("application/json")
	@RequestMapping("/getVehicleById/{tagId}")
	@ResponseStatus(value = org.springframework.http.HttpStatus.CREATED)
	@ResponseBody public ModelAndView getVehicleByTagId(@PathVariable("tagId") String tagId) {
		LOG.info("Received request for tagID: " + tagId);
		UserDetails userDetails = (UserDetails)vehicleService.getVehicleByTagId(tagId);
		
		return new ModelAndView("userDetails", "userDetails", userDetails);
		
	}
	
	
}
