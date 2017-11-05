package com.parking.garage.service;

import com.parking.garage.data.RateDetails;
import com.parking.garage.data.Vehicle;
import com.parking.garage.view.ParkingForm;


public interface ParkingService {

	public Vehicle addParkingDetails(ParkingForm parkingForm);
	
	public Vehicle updateParkingStatus(ParkingForm parkingForm);
	
	
	public Vehicle getParkingStatusInfo(String sessionId);
	
	public RateDetails getParkingTermsInfo(String locationId);
	
	public RateDetails getParkingTermsInfoByGarageId(String locationId);
	
	
	
}
