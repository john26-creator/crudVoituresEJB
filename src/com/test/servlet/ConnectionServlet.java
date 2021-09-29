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

import org.jasypt.util.password.ConfigurablePasswordEncryptor;

import com.test.dao.UserDao;
import com.test.form.RegisterForm;
import com.test.model.User;

@WebServlet( urlPatterns = { "/index" } )
public class ConnectionServlet extends HttpServlet{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static final String FORM   = "form";

	public static final String VEHICULE_VUE = "/TestServletJPA/vehicules.jsp";
	public static final String LOGIN_VUE   = "/connexionForm.jsp";

	@EJB
	private UserDao   userDao;

	public void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {
		this.getServletContext().getRequestDispatcher( LOGIN_VUE ).forward( request, response );
	}

	public void doPost( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {

		RegisterForm form = new RegisterForm(request);
		User formUser = form.getUser();
		request.setAttribute(FORM, form);

		if (form.isValid()) {
			User dataBaseUser = userDao.findByEmail(formUser.getEmail());
			if (isUserValid (formUser, dataBaseUser)) {
				setUserSession(request, dataBaseUser);
				setUserCookie(response, dataBaseUser);
				response.sendRedirect(VEHICULE_VUE);
			}
			else {
				form.addError(RegisterForm.PASSWORD_FIELD_NAME, "Identifiants Incorrects");
				this.getServletContext().getRequestDispatcher( LOGIN_VUE ).forward( request, response );
			}

		}
		else {
			this.getServletContext().getRequestDispatcher( LOGIN_VUE ).forward( request, response );
		}
	}
	
	private boolean isUserValid (User formUser, User dataBaseUser) {
		ConfigurablePasswordEncryptor encryptor = EncryptionTool.getEncryptor();
		return dataBaseUser != null && encryptor.checkPassword(formUser.getPassword(), dataBaseUser.getPassword());
	}

	private void setUserSession (HttpServletRequest request, User user) {
		HttpSession session = request.getSession();
		session.setAttribute( "userId", user.getId());
	}

	private void setUserCookie (HttpServletResponse response, User user) {
		Cookie nom = new Cookie("name", user.getName());
		Cookie mail = new Cookie("email", user.getEmail());
		response.addCookie(nom);
		response.addCookie(mail);
	}

}

