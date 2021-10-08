package com.test.model;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
@Entity
@DiscriminatorValue("car")
public class Car extends Vehicle {

	@Column(name="annee")
	private int year;

	@Column(name="couleur")
	private String color;

	public Car() {}

	public Car(String brand, String model, int year, String color) {
		super("car", brand, model, 4);
		this.year = year;
		this.color = color;
	}

}
