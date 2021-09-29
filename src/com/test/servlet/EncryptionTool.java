package com.test.servlet;

import org.jasypt.util.password.ConfigurablePasswordEncryptor;

public class EncryptionTool {

	private static final String ENCRYPTION_ALGO = "SHA-256";

	public static String encryptPassword(String password) {
		/*
		 * Utilisation de la biblioth�que Jasypt pour chiffrer le mot de passe
		 * efficacement.
		 * 
		 * L'algorithme SHA-256 est ici utilis�, avec par d�faut un salage
		 * al�atoire et un grand nombre d'it�rations de la fonction de hashage.
		 * 
		 * La String retourn�e est de longueur 56 et contient le hash en Base64.
		 */
		ConfigurablePasswordEncryptor passwordEncryptor = new ConfigurablePasswordEncryptor();
		passwordEncryptor.setAlgorithm( ENCRYPTION_ALGO );
		passwordEncryptor.setPlainDigest( false );
		return passwordEncryptor.encryptPassword(password);
	}

	public static ConfigurablePasswordEncryptor getEncryptor () {
		ConfigurablePasswordEncryptor passwordEncryptor = new ConfigurablePasswordEncryptor();
		passwordEncryptor.setAlgorithm( ENCRYPTION_ALGO );
		passwordEncryptor.setPlainDigest( false );
		return passwordEncryptor;
	}

}
