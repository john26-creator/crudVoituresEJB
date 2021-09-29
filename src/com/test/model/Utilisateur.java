package com.test.model;

import javax.persistence.*;

@Entity @Table(name = "user", schema="public")
@SequenceGenerator(name = "public.\"USER_id_seq\"", initialValue = 1, allocationSize = 1)
public class Utilisateur {
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="public.\"USER_id_seq\"")
	@Column(name="id_user")
	private Integer id_user;
	@Column (name = "nom")
    private String nom;
	@Column (name = "email")
    private String email;
	@Column (name = "pass")
	private String password;

    public Utilisateur(String nom, String email, String password) {
    	this.nom = nom;
		this.email = email;
		this.password = password;
	}

	public Utilisateur() {}

	public Integer getId() {
		return id_user;
	}

	public void setId(Integer l) {
		this.id_user = l;
	}

	public void setNom( String nom ) {
        this.nom = nom;
    }

    public String getNom() {
        return nom;
    }

    public void setEmail( String email ) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPassword() {
		return password;
	}
	
}
