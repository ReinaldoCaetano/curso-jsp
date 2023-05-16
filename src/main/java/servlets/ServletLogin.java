package servlets;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.ModelLogin;

import java.io.IOException;

@WebServlet(urlPatterns = {"/ServletLogin"})
public class ServletLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public ServletLogin() {
    
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String login = request.getParameter("login");
		String senha =  request.getParameter("senha");
		
		if (login != null && !login.isEmpty() && senha != null && !senha.isEmpty()) {
			ModelLogin modelLogin = new ModelLogin();
			modelLogin.setLogin(login);
			modelLogin.setSenha(senha);
			
			if (modelLogin.getLogin().equalsIgnoreCase("admin") // simulando login
					&& modelLogin.getSenha().equalsIgnoreCase("admin") ) {
				
				request.getSession().setAttribute("usuario", modelLogin.getLogin());
				
				RequestDispatcher redirecionar = request.getRequestDispatcher("principal/principal.jsp");
				redirecionar.forward(request, response);
				
			}else {
				RequestDispatcher redirecionar = request.getRequestDispatcher("/index.jsp");
				request.setAttribute("msg", "informe login e senha corretamente");
				redirecionar.forward(request, response);
			}
		}else {
			RequestDispatcher redirecionar = request.getRequestDispatcher("index.jsp");
			request.setAttribute("msg", "informe login e senha corretamente");
			redirecionar.forward(request, response);
		}
	
	}

}
