package com.test.model;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("moto")
public class Moto extends Vehicule {
	
	@Column(name="puissance")
	private int power;
	
	public Moto() {}
	
	public Moto(String brand, String model, int power) {
		super("moto", brand, model, 2);
		this.setPower(power);
	}
	
	public int getPower() {
		return power;
	}

	public void setPower(int power) {
		this.power = power;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Moto[")
			.append(super.toString())
			.append(", puissance=").append(power)
			.append("]");
		return builder.toString();
	}

}
