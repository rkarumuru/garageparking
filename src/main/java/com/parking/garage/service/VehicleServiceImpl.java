package com.parking.garage.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.parking.garage.data.BeaconDetails;
import com.parking.garage.data.UserDetails;
import com.parking.garage.data.Vehicle;
import com.parking.garage.dataaccess.vehicles.VehiclesDAO;

public class VehicleServiceImpl implements VehicleService {

	@Autowired(required=true)
	private VehiclesDAO vehiclesDAO;
	
	
	public void setVehiclesDAO(VehiclesDAO vehiclesDAO) {
		this.vehiclesDAO = vehiclesDAO;
	}

	
	@Override
	@Transactional
	public UserDetails getVehicleByTagId(String tagId) {
		
		return (UserDetails)vehiclesDAO.getVehicleDetailsByTagId(tagId);
	}


	@Override
	@Transactional
	public List<BeaconDetails> getBeaconsListByUserId(String userId) {
	
		return (List<BeaconDetails>)vehiclesDAO.getBeaconsListByUserId(userId);
	}

	
	@Override
	@Transactional
	public List<Vehicle> getActiveBeaconsListByUserId(String userId) {
	
		return (List<Vehicle>)vehiclesDAO.getActiveBeaconsListByUserId(userId);
	}
	

}
