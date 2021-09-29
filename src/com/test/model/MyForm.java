package com.test.model;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.test.dao.UsDao;

public final class MyForm {
    private String              resultat;
    private Map<String, String> erreurs         = new HashMap<String, String>();
    
    private UsDao dao;

	public MyForm(UsDao dao) {
		this.dao = dao;
		// TODO Auto-generated constructor stub
	}

	public Map<String, String> getErreurs() {
        return erreurs;
    }

    public String getResultat() {
        return resultat;
    }

    public Utilisateur creerUtilisateur( HttpServletRequest request ) {
        String nom = getValeurChamp( request, "nomUtilisateur" );
        String email = getValeurChamp( request, "emailUtilisateur" );
        String password = getValeurChamp (request, "passwordUtilisateur");

        Utilisateur u = new Utilisateur();

        u.setNom( nom );
        try {
            validationEmail( email );
        } catch ( Exception e ) {
            setErreur( "emailUtilisateur", e.getMessage() );
        }
        u.setEmail( email );
        try {
            validationPassword( password );
        } catch ( Exception e ) {
            setErreur( "passwordUtilisateur", e.getMessage() );
        }
        u.setPassword(password);
        
        if ( erreurs.isEmpty() ) {
            resultat = "Succès de la création du client.";
        } else {
            resultat = "Échec de la création du client.";
        }

        return u;
    }

	private void validationPassword(String password) throws Exception {
    	if ( password != null ) {
            if ( password.length() < 2 ) {
                throw new Exception( "Le mot de passe doit contenir au moins 2 caractères." );
            }
        } else {
            throw new Exception( "Merci d'entrer un mot de passe." );
        }
    }

    private void validationEmail( String email ) throws Exception {
        if ( email != null && !email.matches( "([^.@]+)(\\.[^.@]+)*@([^.@]+\\.)+([^.@]+)" ) ) {
            throw new Exception( "Merci de saisir une adresse mail valide." );
        }
    }

    /*
     * Ajoute un message correspondant au champ spécifié à la map des erreurs.
     */
    public void setErreur( String champ, String message ) {
        erreurs.put( champ, message );
    }

    /*
     * Méthode utilitaire qui retourne null si un champ est vide, et son contenu
     * sinon.
     */
    private static String getValeurChamp( HttpServletRequest request, String nomChamp ) {
        String valeur = request.getParameter( nomChamp );
        if ( valeur == null || valeur.trim().length() == 0 ) {
            return null;
        } else {
            return valeur;
        }
    }
}