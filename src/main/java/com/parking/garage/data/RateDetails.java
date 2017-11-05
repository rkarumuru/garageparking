package com.parking.garage.data;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Entity bean with JPA annotations
 * Hibernate provides JPA implementation
 * 
      sessionid, location id, tagid,start time, endtime, status(active/inactive),total charge
 *
 */
@Entity
@Table(name="RATE_DETAILS", catalog="garageparking")

public class RateDetails implements Serializable {
	
	@Id
	@Column(name="gateBeaconId")
	private String gateBeaconId;
	
	private String garageId;
	
	private String gateId;
	
	private String address1;
	
	private String address2;
	
	private double charge;
	
	private double maxCharge;
	
	private String closingTime;

	
	public String getGateId() {
		return gateId;
	}

	public void setGateId(String gateId) {
		this.gateId = gateId;
	}

	public double getCharge() {
		return charge;
	}

	public void setCharge(double charge) {
		this.charge = charge;
	}

	
	public String getGarageId() {
		return garageId;
	}

	public void setGarageId(String garageId) {
		this.garageId = garageId;
	}

	public String getAddress1() {
		return address1;
	}

	public void setAddress1(String address1) {
		this.address1 = address1;
	}

	public String getAddress2() {
		return address2;
	}

	public void setAddress2(String address2) {
		this.address2 = address2;
	}

	public double getMaxCharge() {
		return maxCharge;
	}

	public void setMaxCharge(double maxCharge) {
		this.maxCharge = maxCharge;
	}

	public String getClosingTime() {
		return closingTime;
	}

	public void setClosingTime(String closingTime) {
		this.closingTime = closingTime;
	}

	public String getGateBeaconId() {
		return gateBeaconId;
	}

	public void setGateBeaconId(String gateBeaconId) {
		this.gateBeaconId = gateBeaconId;
	}

	
}
