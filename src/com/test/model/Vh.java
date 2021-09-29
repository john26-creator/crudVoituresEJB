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
public class Vh {
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="public.\"Vehicule_id_vehicule_seq\"")
	@Column(name="id_vehicule")
	private Integer id_vehicule;

	@Column(name="immatriculation")
	private String immatriculation;
	
	@Column(name="marque")
	private String marque;
	
	@Column(name="model")
	private String modele;
	
	@Column(name="nb_roues")
	private int nbreRoues;
	
	@Column(name="type_vehicule")
	private String typeVehicule;
	
	public Vh() {}
	
	public Vh(String typeVehicule, String marque, String modele, int nbreRoues) {
		this.typeVehicule = typeVehicule;
		this.setMarque(marque);
		this.setModele(modele);
		this.setNbreRoues(nbreRoues);
	}
	
	public Integer getId_vehicule() {
		return id_vehicule;
	}

	public void setId_vehicule(Integer id_vehicule) {
		this.id_vehicule = id_vehicule;
	}

	public String getMarque() {
		return marque;
	}

	public void setMarque(String marque) {
		this.marque = marque;
	}

	public String getModele() {
		return modele;
	}

	public void setModele(String modele) {
		this.modele = modele;
	}

	public int getNbreRoues() {
		return nbreRoues;
	}

	public void setNbreRoues(int nbreRoues) {
		this.nbreRoues = nbreRoues;
	}
	
	
	public String getImmatriculation() {
		return immatriculation;
	}

	public void setImmatriculation(String immatriculation) {
		this.immatriculation = immatriculation;
	}
	
	public String getTypeVehicule() {
		return typeVehicule;
	}

	public void setTypeVehicule(String typeVehicule) {
		this.typeVehicule = typeVehicule;
	}

}
