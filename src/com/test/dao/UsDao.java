package com.test.dao;

import javax.ejb.Stateless;
import javax.persistence.Query;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

import com.test.model.Utilisateur;

@Stateless
public class UsDao {
	private static final String JPQL_SELECT_PAR_EMAIL = "SELECT u FROM Utilisateur u WHERE u.email=:email ";
	private static final String PARAM_EMAIL           = "email";
	private static final String JPQL_SELECT_PAR_ID = "SELECT u FROM Utilisateur u WHERE u.id_user=:id_user ";
	private static final String PARAM_ID           = "id_user";

	// Injection du manager, qui s'occupe de la connexion avec la BDD
	@PersistenceContext( unitName = "bdd_PU" )
	private EntityManager       em;

	// Enregistrement d'un nouvel utilisateur
	public void creer( Utilisateur utilisateur ) throws DaoException {
			em.persist( utilisateur );
	}

	// Recherche d'un utilisateur à partir de son adresse email
	public Utilisateur trouver( String email ) throws DaoException {
		Utilisateur utilisateur = null;
		Query requete = em.createQuery( JPQL_SELECT_PAR_EMAIL );
		requete.setParameter( PARAM_EMAIL, email );
		utilisateur = (Utilisateur)requete.getSingleResult();
		return utilisateur;
	}

	public Utilisateur trouver(Integer id) {
		Utilisateur utilisateur = null;
		Query requete = em.createQuery( JPQL_SELECT_PAR_ID );
		requete.setParameter( PARAM_ID, id );
		utilisateur = (Utilisateur)requete.getSingleResult();
		return utilisateur;
	}
}
