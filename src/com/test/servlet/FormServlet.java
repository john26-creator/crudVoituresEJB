package com.test.servlet;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.test.dao.UsDao;
import com.test.model.MyForm;
import com.test.model.Utilisateur;

@WebServlet( urlPatterns = { "/index" } )
public class FormServlet extends HttpServlet{
	/**
	 * Attributs de classe
	 */
	private static final long serialVersionUID = 1L;

	@EJB
	private UsDao   utilisateurDao;

	public void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {
		/* � la r�ception d'une requ�te GET, simple affichage du formulaire */
		this.getServletContext().getRequestDispatcher( "/connexionForm.jsp" ).forward( request, response );
	}

	public void doPost( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {

		/* Pr�paration de l'objet formulaire */
		MyForm form = new MyForm(utilisateurDao);

		/* Traitement de la requ�te et r�cup�ration du bean en r�sultant */
		Utilisateur user = form.creerUtilisateur( request );

		/* Ajout du bean et de l'objet m�tier � l'objet requ�te */
		request.setAttribute( "user", user );
		request.setAttribute( "form", form );

		if ( form.getErreurs().isEmpty() ) {
			Utilisateur u = utilisateurDao.trouver(user.getEmail());
			//Si il y a un r�sultat c'est qu'il est pr�sent
			if (u != null && Utilitaire.getEncryptor().checkPassword(user.getPassword(), u.getPassword())) {
				//Je cr�� donc une session qui donnera acc�s � l'espace personnel de l'utilisateur
				setUserSession(request, u);
				//					//Je cr�� des coockies pour que les champs du formulaire de connexion soient pr�remplis lors d'un prochaine connexion
				setUserCookie(response, u);
				//					//Je redirige vers l'espace personnel de l'utilisateur
				response.sendRedirect("/TestServletJPA/toto");
			}
			else {
				/* Sinon, r�-affichage du formulaire avec message d'erreur identifiants incorrects*/
				form.setErreur("passwordUtilisateur", "Identifiants Incorrects");
				this.getServletContext().getRequestDispatcher( "/connexionForm.jsp" ).forward( request, response );
			}

		}
		else {
			/* Sinon, r�-affichage du formulaire de cr�ation avec les erreurs */
			this.getServletContext().getRequestDispatcher( "/connexionForm.jsp" ).forward( request, response );
		}
	}

	private void setUserSession (HttpServletRequest request, Utilisateur user) {
		HttpSession session = request.getSession();
		session.setAttribute( "user", user.getId());
	}

	private void setUserCookie (HttpServletResponse response, Utilisateur user) {
		Cookie nom = new Cookie("nom", user.getNom());
		Cookie adresseMail = new Cookie("mail", user.getEmail());
		response.addCookie(nom);
		response.addCookie(adresseMail);
	}

}

