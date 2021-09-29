package com.test.model;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("moto")
public class Motocyclette extends Vh {
	
	@Column(name="puissance")
	private int puissance;
	
	public Motocyclette() {}
	
	public Motocyclette(String marque, String modele, int puissance) {
		super("moto", marque, modele, 2);
		this.setPuissance(puissance);
	}

	public int getPuissance() {
		return puissance;
	}

	public void setPuissance(int puissance) {
		this.puissance = puissance;
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Moto[")
			.append(super.toString())
			.append(", puissance=").append(getPuissance())
			.append("]");
		return builder.toString();
	}

}
