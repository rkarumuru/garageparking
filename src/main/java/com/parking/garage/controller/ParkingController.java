package com.parking.garage.controller;

import java.lang.reflect.InvocationTargetException;

import javax.ws.rs.GET;
import javax.ws.rs.Produces;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import com.parking.garage.data.RateDetails;
import com.parking.garage.data.Vehicle;
import com.parking.garage.service.ParkingService;
import com.parking.garage.view.ParkingForm;
import com.parking.garage.view.RateDetailsView;
import com.parking.garage.view.VehicleView;

@Controller
public class ParkingController {

	public static final String BIND_ATTR = "parkingForm";

	public static final String content_type = "application/json; charset=UTF-8";

	private ParkingService parkingService;

	private static Logger LOG = Logger.getLogger(ParkingController.class);

	@Autowired(required = true)
	@Qualifier(value = "parkingService")
	public void setParkingService(ParkingService parkingService) {
		this.parkingService = parkingService;
	}

	@Produces("application/json")
	@ResponseStatus(value = org.springframework.http.HttpStatus.CREATED)
	@RequestMapping(value = "/parkVehicle", method = RequestMethod.POST, consumes = content_type)
	@ResponseBody
	public ModelAndView addParkingVehicle(@RequestBody ParkingForm parkingForm) {

		BindingResult bindingResult = new BeanPropertyBindingResult(
				parkingForm, BIND_ATTR);

		// createParkingForm(parkingForm);
		LOG.info("request received for start parking for vehicle: "
				+ parkingForm);

		RateDetails rateDetails = this.parkingService.getParkingTermsInfoByGarageId(parkingForm.getGarageId());

		Vehicle vehicle = this.parkingService.addParkingDetails(parkingForm);

		VehicleView vehicleView = new VehicleView();

		try {
			BeanUtils.copyProperties(vehicleView, vehicle);
		} catch (IllegalAccessException | InvocationTargetException e) {
			LOG.error("Error in copying properties: " + parkingForm.getTagId(),
					e);
		}

		LOG.info("request processed for start parking for vehicle: "
				+ parkingForm.getTagId());
		
		vehicleView.setAddress1(rateDetails.getAddress1());
		vehicleView.setAddress2(rateDetails.getAddress2());
		
		return new ModelAndView("vehicleView", "vehicleView", vehicleView);
	}

	
	@Produces("application/json")
	@ResponseStatus(value = org.springframework.http.HttpStatus.CREATED)
	@RequestMapping(value = "/endParking", method = RequestMethod.POST)
	@ResponseBody
	public ModelAndView updateParkingVehicle(
			@RequestBody ParkingForm parkingForm) {

		LOG.info("request received for end parking for vehicle: "
				+ parkingForm.getTagId());
		// createParkingForm(parkingForm);

		Vehicle vehicle = this.parkingService.updateParkingStatus(parkingForm);

		VehicleView vehicleView = new VehicleView();

		try {
			BeanUtils.copyProperties(vehicleView, vehicle);
		} catch (IllegalAccessException | InvocationTargetException e) {
			LOG.error("Error in copying properties: " + parkingForm.getTagId(),
					e);
		}

		LOG.info("request processed for end parking for vehicle: "
				+ parkingForm.getTagId());
		
		RateDetails rateDetails = this.parkingService.getParkingTermsInfoByGarageId(vehicle.getGarageId());
		
		vehicleView.setAddress1(rateDetails.getAddress1());
		vehicleView.setAddress2(rateDetails.getAddress2());
		
		
		return new ModelAndView("vehicleView", "vehicleView", vehicleView);

	}

	

	@GET
	@Produces("application/json")
	@RequestMapping("/parkingDetails/{sessionId}")
	@ResponseStatus(value = org.springframework.http.HttpStatus.CREATED)
	@ResponseBody
	public ModelAndView getParkingStatusInfo(
			@PathVariable("sessionId") String sessionId) {

		LOG.info("request received for parkingDetails for session: "
				+ sessionId);
		Vehicle vehicle = this.parkingService.getParkingStatusInfo(sessionId);

		VehicleView vehicleView = new VehicleView();

		try {
			BeanUtils.copyProperties(vehicleView, vehicle);
		} catch (IllegalAccessException | InvocationTargetException e) {
			LOG.error("Error in copying properties: " + sessionId, e);
		}
		LOG.info("request processed for parkingDetails for session: "
				+ sessionId);
		
		RateDetails rateDetails = this.parkingService.getParkingTermsInfoByGarageId(vehicle.getGarageId());
		
		vehicleView.setAddress1(rateDetails.getAddress1());
		vehicleView.setAddress2(rateDetails.getAddress2());
		
		return new ModelAndView("vehicleView", "vehicleView", vehicleView);

	}

	@GET
	@Produces("application/json")
	@RequestMapping("/acceptGateDetails")
	@ResponseStatus(value = org.springframework.http.HttpStatus.CREATED)
	@ResponseBody
	public ModelAndView acceptGateDetails(@RequestBody ParkingForm parkingForm) {

		BindingResult bindingResult = new BeanPropertyBindingResult(
				parkingForm, BIND_ATTR);
		LOG.info("Accept location request received for vehicle: " + parkingForm);
		// createParkingForm(parkingForm);
		
		RateDetails rateDetails = this.parkingService.getParkingTermsInfoByGarageId(parkingForm.getGateBeaconId());

		RateDetailsView rateDetailsView = new RateDetailsView();

		try {
			BeanUtils.copyProperties(rateDetailsView, rateDetails);
		} catch (IllegalAccessException | InvocationTargetException e) {
			LOG.error("Error in copying properties: " + parkingForm.getGateBeaconId(),
					e);
		}
		LOG.info("Accept location request processed for vehicle: "
				+ parkingForm);
		return new ModelAndView("rateDetailsView", "rateDetailsView", rateDetailsView);

	}

}
