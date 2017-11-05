package com.parking.garage.data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="BEACON_DETAILS", catalog="garageparking")
public class BeaconDetails {
	
	@Id
	@Column(name="beaconId")
	private String beaconId;
	
	private String vehicleId;
	
	private String userId;
	
	private String beaconType;
	
	private String userName;
	
	private String address;

	public String getBeaconId() {
		return beaconId;
	}

	public void setBeaconId(String beaconId) {
		this.beaconId = beaconId;
	}

	public String getVehicleId() {
		return vehicleId;
	}

	public void setVehicleId(String vehicleId) {
		this.vehicleId = vehicleId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getBeaconType() {
		return beaconType;
	}

	public void setBeaconType(String beaconType) {
		this.beaconType = beaconType;
	}
	
	
	

}
