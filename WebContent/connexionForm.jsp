<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link
	href="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
	rel="stylesheet" id="bootstrap-css">
<link rel="stylesheet" href="stylesheet.css" />
<script
	src="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
<script
	src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<title>Insert title here</title>
</head>
<body>

	<div class="wrapper fadeInDown">
		<div id="formContent">
			<!-- Tabs Titles -->

			<!-- Icon -->
			<div class="fadeIn first">
				<img src="http://danielzawadzki.com/codepen/01/icon.svg" id="icon"
					alt="User Icon" />
			</div>

			<form action="/TestServletJPA/index" method="post">
				<input type="email" id="emailUtilisateur" name="email"
					class="fadeIn second" value="<c:out value="${cookie.mail.value}"/>"
					size="30" maxlength="60" placeholder="email" required/> <span class="erreur">${form.erreurs['email']}</span>
				<br /> <input type="password" id="pass" name="password"
					class="fadeIn second" size="30" maxlength="60" placeholder="password" placeholder="password" required/> <span
					class="erreur">${form.erreurs['password']}</span> <br />

				<input type="submit" class="fadeIn fourth" value="Connexion">

				<!-- Remind Passowrd -->
				<div id="formFooter">
					<a class="underlineHover" href="#">Forgot Password?</a>
				</div>
			</form>
		</div>
	</div>
</body>
</html>

