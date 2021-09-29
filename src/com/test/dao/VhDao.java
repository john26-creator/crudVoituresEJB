package com.test.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.test.model.Vh;
import com.test.model.Vo;

@Stateless
public class VhDao {

	// Injection du manager, qui s'occupe de la connexion avec la BDD
	@PersistenceContext( unitName = "bdd_PU" )
	private EntityManager       em;

	private static final String SQL_SELECT_ALL = "SELECT v FROM Vehicule v;";
	private static final String SQL_SELECT_BY_EMAT = "SELECT v FROM Vehicule v WHERE v.immatriculation=:immatriculation;";

	public Map<String, Vh> trouverVehicules() throws  DaoException {

		Query request = em.createQuery(SQL_SELECT_ALL);
		List <Vh> v = request.getResultList();
		Map <String,Vh> vehicules = new HashMap <String,Vh> ();
		for (Vh vehicule : v) {
			vehicules.put(vehicule.getImmatriculation(),vehicule);
		}
		return vehicules;
	}

	public void supprimerVehicule(String immatriculation) {
		Query request = em.createQuery(SQL_SELECT_BY_EMAT);
		request.setParameter("immatriculation", immatriculation);
		Vh v = (Vh) request.getSingleResult();
		em.remove(v);
		em.flush();
	}

	public void ajouterVehicule(Vh vehicule) {
		em.persist(vehicule);
	}

}
