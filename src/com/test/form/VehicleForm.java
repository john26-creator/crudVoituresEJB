package com.test.form;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.google.common.base.Strings;
import com.test.model.Car;
import com.test.model.Moto;
import com.test.model.Vehicle;

public final class VehicleForm {

	public static final String PLATE_FIELD_NAME = "plate";
	public static final String BRAND_FIELD_NAME = "brand";
	public static final String MODEL_FIELD_NAME = "model";
	public static final String COLOR_FIELD_NAME = "color";
	public static final String POWER_FIELD_NAME = "power";
	public static final String YEAR_FIELD_NAME  = "year";
	public static final String VEHICULE_TYPE_FIELD_NAME  = "year";

	Vehicle vehicule;

	private String              stateMessage;
	private Map<String, String> errors         = new HashMap<String, String>();

	public Map<String, String> getErrors() {
		return errors;
	}

	public String getStateMessage () {
		return stateMessage;
	}

	public VehicleForm (HttpServletRequest request) {

		createVehicule (request);
		validateFields();
		setStateMessage();
	}

	private void setStateMessage() {
		if ( errors.isEmpty() ) {
			this.stateMessage = "Succès de la création du véhicule.";
		} else {
			this.stateMessage = "Échec de la création du véhicule.";
		}
	}

	private void validateFields() {
//      try {
		//            validateEmail(email);
		//        } catch ( Exception e ) {
		//            addError(EMAIL_FIELD_NAME, e.getMessage());
		//        }
		//        try {
		//            validatePassword(password);
		//        } catch (Exception e) {
		//            addError(PASSWORD_FIELD_NAME, e.getMessage());
		//        }
		//        
		//TODO validatefields All fiells are mandatory depending on vehicule type
	}

	private void createVehicule(HttpServletRequest request) {
		String plate = getFieldValue( request, PLATE_FIELD_NAME );
		String brand = getFieldValue( request, BRAND_FIELD_NAME );
		String model = getFieldValue (request, MODEL_FIELD_NAME );
		String color = getFieldValue( request, COLOR_FIELD_NAME );
		String power = getFieldValue( request, POWER_FIELD_NAME );
		String year = getFieldValue( request, YEAR_FIELD_NAME );
		String vehiculeType = getFieldValue( request, VEHICULE_TYPE_FIELD_NAME );

		vehicule = new Vehicle();
		vehicule.setBrand(brand);      
		vehicule.setPlate(plate);
		vehicule.setTypeVehicule(vehiculeType);

		if(isCar(vehiculeType)) {
			vehicule = new Car(brand, model, Integer.parseInt(year), color);
		}
		else {
			vehicule = new Moto(brand, model, Integer.parseInt(power));
		}
		vehicule.setPlate(plate);
	}

	private boolean isCar (String vehiculeType) {
		return "Voiture".equals(vehiculeType);
	}

	public boolean isValid () {
		return errors.isEmpty();
	}

	public void addError (String fieldName, String message) {
		errors.put(fieldName, message);
	}
	
	private static String getFieldValue (HttpServletRequest request, String fieldName) {
		String value = request.getParameter (fieldName);
		
		if (Strings.isNullOrEmpty(value)) {
			return null;
		} else {
			return value;
		}
	}

	public Vehicle getVehicule() {
		return this.vehicule;
	}



}