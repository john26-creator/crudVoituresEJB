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
@ToString
@NoArgsConstructor
@Entity
@DiscriminatorValue("moto")
public class Moto extends Vehicle {

	@Column(name="puissance")
	private int power;

	public Moto() {}

	public Moto(String brand, String model, int power) {
		super("moto", brand, model, 2);
		this.power = power;
	}

}
