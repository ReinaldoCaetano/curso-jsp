package servlets;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.http.HttpServlet;
import java.util.Base64;

import com.fasterxml.jackson.databind.ObjectMapper;

import dao.UsuarioDAO;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import model.ModelLogin;

@MultipartConfig
@WebServlet(urlPatterns = {"/ServletUsuarioController"})
public class ServletUsuarioController extends ServletGenericUtil {
	
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
				
				List<ModelLogin> modelLogins = usuarioDAO.consultaUsuarioList(super.getUserLogado(request));
				request.setAttribute("modelLogins", modelLogins);	
				
				request.setAttribute("msg", "Excluido com sucesso !!");
				request.getRequestDispatcher("principal/usuario.jsp").forward(request, response);
				
			}else if (acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("deletarajax")) {
				
				String idUser = request.getParameter("id");
				usuarioDAO.deletarUsuario(idUser);
				
				response.getWriter().write("Excluido Com Sucesso !");
				
			}else if (acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("buscarUserAjax")) {
				
				String nomeBusca = request.getParameter("nomeBusca");
				
			     List<ModelLogin> dadosJsonUser = usuarioDAO.consultaUsuarioList(nomeBusca,super.getUserLogado(request));
			     
			     ObjectMapper mapper = new ObjectMapper();
			     
			     String json = mapper.writeValueAsString(dadosJsonUser);
				
			    response.getWriter().write(json);
			    
				
			}else if (acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("buscarEditar")) {
				
				String id = request.getParameter("id");
				
				ModelLogin modelLogin = usuarioDAO.consultaUsuarioId(id,super.getUserLogado(request));
				
				List<ModelLogin> modelLogins = usuarioDAO.consultaUsuarioList(super.getUserLogado(request));
				request.setAttribute("modelLogins", modelLogins);	
				
				request.setAttribute("msg", "Usuario em Edição");
				request.setAttribute("modolLogin", modelLogin);
				request.getRequestDispatcher("principal/usuario.jsp").forward(request, response);
				
			}else if (acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("listarUsuario")) {
				
				List<ModelLogin> modelLogins = usuarioDAO.consultaUsuarioList(super.getUserLogado(request));
				request.setAttribute("msg", "Usuarios em Carregados");
				request.setAttribute("modelLogins", modelLogins);
				request.getRequestDispatcher("principal/usuario.jsp").forward(request, response);
				
			}else {
				
			List<ModelLogin> modelLogins = usuarioDAO.consultaUsuarioList(super.getUserLogado(request));
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
			String perfil = request.getParameter("perfil");
			String sexo = request.getParameter("sexo");
			
			ModelLogin modelLogin = new ModelLogin();
			
			modelLogin.setId(id != null && !id.isEmpty() ? Long.parseLong(id) : null);
			modelLogin.setNome(nome);
			modelLogin.setEmail(email);
			modelLogin.setLogin(login);
			modelLogin.setSenha(senha);
			modelLogin.setPerfil(perfil);
			modelLogin.setSexo(sexo);
			
			
			 boolean isMultipart = request.getContentType() != null && request.getContentType().startsWith("multipart/form-data");

	            if (isMultipart) {
	                Part part = request.getPart("fileFoto"); // pega foto da tela

	                if (part != null && part.getSize() > 0) {
	                    byte[] foto = part.getInputStream().readAllBytes();
	                    String imagemBase64 = "data:image/" +part.getContentType().split("\\/")[1] +";base64,"+ Base64.getEncoder().encodeToString(foto);
	                    modelLogin.setFotouser(imagemBase64);
	                    modelLogin.setExtensaofotouser(part.getContentType().split("\\/")[1]);
	                }
	            }


			
			if(usuarioDAO.validarLogin(modelLogin.getLogin())  && modelLogin.getId() == null) {
				 msg = "Já existe usuário com o mesmo login !" ;
					
			}else {
				if(modelLogin.isNovo()) {
					msg = "Gravado Com sucesso !!";
				}else {
					msg = "Atualizado com Sucesso !!";
				}
				modelLogin =  usuarioDAO.gravarUsuario(modelLogin, super.getUserLogado(request));
				
			}
			
			List<ModelLogin> modelLogins = usuarioDAO.consultaUsuarioList(super.getUserLogado(request));
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
