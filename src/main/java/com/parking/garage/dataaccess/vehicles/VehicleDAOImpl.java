package com.parking.garage.dataaccess.vehicles;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.parking.garage.data.BeaconDetails;
import com.parking.garage.data.UserDetails;
import com.parking.garage.data.Vehicle;

public class VehicleDAOImpl implements VehiclesDAO {

	  private static final Logger logger = Logger.getLogger(VehicleDAOImpl.class);
	
	  @Autowired
	private SessionFactory sessionFactory;
	  
		    
	  public void setSessionFactory(SessionFactory sf){
	        this.sessionFactory = sf;
	    }
	  
	
	@SuppressWarnings("unchecked")
	@Override
	public UserDetails getVehicleDetailsByTagId(String tagId) {
		
		Session session = this.sessionFactory.getCurrentSession();      
		UserDetails p = (UserDetails) session.get(UserDetails.class, tagId);
	
		return p;
	}


	@Override
	public List<Vehicle> getActiveBeaconsListByUserId(String userId) {
		
		String hql = "FROM Vehicle E WHERE (E.userId =:userId AND E.status='Open'))";
		
		Session session = this.sessionFactory.getCurrentSession();  
		
		Query query = session.createQuery(hql);
		
		query.setParameter("userId",userId);
		
				
		List <Vehicle> beaconsList  = (List <Vehicle>)query.list();
		
		return beaconsList;
	}


	@Override
	public List<BeaconDetails> getBeaconsListByUserId(String userId) {
		
		String hql = "FROM BeaconDetails E WHERE E.userId =:userId OR E.beaconType='G'";
		
		Session session = this.sessionFactory.getCurrentSession();  
		
		Query query = session.createQuery(hql);
		
		query.setParameter("userId",userId);
		
		List <BeaconDetails> beaconsList  = (List <BeaconDetails>)query.list();
		
		return beaconsList;
		
	}


}
