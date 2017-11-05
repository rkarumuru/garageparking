package com.parking.garage.controller;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Produces;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import com.parking.garage.data.BeaconDetails;
import com.parking.garage.data.RateDetails;
import com.parking.garage.data.Vehicle;
import com.parking.garage.service.ParkingService;
import com.parking.garage.service.VehicleService;
import com.parking.garage.view.BeaconDetailsView;
import com.parking.garage.view.BeanconView;
import com.parking.garage.view.ParkingForm;

@Controller
public class BeaconController {
	
	private static Logger LOG = Logger.getLogger(BeaconController.class.getName()); 
	private VehicleService vehicleService;

	private ParkingService parkingService;

	public static final String BIND_ATTR = "parkingForm";

	public static final String content_type = "application/json; charset=UTF-8";
	
	@Autowired(required=true)
	@Qualifier(value="vehicleService")
	public void setVehicleService(VehicleService vehicleService) {
		this.vehicleService = vehicleService;
	}

	@Autowired(required = true)
	@Qualifier(value = "parkingService")
	public void setParkingService(ParkingService parkingService) {
		this.parkingService = parkingService;
	}
	
	@GET
	@Produces("application/json")
	@RequestMapping("/getBeaconList/{userId}")
	@ResponseStatus(value = org.springframework.http.HttpStatus.CREATED)
	@ResponseBody public ModelAndView getBeaconsListByUserId(@PathVariable("userId") String userId) {
		LOG.info("Received request for UserID: " + userId);
		List<BeaconDetails> beaconsList = (List<BeaconDetails>)vehicleService.getBeaconsListByUserId(userId);
		
		BeanconView beaconView = new BeanconView();
		
		List<BeaconDetailsView> beaconDetailsList = new ArrayList<BeaconDetailsView>();
		
		if (!CollectionUtils.isEmpty(beaconsList)) {
			
			for(BeaconDetails beaconDetails: beaconsList) {
				
				BeaconDetailsView beaconDetailsView = new BeaconDetailsView();
				beaconDetailsView.setBeaconId(beaconDetails.getBeaconId());
				beaconDetailsView.setUserId(beaconDetails.getUserId());
				beaconDetailsView.setUserName(beaconDetails.getUserName());
				beaconDetailsView.setVehicleId(beaconDetails.getVehicleId());
				beaconDetailsView.setBeaconType(beaconDetails.getBeaconType());
				
				beaconDetailsList.add(beaconDetailsView);
		
			}
			
		}
		
		beaconView.setBeaconsView(beaconDetailsList);
		
		return new ModelAndView("beaconView", "beaconView", beaconView);
		
	}
	
	
	
	@Produces("application/json")
	@RequestMapping(value = "/getActiveSessionsList", method = RequestMethod.POST, consumes = content_type)
	@ResponseStatus(value = org.springframework.http.HttpStatus.CREATED)
	@ResponseBody public ModelAndView getActiveSessionsListByUserId(@RequestBody ParkingForm parkingForm) {
		LOG.info("Received request for UserID: " + parkingForm.getUserId());
		List<Vehicle> vehiclesList = (List<Vehicle>)vehicleService.getActiveBeaconsListByUserId(parkingForm.getUserId());
		
		BeanconView beaconView = new BeanconView();
		
		if (!CollectionUtils.isEmpty(vehiclesList)) {
			
			beaconView.setActiveSessions(true);
			
		}
		
		List<BeaconDetailsView> beaconDetailsList = new ArrayList<BeaconDetailsView>();
		
		if (beaconView.isActiveSessions()) {
		
			if (!CollectionUtils.isEmpty(vehiclesList)) {
				
			for(Vehicle vehicle: vehiclesList) {
				
				BeaconDetailsView beaconDetailsView = new BeaconDetailsView();
				
				beaconDetailsView.setSessionId(vehicle.getSessionId());
				beaconDetailsView.setBeaconId(vehicle.getTagId());
				beaconDetailsView.setStatus(vehicle.getStatus());
			
			
				beaconDetailsList.add(beaconDetailsView);
					
				}
				
		    }
			
		 }  else {
			 
			 List<BeaconDetails> beaconsList = (List<BeaconDetails>)vehicleService.getBeaconsListByUserId(parkingForm.getUserId());
				
			 
			 if (!CollectionUtils.isEmpty(beaconsList)) {
					
					for(BeaconDetails beaconDetails: beaconsList) {
						
						BeaconDetailsView beaconDetailsView = new BeaconDetailsView();
						beaconDetailsView.setBeaconId(beaconDetails.getBeaconId());
						beaconDetailsView.setUserId(beaconDetails.getUserId());
						beaconDetailsView.setUserName(beaconDetails.getUserName());
						beaconDetailsView.setVehicleId(beaconDetails.getVehicleId());
						beaconDetailsView.setBeaconType(beaconDetails.getBeaconType());
						
						beaconDetailsList.add(beaconDetailsView);
				
					}
					
				}
		 }
		
		beaconView.setBeaconsView(beaconDetailsList);
		
		return new ModelAndView("beaconView", "beaconView", beaconView);
		
	}
	

}
