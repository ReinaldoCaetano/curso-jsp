<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-KK94CHFLLe+nY2dmCWGMq91rCGa5gtU4mk92HdvYe+M/SXH301p5ILy+dN9+nJOZ" crossorigin="anonymous">

<title>Curso JSP</title>
<style type="text/css">
	form{
	position: absolute;
	top: 40%;
	left: 33%;
	right: 33%;
	}
	h1{
	position:absolute;
	top: 30%;
	left: 33%;
	}
	.msg{
	position:fixed;
	top: 80%;
	left: 33%;
	color: red;
	font-size: 15px
	}

</style>
</head>
<body>
<h1>Curso de JSP Login</h1>

<form action="<%=request.getContextPath()%>/ServletLogin" method="post" class="row g-3 needs-validation" novalidate>
<input type="hidden"  value="<%=request.getParameter("url")%>" name="url">
	
	<div class="mb-3">
    <label for="inputAddress" class="form-label">Login</label>
    <input type="text" class="form-control" id="inputEmail4" name="login" required>
	  	<div class="invalid-feedback">
	      Informe o Login !
	    </div>
  </div>
  
  <div class="mb-3">
    <label for="inputPassword4" class="form-label">Senha</label>
    <input type="password" class="form-control" id="inputPassword4" name="senha" required>
		 <div class="invalid-feedback">
		      Informe a senha !
		    </div>
  </div>
  
    <button type="submit" class="btn btn-primary" value="Enviar">Enviar</button>
 	
</form>


 <h4 class="msg"> ${msg}</h4>


    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ENjdO4Dr2bkBIFxQpeoTz1HIcje39Wm4jDKdf19U8gI4ddQ3GYNS7NTKfAdVQSZe" crossorigin="anonymous"></script>
<script type="text/javascript">
(() => {
	  'use strict'

	  // Fetch all the forms we want to apply custom Bootstrap validation styles to
	  const forms = document.querySelectorAll('.needs-validation')

	  // Loop over them and prevent submission
	  Array.from(forms).forEach(form => {
	    form.addEventListener('submit', event => {
	      if (!form.checkValidity()) {
	        event.preventDefault()
	        event.stopPropagation()
	      }

	      form.classList.add('was-validated')
	    }, false)
	  })
	})()
</script>
</body>
</html>