package servlets;

import java.io.IOException;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;

import dao.UsuarioDAO;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.Servlet;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.ModelLogin;

@WebServlet(urlPatterns = {"/ServletUsuarioController"})
public class ServletUsuarioController extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
    
	private UsuarioDAO usuarioDAO =  new UsuarioDAO();
  
    public ServletUsuarioController() {
        
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			String acao = request.getParameter("acao");
			if (acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("deletar")) {
				
				String idUser = request.getParameter("id");
				usuarioDAO.deletarUsuario(idUser);
				
				List<ModelLogin> modelLogins = usuarioDAO.consultaUsuarioList();
				request.setAttribute("modelLogins", modelLogins);	
				
				request.setAttribute("msg", "Excluido com sucesso !!");
				request.getRequestDispatcher("principal/usuario.jsp").forward(request, response);
				
			}else if (acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("deletarajax")) {
				
				String idUser = request.getParameter("id");
				usuarioDAO.deletarUsuario(idUser);
				
				response.getWriter().write("Excluido Com Sucesso !");
				
			}else if (acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("buscarUserAjax")) {
				
				String nomeBusca = request.getParameter("nomeBusca");
				
			     List<ModelLogin> dadosJsonUser = usuarioDAO.consultaUsuarioList(nomeBusca);
			     
			     ObjectMapper mapper = new ObjectMapper();
			     
			     String json = mapper.writeValueAsString(dadosJsonUser);
				
			    response.getWriter().write(json);
			    
				
			}else if (acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("buscarEditar")) {
				
				String id = request.getParameter("id");
				
				ModelLogin modelLogin = usuarioDAO.consultaUsuarioId(id);
				
				List<ModelLogin> modelLogins = usuarioDAO.consultaUsuarioList();
				request.setAttribute("modelLogins", modelLogins);	
				
				request.setAttribute("msg", "Usuario em Edição");
				request.setAttribute("modolLogin", modelLogin);
				request.getRequestDispatcher("principal/usuario.jsp").forward(request, response);
				
			}else if (acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("listarUsuario")) {
				
				List<ModelLogin> modelLogins = usuarioDAO.consultaUsuarioList();
				request.setAttribute("msg", "Usuarios em Carregados");
				request.setAttribute("modelLogins", modelLogins);
				request.getRequestDispatcher("principal/usuario.jsp").forward(request, response);
				
			}else {
				
			List<ModelLogin> modelLogins = usuarioDAO.consultaUsuarioList();
		    request.setAttribute("modelLogins", modelLogins);
		    
			request.getRequestDispatcher("principal/usuario.jsp").forward(request, response);
			}
		} catch (Exception e) {
			e.printStackTrace();
			RequestDispatcher redirecionar = request.getRequestDispatcher("erro.jsp");
			request.setAttribute("msg", e.getMessage());
			redirecionar.forward(request, response);
		}
	
	
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			String msg = "Operação realizada com sucesso !";
			
			String id = request.getParameter("id");
			String nome = request.getParameter("nome");
			String email = request.getParameter("email");
			String login = request.getParameter("login");
			String senha = request.getParameter("senha");
			
			ModelLogin modelLogin = new ModelLogin();
			
			modelLogin.setId(id != null && !id.isEmpty() ? Long.parseLong(id) : null);
			modelLogin.setNome(nome);
			modelLogin.setEmail(email);
			modelLogin.setLogin(login);
			modelLogin.setSenha(senha);
			
			if(usuarioDAO.validarLogin(modelLogin.getLogin())  && modelLogin.getId() == null) {
				 msg = "Já existe usuário com o mesmo login !" ;
					
			}else {
				if(modelLogin.isNovo()) {
					msg = "Gravado Com sucesso !!";
				}else {
					msg = "Atualizado com Sucesso !!";
				}
				modelLogin =  usuarioDAO.gravarUsuario(modelLogin);
				
			}
			
			List<ModelLogin> modelLogins = usuarioDAO.consultaUsuarioList();
			request.setAttribute("modelLogins", modelLogins);	
			
			request.setAttribute("msg", msg);
			request.setAttribute("modolLogin", modelLogin);
			request.getRequestDispatcher("principal/usuario.jsp").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
			RequestDispatcher redirecionar = request.getRequestDispatcher("erro.jsp");
			request.setAttribute("msg", e.getMessage());
			redirecionar.forward(request, response);
		}

	}

}
