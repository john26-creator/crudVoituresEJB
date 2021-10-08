package com.test.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity @Table(name = "user", schema="public")
@SequenceGenerator(name = "public.\"USER_id_seq\"", initialValue = 1, allocationSize = 1)
public class User {

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

	public User(String nom, String email, String password) {
		this.nom = nom;
		this.email = email;
		this.password = password;
	}

	public User() {}

}
