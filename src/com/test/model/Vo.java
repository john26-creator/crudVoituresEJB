package com.test.model;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("voiture")
public class Vo extends Vh {

	@Column(name="annee")
	private int annee;
	
	@Column(name="couleur")
	private String couleur;
	
	public Vo() {}
	
	public Vo(String marque, String modele, int annee, String couleur) {
		super("voiture", marque, modele, 4);
		this.setAnnee(annee);
		this.setCouleur(couleur);
	}

	public int getAnnee() {
		return annee;
	}

	public void setAnnee(int annee) {
		this.annee = annee;
	}

	public String getCouleur() {
		return couleur;
	}

	public void setCouleur(String couleur) {
		this.couleur = couleur;
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Voiture[")
			.append(super.toString())
			.append(", annee=").append(getAnnee())
			.append(", couleur=").append(getCouleur())
			.append("]");
		return builder.toString();
	}
}
