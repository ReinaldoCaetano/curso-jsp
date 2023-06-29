package servlets;

import java.io.Serializable;

import dao.UsuarioDAO;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

public class ServletGenericUtil extends HttpServlet implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	
	private UsuarioDAO usuarioDAO = new UsuarioDAO();
	
	
	
	
	
	
	public Long getUserLogado(HttpServletRequest request) throws Exception {
		
		HttpSession session = request.getSession();
		
		String usuarioLogado = (String) session.getAttribute("usuario");
	
		return usuarioDAO.consultaUsuarioLogado(usuarioLogado).getId();
	}
	
	
	
	
	
	
	
	
	
	
	
}
