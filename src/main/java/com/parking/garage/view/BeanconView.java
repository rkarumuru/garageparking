package com.parking.garage.view;

import java.util.List;

public class BeanconView {
	
	private boolean activeSessions;
	
	private List<BeaconDetailsView> beaconsView;

	public boolean isActiveSessions() {
		return activeSessions;
	}

	public void setActiveSessions(boolean activeSessions) {
		this.activeSessions = activeSessions;
	}

	public List<BeaconDetailsView> getBeaconsView() {
		return beaconsView;
	}

	public void setBeaconsView(List<BeaconDetailsView> beaconsView) {
		this.beaconsView = beaconsView;
	}
	
	

}
