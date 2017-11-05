package com.parking.garage.dataaccess.vehicles;

import java.util.List;

import com.parking.garage.data.BeaconDetails;
import com.parking.garage.data.UserDetails;
import com.parking.garage.data.Vehicle;

public interface VehiclesDAO {

	public UserDetails getVehicleDetailsByTagId(String tagId);
	
	public List<BeaconDetails> getBeaconsListByUserId(String userId);
	
	
	public List<Vehicle> getActiveBeaconsListByUserId(String userId);
	
}
