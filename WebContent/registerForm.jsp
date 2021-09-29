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
<title>Insert title here</title>
</head>
<body>
	<div class="wrapper fadeInDown">
		<div id="formContent">
			<form action="/TestServletJPA/creationUtilisateur" method="post">
				<label for="name">Nom :</label> 
				<input type="text" id="name" name="name" size="30" maxlength="60" class="fadeIn first" placeholder="nom"/> <br />
				
				<label for="emailUtilisateur">Adresse email:</label>
				<input type="email" id="emailUtilisateur" name="email" size="30" maxlength="60" class="fadeIn second" placeholder="email"/>
				<br />       
				
			    <label	for="passwordUtilisateur">Password (8 characters minimum):</label> 
				<input	type="password" id="pass" name="password" size="30" maxlength="60" required class="fadeIn third" placeholder="password"/>
				<br />
				
				<div class="button">
					<input type="submit" class="fadeIn fourth" value="Ajouter utilisateur"/>
				</div>
			</form>
		</div>
	</div>
</body>
</html>