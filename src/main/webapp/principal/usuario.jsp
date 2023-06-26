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
                                                        <form class="form-material" action="<%=request.getContextPath()%>/ServletUsuarioController" method="post">
	                                                       <div class="form-group form-default">
	                                                             <input type="text" name="id" id="id" class="form-control" disabled="" value="${modolLogin.id}">
	                                                             <span class="form-bar"></span>
	                                                             <label class="float-label">ID:</label>
	                                                       </div>
                                                             <div class="form-group form-default">
                                                                <input type="text" name="nome" id="nome" class="form-control" required="required" value="${modolLogin.nome }">
                                                                <span class="form-bar"></span>
                                                                <label class="float-label">Nome: </label>
                                                            </div>
                                                            <div class="form-group form-default">
                                                                <input type="email" name="email" id="email" class="form-control" required="required" autocomplete="off" value="${modolLogin.email}">
                                                                <span class="form-bar"></span>
                                                                <label class="float-label">E-mail:</label>
                                                            </div>
                                                             <div class="form-group form-default">
                                                                <input type="text" name="login" id="login" class="form-control" required="required" value="${modolLogin.login }">
                                                                <span class="form-bar"></span>
                                                                <label class="float-label">Login: </label>
                                                            </div>
                                                               <div class="form-group form-default">
                                                                <input type="password" name="senha" id="senha" class="form-control" required="required" autocomplete="off" value="${modolLogin.senha}">
                                                                <span class="form-bar"></span>
                                                                <label class="float-label">Senha</label>
                                                            </div>
                                                         	<div class="card-block">
                                                            <button class="btn btn-primary btn-round waves-effect waves-light">Novo</button>
                                                            <button class="btn btn-success btn-round waves-effect waves-light">Salvar</button>
                                                        	<button class="btn btn-danger btn-round waves-effect waves-light">Excluir</button>
                                                        	</div>
                                                        </form>
                                                    </div>
                                                </div>
                                            </div>	
                                            <!--  project and team member end -->
                                        </div>
                                        <span>${msg}</span>
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
</body>

</html>
    