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

@Entity @Table(name = "vehicule", schema="public")
@SequenceGenerator(name = "public.\"Vehicule_id_vehicule_seq\"", initialValue = 1, allocationSize = 1)
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="type_vehicule")
public class Vehicule {
	
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
	
	public Vehicule() {}
	
	public Vehicule(String typeVehicule, String brand, String model, int wheelNumber) {
		this.typeVehicule = typeVehicule;
		this.brand = brand;
		this.model = model ;
		this.wheelNumber  = wheelNumber;
	}
	
	public Integer getId_vehicule() {
		return id_vehicule;
	}

	public void setId_vehicule(Integer id_vehicule) {
		this.id_vehicule = id_vehicule;
	}
	
	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public int getWheelNumber() {
		return wheelNumber;
	}

	public void setWheelNumber(int wheelNumber) {
		this.wheelNumber = wheelNumber;
	}

	public String getPlate() {
		return this.plate;
	}

	public void setPlate(String immatriculation) {
		this.plate = immatriculation;
	}
	
	public String getTypeVehicule() {
		return typeVehicule;
	}

	public void setTypeVehicule(String typeVehicule) {
		this.typeVehicule = typeVehicule;
	}

}
