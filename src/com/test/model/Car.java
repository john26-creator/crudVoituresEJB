package com.test.model;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("car")
public class Car extends Vehicule {

	@Column(name="annee")
	private int year;
	
	@Column(name="couleur")
	private String color;
	
	public Car() {}
	
	public Car(String brand, String model, int year, String color) {
		super("car", brand, model, 4);
		this.setYear(year);
		this.setColor(color);
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Car[")
			.append(super.toString())
			.append(", year=").append(getYear())
			.append(", color=").append(getColor())
			.append("]");
		return builder.toString();
	}
}
