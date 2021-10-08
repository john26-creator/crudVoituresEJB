package com.test.dao;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.test.model.User;

@Stateless
public class UserDao {
	private static final String SELECT_BY_EMAIL_QUERY = "SELECT u FROM Utilisateur u WHERE u.email=:email ";
	private static final String EMAIL_PARAM           = "email";
	private static final String SELECT_BY_ID_QUERY = "SELECT u FROM Utilisateur u WHERE u.id_user=:id_user ";
	private static final String ID_PARAM           = "id_user";

	@PersistenceContext( unitName = "bdd_PU" )
	private EntityManager       em;

	public void save( User user ) throws DaoException {
		em.persist( user );
	}

	public User findByEmail( String email ) throws DaoException {
		Query query = em.createQuery( SELECT_BY_EMAIL_QUERY );
		
		query.setParameter( EMAIL_PARAM, email );
		return (User) query.getSingleResult();
	}

	public User findById (Integer id) {
		Query query = em.createQuery( SELECT_BY_ID_QUERY );
		
		query.setParameter( ID_PARAM, id );
		return (User) query.getSingleResult();
	}
}
