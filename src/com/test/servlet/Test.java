package com.test.servlet;

import java.io.IOException;
import java.util.Map;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.test.dao.UsDao;
import com.test.dao.VhDao;
import com.test.model.Motocyclette;
import com.test.model.Vh;
import com.test.model.Vo;

@WebServlet( urlPatterns = { "/toto/*" } )
public class Test extends HttpServlet {

	@EJB
	private UsDao   utilisateurDao;
	@EJB
	private VhDao   vehiculeDao;

	public void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException{

		HttpSession session = request.getSession();
		if ( session.getAttribute( "user" ) == null ) {
			/* Redirection vers la page publique */
			response.sendRedirect("/TestServletJPA/connexionForm.jsp");
			return;
		} else {
			Map <String,Vh> vehicules = vehiculeDao.trouverVehicules();
			String n = utilisateurDao.trouver((Integer) session.getAttribute( "user" )).getNom();
			String spath = request.getRequestURL().toString();
			if(spath.contains("remove")) {
				String immatToRemove = request.getParameter("immat");
				vehicules.remove(immatToRemove);
				vehiculeDao.supprimerVehicule(immatToRemove);
				response.sendRedirect("/TestServletJPA/toto");
			}
			else {

				String message = "Bonjour";
				if (vehicules.isEmpty()) {
					vehicules = vehiculeDao.trouverVehicules();
				}

				request.setAttribute("message", message + " " + n);
				request.setAttribute("vehicules", vehicules);
				this.getServletContext().getRequestDispatcher("/restreint/vehicules.jsp").forward(request, response);
			}
		}
	}

	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String immatriculation = req.getParameter("immatriculation");
		String marque = req.getParameter("marque");
		String modele = req.getParameter("modele");
		String couleur = req.getParameter("couleur");
		String puissance = req.getParameter("puissance");
		String annee = req.getParameter("annee");
		String vehicule = req.getParameter("typeVehicule");

		Map<String, Vh> vehicules = null;
		vehicules = vehiculeDao.trouverVehicules();

		if("Voiture".equals(vehicule)) {//c'est une voiture
			Vo v = new Vo(marque, modele, Integer.parseInt(annee), couleur);
			v.setImmatriculation(immatriculation);
			vehicules.put(v.getImmatriculation(),v);
			vehiculeDao.ajouterVehicule(v);
		}
		else {
			Motocyclette m = new Motocyclette(marque, modele, Integer.parseInt(puissance));
			m.setImmatriculation(immatriculation);
			vehicules.put(m.getImmatriculation(),m);
			vehiculeDao.ajouterVehicule(m);
		}
		req.setAttribute("vehicules", vehicules);
		this.getServletContext().getRequestDispatcher("/restreint/vehicules.jsp").forward(req, resp);
	}

}
