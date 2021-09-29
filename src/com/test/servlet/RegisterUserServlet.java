package com.test.servlet;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.test.dao.UserDao;
import com.test.form.RegisterForm;

@WebServlet( urlPatterns = { "/creationUtilisateur" } )
public class RegisterUserServlet extends HttpServlet {

	public static final String FORM_ATTRIBUTE   = "form";
	public static final String REGISTER_FORM_VUE = "/registerForm.jsp";
	
	@EJB
	private UserDao   userDAO;

	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.getServletContext().getRequestDispatcher(REGISTER_FORM_VUE).forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		RegisterForm form = new RegisterForm(request);
		request.setAttribute(FORM_ATTRIBUTE, form);
		
		if (form.isValid()) {
			userDAO.save(form.getUser());
		}
		
		this.getServletContext().getRequestDispatcher(REGISTER_FORM_VUE).forward(request, response);
	}
	

}
