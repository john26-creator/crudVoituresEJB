package com.test.form;

import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import com.google.common.base.Strings;
import com.test.model.User;

public final class RegisterForm {
	
    public static final String NAME_FIELD_NAME       = "name";
    public static final String EMAIL_FIELD_NAME     = "email";
    public static final String PASSWORD_FIELD_NAME  = "password";
    
    public static final int PASSWORD_MAX_LENGTH = 8;
    private User user;
    
    private String              stateMessage;
    private Map<String, String> errors         = new HashMap<String, String>();
    
	public Map<String, String> getErrors() {
        return errors;
    }

    public String getStateMessage () {
        return stateMessage;
    }

    public RegisterForm (HttpServletRequest request) {
    	createUser (request);
        validateFields();
        setStateMessage();
    }
    
    private void createUser(HttpServletRequest request) {
    	String name = getFieldValue( request, NAME_FIELD_NAME );
        String email = getFieldValue( request, EMAIL_FIELD_NAME );
        String password = getFieldValue (request, PASSWORD_FIELD_NAME);
        
        user = new User();
        user.setName(name);      
        user.setEmail( email );
        user.setPassword(password);
	}

	private void validateFields () {
    	try {
            validateEmail(user.getEmail());
        } catch ( Exception e ) {
            addError(EMAIL_FIELD_NAME, e.getMessage());
        }
        try {
            validatePassword(user.getPassword());
        } catch (Exception e) {
            addError(PASSWORD_FIELD_NAME, e.getMessage());
        }
    }
    
    private void setStateMessage () {
    	if ( errors.isEmpty() ) {
            this.stateMessage = "Succès de la création du client.";
        } else {
            this.stateMessage = "Échec de la création du client.";
        }
    }
    
    public boolean isValid () {
    	return errors.isEmpty();
    }

	private void validatePassword (String password) throws Exception {
    	if (Strings.isNullOrEmpty(password) || password.length() < PASSWORD_MAX_LENGTH) {
                throw new Exception("Le mot de passe doit contenir au moins " + PASSWORD_MAX_LENGTH + " caractères.");
        }
    }

    private void validateEmail (String email) throws Exception {
        if (Strings.isNullOrEmpty(email)|| !email.matches( "([^.@]+)(\\.[^.@]+)*@([^.@]+\\.)+([^.@]+)")) {
            throw new Exception( "Merci de saisir une adresse mail valide." );
        }
    }

    public void addError (String fieldName, String message) {
        errors.put(fieldName, message);
    }

    private static String getFieldValue (HttpServletRequest request, String fieldName) {
        String value = request.getParameter (fieldName);
        if (Strings.isNullOrEmpty(value)) {
            return null;
        } else {
            return value;
        }
    }

	public User getUser() {
		return user;
	}
    
}