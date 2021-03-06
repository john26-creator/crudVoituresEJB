package com.test.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

	
@WebFilter
public class RestrictionFilter implements Filter {
	
    public static final String PUBLIC_ACCESS     = "/connexionForm.jsp";
    public static final String USER_SESSION = "sessionUtilisateur";

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
			throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;
        HttpSession session = request.getSession();

        /**
         * Si l'objet utilisateur n'existe pas dans la session en cours, alors
         * l'utilisateur n'est pas connect?.
         */
        if ( session.getAttribute( USER_SESSION ) == null ) {
            response.sendRedirect( request.getContextPath() + PUBLIC_ACCESS );
        } else {
            chain.doFilter( request, response );
        }
    }

	@Override
	public void destroy() {}

	@Override
	public void init(FilterConfig arg0) throws ServletException {}	

}
