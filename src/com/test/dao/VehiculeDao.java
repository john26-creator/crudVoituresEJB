package com.test.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.test.model.Vehicule;

@Stateless
public class VehiculeDao {

	@PersistenceContext( unitName = "bdd_PU" )
	private EntityManager       em;

	private static final String SQL_SELECT_ALL = "SELECT v FROM Vehicule v;";
	private static final String SQL_SELECT_BY_EMAT = "SELECT v FROM Vehicule v WHERE v.immatriculation=:immatriculation;";

	public Map<String, Vehicule> findAll() throws  DaoException {

		Query request = em.createQuery(SQL_SELECT_ALL);
		List <Vehicule> vehivules = request.getResultList();
		Map <String,Vehicule> vehicules = new HashMap <String,Vehicule> ();
		for (Vehicule vehicule : vehivules) {
			vehicules.put(vehicule.getPlate(),vehicule);
		}
		return vehicules;
	}

	public void remove (String immatriculation) {
		Query request = em.createQuery(SQL_SELECT_BY_EMAT);
		request.setParameter("immatriculation", immatriculation);
		Vehicule vehicule = (Vehicule) request.getSingleResult();
		em.remove(vehicule);
		em.flush();
	}

	public void save (Vehicule vehicule) {
		em.persist(vehicule);
	}

}
