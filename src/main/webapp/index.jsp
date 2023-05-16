<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Curso JSP</title>
</head>
<body>
<h1>Curso de JSP Login BB</h1>
<form action="ServletLogin" method="post">

<table>
	<tr>
	<td><label>Login</label></td>
	<td>
		<input type="text" name = "login">	
	</td>
	</tr>
	<tr>
	<td><label>Senha</label></td>
	<td><input type="password" name = "senha"></td>
	</tr>
	<tr>
	<td>
	<td>
		<input type ="submit" value="Enviar">	
	</td>
	</tr>

</table>

</form>
<h4>${msg}</h4>

</body>
</html>