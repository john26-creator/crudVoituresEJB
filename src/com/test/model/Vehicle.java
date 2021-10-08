package com.test.model;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity @Table(name = "vehicule", schema="public")
@SequenceGenerator(name = "public.\"Vehicule_id_vehicule_seq\"", initialValue = 1, allocationSize = 1)
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="type_vehicule")
public class Vehicle {

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="public.\"Vehicule_id_vehicule_seq\"")
	@Column(name="id_vehicule")
	private Integer id_vehicule;

	@Column(name="immatriculation")
	private String plate;

	@Column(name="marque")
	private String brand;

	@Column(name="model")
	private String model;

	@Column(name="nb_roues")
	private int wheelNumber;

	@Column(name="type_vehicule")
	private String typeVehicule;

	public Vehicle() {}

	public Vehicle(String typeVehicule, String brand, String model, int wheelNumber) {
		this.typeVehicule = typeVehicule;
		this.brand = brand;
		this.model = model ;
		this.wheelNumber  = wheelNumber;
	}
	
}
