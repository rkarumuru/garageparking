package com.parking.garage.dataaccess.parking;

import com.parking.garage.data.RateDetails;
import com.parking.garage.data.Vehicle;

public interface ParkingDAO {
	
	public Vehicle addParkingDetails(Vehicle vehicle);
	
	public Vehicle updateParkingStatus(Vehicle vehicle);
	
	public Vehicle getParkingStatusInfo(String sessionId);
	
	public RateDetails getChargesByLocationId(String rateId);
	
	public RateDetails getChargesByGarageId(String garageId);
	
	

}
