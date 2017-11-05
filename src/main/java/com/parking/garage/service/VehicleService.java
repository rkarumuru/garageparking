package com.parking.garage.service;

import java.util.List;

import com.parking.garage.data.BeaconDetails;
import com.parking.garage.data.UserDetails;
import com.parking.garage.data.Vehicle;

public interface VehicleService {

	public UserDetails getVehicleByTagId(String tagId);
	
	public List<BeaconDetails> getBeaconsListByUserId(String userId);
	
	public List<Vehicle> getActiveBeaconsListByUserId(String userId);
	
}
