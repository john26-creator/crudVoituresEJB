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

import com.test.dao.UserDao;
import com.test.dao.VehiculeDao;
import com.test.form.VehiculeForm;
import com.test.model.Vehicule;

@WebServlet( urlPatterns = { "/vehicules/*" } )
public class VehiculeServlet extends HttpServlet {
	
	public static final String USERID_SESSION_NAME = "userId";
	public static final String PLATE_PARAM = "plate";
	public static final String MESSAGE_PARAM_NAME = "message";
	public static final String VEHICULE_PARAM_NAME = "vehicules";
	
	public static final String CONNECTION_FORM_VUE = "/TestServletJPA/connexionForm.jsp";
	public static final String VEHICULES_SERVLET_PATH = "/TestServletJPA/vehicules";
	public static final String VEHICULES_VUE = "/restreint/vehicules.jsp";

	@EJB
	private UserDao   utilisateurDao;
	@EJB
	private VehiculeDao   vehiculeDao;

	public void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException{

		HttpSession session = request.getSession();
		Integer userId = (Integer) session.getAttribute(USERID_SESSION_NAME);
		
		if (userId == null) {
			response.sendRedirect(CONNECTION_FORM_VUE);
		} else {
			if(isRemoveAction(request)) {
				removeVehicule(request);
				response.sendRedirect(VEHICULES_SERVLET_PATH);
			}
			else {
				setVehiculeVueAttributes (request, userId);
				this.getServletContext().getRequestDispatcher(VEHICULES_VUE).forward(request, response);
			}
		}
	}
	
	private void removeVehicule (HttpServletRequest request) {
		String plateToRemove = request.getParameter(PLATE_PARAM);
		vehiculeDao.remove(plateToRemove);
	}
	
	private void setVehiculeVueAttributes (HttpServletRequest request, Integer userId) {
		Map <String,Vehicule> vehicules = vehiculeDao.findAll();
		String userName = utilisateurDao.findById(userId).getName();
		String message = "HELLO" + userName;
		
		request.setAttribute(MESSAGE_PARAM_NAME, message);
		request.setAttribute(VEHICULE_PARAM_NAME, vehicules);
	}
	
	private static boolean isRemoveAction (HttpServletRequest request) {
		String servletPath = request.getRequestURL().toString();
		return servletPath.contains("remove");
	}

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		VehiculeForm form = new VehiculeForm (request);
		Vehicule formVehicule = form.getVehicule();
		if (form.isValid()) {
			vehiculeDao.save (formVehicule);
		}
		
		Map<String, Vehicule> vehicules = null;
		vehicules = vehiculeDao.findAll();
		
		request.setAttribute(VEHICULE_PARAM_NAME, vehicules);
		this.getServletContext().getRequestDispatcher(VEHICULES_VUE).forward(request, response);
	}
	
}
