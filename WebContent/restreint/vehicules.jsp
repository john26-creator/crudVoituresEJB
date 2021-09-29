<%@ page import="java.util.List, com.test.model.*"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<link href="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
	<script src="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
	<script 	src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
	<link rel="stylesheet" href="stylesheet.css" />
	<title>Page Véhicule</title>
</head>
<body
	onload="document.getElementById('VoitureFields').style.visibility = 'visible';document.getElementById('MotoFields').style.visibility = 'hidden';">

	
	<div class="wrapper fadeInDown">
	<div class="container" style="margin:20px; align:right">
		<form action="/TestServletJPA/disconnect" method="post">
				<h3> ${requestScope.message}</h3>
				<input class="btn btn-danger" type="submit" value="Deconnexion">
		</form>
	</div>
		<div id="formContent">
	<div class="container" style="margin:20px">
		<h1>Formulaire Ajout Véhicule</h1>
	</div>
	<div class="container" style="margin:20px" >
		<form action="/TestServletJPA/toto" method="post">
			<fieldset>
				<legend>Type de Véhicule</legend>
				<div class="form-group">
					<span> <input class="fadeIn first" type="radio" id="voiture" name="typeVehicule"
						value="Voiture"
						onclick="document.getElementById('VoitureFields').style.visibility = 'visible';document.getElementById('MotoFields').style.visibility = 'hidden';"
						checked> <label for="voiture">Voiture</label>
					</span> <span> <input class="fadeIn first" type="radio" id="moto" name="typeVehicule"
						value="Moto"
						onclick="document.getElementById('VoitureFields').style.visibility = 'hidden';document.getElementById('MotoFields').style.visibility = 'visible';">
						<label for="moto">Moto</label>
					</span>
				</div>
			</fieldset>
			<br />
			<fieldset id="common">
				<legend>Attributs V&eacute;hicule</legend>
				<div class="form-group">
					<label for="immatriculation">Immat :</label><input type="text" id="immatriculation" name="plate" class="form-control" style="width:300px" placeholder="LL-NNN-LL">
				</div>
				<div class="form-group">
					<label for="marque">Marque :</label> <input type="text" class="form-control" id="marque"
						name="brand" style="width:300px" placeholder="Ex: Renault">
				</div>
				<div class="form-group">
					<label for="modele">Modèle :</label> <input type="text" class="form-control" id="modele"
						name="model" style="width:300px" placeholder="Ex: Clio III">
				</div>
			</fieldset>
			<br />
			<fieldset id="MotoFields">
				<legend>Attribut Moto</legend>
				<div class="form-group">
					<label for="puissance">Puissance :</label> <input type="text"
						class="form-control" id="puissance" name="power" style="width:300px" placeholder="Nombre de CV">
				</div>
	
			</fieldset>
			<br />
			<fieldset id="VoitureFields">
				<legend>Attributs Voiture</legend>
				<div class="form-group">
					<label for="couleur">Couleur :</label> <input type="text"
						id="couleur" class="form-control" name="color" style="width:300px">
				</div>
	
				<div class="form-group">
					<label for="annee">Année :</label> <input type="text" class="form-control" id="annee"
						name="year" style="width:300px" placeholder="YYYY">
				</div>
			</fieldset>
				<input class="btn btn-primary" type="submit" value="Ajouter véhicule">
		</form>
	</div>
	</div>
	
	<div class="container" style="margin:20px">
		<h1>Liste des véhicules</h1>
	</div>
<div class="container" class="col-6" style="margin:20px;">
	<table class="table">
		<thead>
			<tr>
				<th scope="col">Immatriculation</th>
				<th scope="col">Marque</th>
				<th scope="col">Modèle</th>
				<th scope="col">Nombre de roues</th>
				<th scope="col">Couleur</th>
				<th scope="col">Puissance</th>
				<th scope="col">Année</th>
				<th scope="col">Supprimer</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${requestScope.vehicules}" var="vehicule">
				<tr>
					<td scope="row"><c:out value="${vehicule.value.plate}" /></td>
					<td scope="row"><c:out value="${vehicule.value.model}" /></td>
					<td scope="row"><c:out value="${vehicule.value.wheelNumber}" /></td>
					<td scope="row"><c:out value="${vehicule.value.brand}" /></td>
					<c:choose>
						<c:when test="${vehicule.value.typeVehicule == 'voiture'}">
							<td scope="row"><c:out value="${vehicule.value.color}" /></td>
							<td scope="row"></td>
							<td scope="row"><c:out value="${vehicule.value.year}" /></td>
						</c:when>
						<c:when test="${vehicule.value.typeVehicule =='moto'}">
							<td scope="row"></td>
							<td scope="row"><c:out value="${vehicule.value.power}" /></td>
							<td scope="row"></td>
						</c:when>
					</c:choose>
					<td scope="row"><a class="btn btn-danger"
						href="/TestServletJPA/toto/remove?immat=${vehicule.value.plate}">Supprimer</a></td>
				<tr>
			</c:forEach>
		</tbody>
	</table>
	</div>
	</div>
</body>
</html>