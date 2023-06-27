<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<jsp:include page="head.jsp"></jsp:include>
  <body>
  <!-- Pre-loader start -->
  <jsp:include page="theme-loader.jsp"></jsp:include>
  <!-- Pre-loader end -->
  <div id="pcoded" class="pcoded">
      <div class="pcoded-overlay-box"></div>
      <div class="pcoded-container navbar-wrapper">
 <jsp:include page="header-navbar.jsp"></jsp:include>         

          <div class="pcoded-main-container">
              <div class="pcoded-wrapper">
                <jsp:include page="navbarmainmenu.jsp"></jsp:include>
                  <div class="pcoded-content">
                      <!-- Page-header start -->
                      <jsp:include page="page-header.jsp"></jsp:include>
                      <!-- Page-header end -->
                        <div class="pcoded-inner-content">
                            <!-- Main-body start -->
                            <div class="main-body">
                                <div class="page-wrapper">
                                    <!-- Page-body start -->
                                    <div class="page-body">
                                        <div class="row">
                                            <!-- task, page, download counter  start -->
                                          <div class="col-md-6">
                                                <div class="card">
                                                    <div class="card-header">
                                                        <h5>Cadastro Usuário</h5>
                                                        <!--<span>Add class of <code>.form-control</code> with <code>&lt;input&gt;</code> tag</span>-->
                                                    </div>
                                                    <div class="card-block">
                                                        <form class="form-material" action="<%=request.getContextPath()%>/ServletUsuarioController" method="post" id="formUser">
	                                                      	<input type="hidden" name="acao" id="acao" value="">
	                                                      	
	                                                       <div class="form-group form-default form-static-label">
	                                                             <input type="text" name="id" id="id" class="form-control" value="${modolLogin.id}">
	                                                             <span class="form-bar"></span>
	                                                             <label class="float-label">ID:</label>
	                                                       </div>
                                                             <div class="form-group form-default form-static-label">
                                                                <input type="text" name="nome" id="nome" class="form-control" required="required" value="${modolLogin.nome }">
                                                                <span class="form-bar"></span>
                                                                <label class="float-label">Nome: </label>
                                                            </div>
                                                            <div class="form-group form-default form-static-label">
                                                                <input type="email" name="email" id="email" class="form-control" required="required" autocomplete="off" value="${modolLogin.email}">
                                                                <span class="form-bar"></span>
                                                                <label class="float-label">E-mail:</label>
                                                            </div>
                                                             <div class="form-group form-default form-static-label">
                                                                <input type="text" name="login" id="login" class="form-control" required="required" value="${modolLogin.login }">
                                                                <span class="form-bar"></span>
                                                                <label class="float-label">Login: </label>
                                                            </div>
                                                               <div class="form-group form-default form-static-label">
                                                                <input type="password" name="senha" id="senha" class="form-control" required="required" autocomplete="off" value="${modolLogin.senha}">
                                                                <span class="form-bar"></span>
                                                                <label class="float-label">Senha</label>
                                                            </div>
                                                         	<div class="card-block">
                                                            <button type="button" class="btn btn-primary btn-round waves-effect waves-light" onclick="limparForm();">Novo</button>
                                                            <button type="submit" class="btn btn-success btn-round waves-effect waves-light">Salvar</button>
                                                        	<button type="button" class="btn btn-danger btn-round waves-effect waves-light" onclick="criaDeleteAjax();">Excluir</button>
                                                        	</div>
                                                        </form>
                                                    </div>
                                                </div>
                                            </div>	
                                            <!--  project and team member end -->
                                        </div>
                                        <span id="msg">${msg}</span>
                                    </div>
                                    <!-- Page-body end -->
                                </div>
                                <div id="styleSelector"> </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    
 <jsp:include page="javascriptfile.jsp"></jsp:include>
 <script type="text/javascript">
 
function criaDeleteAjax() {
	if(confirm("Deseja deletar ?")){
		
		var urlAction = document.getElementById("formUser").action;
		var idUser = document.getElementById("id").value;
		
		$.ajax({
			
			method:"get",
			url : urlAction,
			data : "id=" + idUser + "&acao=deletarajax",
			success: function (response){
				limparForm();
				document.getElementById("msg").textContent = response;
			}
			
		}).fail(function(xhr, status, errorThrown){
			alert("Erro ao Deletar Usuário por Id " + xhr.responseText);
		});
	}
} 
 
function criarDelete() {
	if(confirm('Deseja realment excluir os dados ?')){
		document.getElementById("formUser").method = 'get';
		document.getElementById('acao').value = 'deletar';
		document.getElementById("formUser").submit();
	}
	
}
 
 function limparForm() {
	var elementos = document.getElementById("formUser").elements;
	for(p = 0; p < elementos.length; p++){
		elementos[p].value = "";
	}
}
 
 </script>
</body>

</html>
    