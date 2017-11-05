package com.parking.garage.view;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonRootName;

@JsonRootName(value="parkingForm")
public class ParkingForm implements Serializable {
	
	private String sessionId;
	
	private String tagId;
	
	private String userId;
	
	private String gateId;
	
	private String garageId;
	
	private String gateBeaconId;
	
	private String transId;
	
	private Date startTime;
	
	private Date endTime;
	
	private String status;
	
	private double totalcharge;
	
  
	public String getSessionId() {
		return sessionId;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}

	public String getTagId() {
		return tagId;
	}

	public void setTagId(String tagId) {
		this.tagId = tagId;
	}
	
		
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getGateId() {
		return gateId;
	}

	public void setGateId(String gateId) {
		this.gateId = gateId;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	
	public double getTotalcharge() {
		return totalcharge;
	}

	public void setTotalcharge(double totalcharge) {
		this.totalcharge = totalcharge;
	}

	public String getTransId() {
		return transId;
	}

	public void setTransId(String transId) {
		this.transId = transId;
	}
	
	
	public String getGarageId() {
		return garageId;
	}

	public void setGarageId(String garageId) {
		this.garageId = garageId;
	}
	
	
	public String getGateBeaconId() {
		return gateBeaconId;
	}

	public void setGateBeaconId(String gateBeaconId) {
		this.gateBeaconId = gateBeaconId;
	}

	@Override
		public String toString() {
			StringBuilder builder = new StringBuilder();
			builder.append("transId=").append(transId);
			builder.append("totalCharge=").append(totalcharge);
			builder.append("status=").append(status);
			builder.append("endTime=").append(endTime);
			builder.append("startTime=").append(startTime);
			builder.append("locationId=").append(gateId);
			builder.append("tagId=").append(tagId);
			builder.append("sessionId=").append(sessionId);
			return builder.toString();
		}

	

}
