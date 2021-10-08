package com.test.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.test.model.Vehicle;

@Stateless
public class VehiculeDao {

	@PersistenceContext( unitName = "bdd_PU" )
	private EntityManager       em;

	private static final String PLATE_PARAMETER = "immatriculation";
	
	private static final String SQL_SELECT_ALL = "SELECT v FROM Vehicule v;";
	private static final String SQL_SELECT_BY_EMAT = "SELECT v FROM Vehicule v WHERE v.immatriculation=:immatriculation;";

	public Map<String, Vehicle> getVehicleMap() throws  DaoException {
		Query request = em.createQuery(SQL_SELECT_ALL);
		List <Vehicle> vehicles = request.getResultList();
		Map <String,Vehicle> vehicules = new HashMap <String,Vehicle> ();
		
		for (Vehicle vehicule : vehicles) {
			vehicules.put(vehicule.getPlate(),vehicule);
		}
		return vehicules;
	}

	public void remove (String plateNumber) {
		Query request = em.createQuery(SQL_SELECT_BY_EMAT);
		
		request.setParameter(PLATE_PARAMETER, plateNumber);
		Vehicle vehicule = (Vehicle) request.getSingleResult();
		
		em.remove(vehicule);
		em.flush();
	}

	public void save (Vehicle vehicule) {
		em.persist(vehicule);
	}

}
