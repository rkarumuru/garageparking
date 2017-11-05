package com.parking.garage.service;

import java.lang.reflect.InvocationTargetException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.parking.garage.data.RateDetails;
import com.parking.garage.data.Vehicle;
import com.parking.garage.dataaccess.parking.ParkingDAO;
import com.parking.garage.view.ParkingForm;

public class ParkingServiceImpl implements ParkingService {

	@Autowired(required=true)
	private ParkingDAO parkingDAO;
	
	public void setParkingDAO(ParkingDAO parkingDAO) {
		this.parkingDAO = parkingDAO;
	}

	@Override
	@Transactional
	public Vehicle addParkingDetails(ParkingForm parkingForm) {
		
		Vehicle vehicle = new Vehicle();
		
		try {
			BeanUtils.copyProperties(vehicle, parkingForm);
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		String today = sdf.format(Calendar.getInstance().getTime());
		
		try {
			vehicle.setStartTime(sdf.parse(today));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		vehicle.setStatus("Open");
		vehicle.setUserId(parkingForm.getUserId());
		
		return (Vehicle)parkingDAO.addParkingDetails(vehicle);
	}

	@Override
	@Transactional
	public Vehicle updateParkingStatus(ParkingForm parkingForm) {
		
		Vehicle vehicle = new Vehicle();
		
		try {
			BeanUtils.copyProperties(vehicle, parkingForm);
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

		Vehicle currentVehicleDetails = (Vehicle)parkingDAO.getParkingStatusInfo(parkingForm.getSessionId());
	
		vehicle.setStartTime(currentVehicleDetails.getStartTime());
		vehicle.setEndTime(Calendar.getInstance().getTime());
		vehicle.setGateId(currentVehicleDetails.getGateId());
		vehicle.setGarageId(currentVehicleDetails.getGarageId());
		//we will need to call Rate details to get the rate and substract current time - starttime
		
		//get location charges
		RateDetails details = (RateDetails)parkingDAO.getChargesByLocationId(vehicle.getGateId());
		
		double totalCharge = RateCalculator.calculateRate(vehicle, details);
		vehicle.setTotalcharge(totalCharge);
		vehicle.setStatus("Closed");
		return (Vehicle)parkingDAO.updateParkingStatus(vehicle);
	}


	@Override
	@Transactional
	public Vehicle getParkingStatusInfo(String sessionId) {
		Vehicle currentVehicleDetails = (Vehicle)parkingDAO.getParkingStatusInfo(sessionId);
		//get rate details for current location
		RateDetails details = (RateDetails)parkingDAO.getChargesByLocationId(currentVehicleDetails.getGateId());
		
		double totalCharge = RateCalculator.calculateRate(currentVehicleDetails, details);
		currentVehicleDetails.setTotalcharge(totalCharge);
		return currentVehicleDetails;
		
	}

	@Override
	@Transactional
	public RateDetails getParkingTermsInfo(String locationId) {
		
		return (RateDetails)parkingDAO.getChargesByLocationId(locationId);
	}
	
	@Override
	@Transactional
	public RateDetails getParkingTermsInfoByGarageId(String locationId) {
		
		return (RateDetails)parkingDAO.getChargesByGarageId(locationId);
	}
	
}
