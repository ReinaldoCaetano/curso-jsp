package servlets;

import java.io.IOException;

import dao.UsuarioDAO;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.Servlet;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.ModelLogin;

@WebServlet("/ServletUsuarioController")
public class ServletUsuarioController extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
    
	private UsuarioDAO usuarioDAO =  new UsuarioDAO();
  
    public ServletUsuarioController() {
        
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
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
			
			modelLogin.setNome(nome);
			modelLogin.setEmail(email);
			modelLogin.setLogin(login);
			modelLogin.setSenha(senha);
			
			if(usuarioDAO.validarLogin(modelLogin.getLogin())) {
			msg = "Já existe usuário com o mesmo login !" ;
				
			}else {
				modelLogin =  usuarioDAO.gravarUsuario(modelLogin);
				
			}
			
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
