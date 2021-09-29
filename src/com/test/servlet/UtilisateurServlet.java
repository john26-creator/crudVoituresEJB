package com.test.servlet;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.test.dao.UsDao;
import com.test.model.MyForm;
import com.test.model.Utilisateur;

@WebServlet( urlPatterns = { "/creationUtilisateur" } )
public class UtilisateurServlet extends HttpServlet {

	@EJB
	private UsDao   utilisateurDao;

	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		this.getServletContext().getRequestDispatcher("/connexionForm.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		/* Préparation de l'objet formulaire */
		MyForm form = new MyForm(utilisateurDao);

		/* Traitement de la requête et récupération du bean en résultant */
		String nom = req.getParameter("name");
		String email = req.getParameter("emailUtilisateur");
		String password = req.getParameter("passwordUtilisateur");
		String passwordEncrypted = Utilitaire.encryptPassword(password);
		
		Utilisateur user = new Utilisateur(nom,email,passwordEncrypted);
		utilisateurDao.creer(user);

		this.getServletContext().getRequestDispatcher("/connexionForm.jsp").forward(req, resp);
	}


}
